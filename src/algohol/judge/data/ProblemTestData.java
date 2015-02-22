package algohol.judge.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProblemTestData {
	private boolean redirect;
	private String input, output;
	private TestcaseData[] testcases;
	private int checkerType;
	private File specialChecker;
	
	ProblemTestData(File dir) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File(dir, "config")));
		
		int tcnt = Integer.valueOf(reader.readLine());
		testcases = new TestcaseData[tcnt];
		for(int i = 0; i < tcnt; i ++){
			int timeLimit = Integer.valueOf(reader.readLine());
			int memoryLimit = Integer.valueOf(reader.read());
			testcases[i] = new TestcaseData(new File(dir, "test" + i + ".in"), new File(dir, "test" + i + ".out"),
					timeLimit, memoryLimit);
		}
		if(Integer.valueOf(reader.readLine()) == 1){
			redirect = true;
			input = reader.readLine();
			output = reader.readLine();
		} else
			redirect = false;
		checkerType = Integer.valueOf(reader.readLine());
		if(checkerType == 1)
			specialChecker = new File(dir, "checker." + reader.readLine());
		
		reader.close();
	}
	public boolean isRedirect() {
		return redirect;
	}
	public String getInput() {
		return input;
	}
	public String getOutput() {
		return output;
	}
	public TestcaseData[] getTestcases() {
		return testcases.clone();
	}
	public int getCheckerType() {
		return checkerType;
	}
	public void setCheckerType(int checkerType) {
		this.checkerType = checkerType;
	}
	public File getSpecialChecker(){
		return specialChecker;
	}
}
