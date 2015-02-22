package algohol.judge.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import algohol.judge.data.raw.RawProblemTestData;
import algohol.judge.data.raw.RawTestcaseData;

public class ProblemTestDataManager {
	private File dir;
	
	public ProblemTestDataManager(File dir) throws IOException{
		this.dir = dir;
		if(!dir.exists())
			dir.mkdirs();
	}
	
	public synchronized ProblemTestData getData(String id) throws IOException{
		File workDir = new File(dir, id);
		if(!workDir.exists())
			return null;
		return new ProblemTestData(workDir);
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
	
	public synchronized void updateProblemTestData(String id, RawProblemTestData data) throws IOException{
		File workDir = new File(dir, id);
		if(!workDir.exists())
			workDir.mkdir();
		RawTestcaseData[] testcases = data.getTestcases();
		BufferedWriter writer;
		writer = new BufferedWriter(new FileWriter(new File(workDir, "config")));
		//--------test cases------------------
		writer.write(testcases.length + "\n");
		for(int i = 0; i < testcases.length; i ++){
			writer.write(testcases[i].getTimeLimit() + "\n");
			writer.write(testcases[i].getMemoryLimit() + "\n");
			Files.write(new File(workDir, "test" + i + ".in").toPath(), testcases[i].getInput());
			Files.write(new File(workDir, "test" + i + ".out").toPath(), testcases[i].getOutput());
		}
		//--------redirect--------------------
		writer.write((data.isRedirect() ? 1 : 0) + "\n");
		if(data.isRedirect()){
			writer.write(data.getInFile() + "\n");
			writer.write(data.getOutFile() + "\n");
		}
		//--------checker---------------------
		writer.write(data.getCheckerType() + "\n");
		if(data.getCheckerType() == 1){
			writer.write(data.getSpecialChecker().getType() + "\n");
			data.getSpecialChecker().writeToFile(new File(workDir, "checker").toString());
		}
		writer.flush();
		writer.close();
	}
	
}
