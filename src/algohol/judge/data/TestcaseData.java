package algohol.judge.data;

import java.io.File;

public class TestcaseData {
	private File in, out;
	private int timeLimit, memoryLimit;
	
	TestcaseData(File in, File out, int timeLimit, int memoryLimit){
		this.in = in;
		this.out = out;
		this.timeLimit = timeLimit;
		this.memoryLimit = memoryLimit;
	}
	
	public File getIn() {
		return in;
	}
	public File getOut() {
		return out;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public int getMemoryLimit() {
		return memoryLimit;
	}
}
