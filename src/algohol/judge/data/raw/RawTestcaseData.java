package algohol.judge.data.raw;

import java.io.IOException;

import algohol.com.DataIOInterface;

public class RawTestcaseData {
	private byte[] input, output;
	int timeLimit, memoryLimit;
	
	public RawTestcaseData(DataIOInterface io) throws IOException{
		timeLimit = Integer.valueOf(io.readLine());
		memoryLimit = Integer.valueOf(io.readLine());
		input = io.readBytes();
		output = io.readBytes();
	}
	public byte[] getInput() {
		return input.clone();
	}
	public byte[] getOutput() {
		return output.clone();
	}
	
	public void write(DataIOInterface io) throws IOException{
		io.writeLine(String.valueOf(timeLimit));
		io.writeLine(String.valueOf(memoryLimit));
		io.writeBytes(input);
		io.writeBytes(output);
	}
	
	public int getTimeLimit(){
		return timeLimit;
	}
	
	public int getMemoryLimit(){
		return memoryLimit;
	}
}
