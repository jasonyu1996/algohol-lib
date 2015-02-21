package algohol.judge.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProblemTestDataManager {
	private File dir;
	
	public ProblemTestDataManager(File dir) throws IOException{
		this.dir = dir;
		if(!dir.exists())
			dir.mkdirs();
	}
	
	public synchronized String getHashVal(String id) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File(dir, id + "/" + "MD5")));
		String res = reader.readLine();
		reader.close();
		return res;
	}
	
	public synchronized boolean isExistent(String id){
		return new File(dir, id).exists();
	}
	
	public synchronized void updateProblemTestData(String id, ProblemTestData data) throws IOException{
		File workDir = new File(dir, id);
		if(!workDir.exists())
			workDir.mkdir();
		TestcaseData[] testcases = data.getTestcases();
		BufferedWriter writer;
		writer = new BufferedWriter(new FileWriter(new File(workDir, "config")));
		writer.write(testcases.length + "\n");
		writer.write((data.isRedirect() ? 1 : 0) + "\n");
		if(data.isRedirect()){
			writer.write(data.getInFile() + "\n");
			writer.write(data.getOutFile() + "\n");
		}
		writer.write(data.getCheckerType() + "\n");
		if(data.getCheckerType() == 1)
			writer.write(data.getSpecialChecker().getType() + "\n");
		writer.flush();
		writer.close();
		for(int i = 0; i < testcases.length; i ++){
			writer = new BufferedWriter(new FileWriter(new File(workDir, "test" + i + ".in")));
			writer.write(testcases[i].getInput());
			writer.close();
			
			writer = new BufferedWriter(new FileWriter(new File(workDir, "test" + i + ".out")));
			writer.write(testcases[i].getOutput());
			writer.close();
		}
		if(data.isRedirect()){
			writer = new BufferedWriter(new FileWriter(new File(workDir, "checker")));
			writer.write(data.getSpecialChecker().getContents());
			writer.close();
		}
	}
	
}
