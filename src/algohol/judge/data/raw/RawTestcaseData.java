package algohol.judge.data.raw;

import java.io.IOException;

import algohol.com.DataIOInterface;

public class RawTestcaseData {
	private byte[] input, output;
	
	public RawTestcaseData(DataIOInterface io) throws IOException{
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
		io.writeBytes(input);
		io.writeBytes(output);
	}
}
