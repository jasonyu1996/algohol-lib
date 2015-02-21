package algohol.judge.data;

import java.io.BufferedReader;
import java.io.IOException;

class TestcaseData {
	private String input, output;
	
	TestcaseData(BufferedReader in) throws IOException{
		input = Multiline.getLines(in);
		output = Multiline.getLines(in);
	}
	String getInput() {
		return input;
	}
	String getOutput() {
		return output;
	}
	
	public String toString(){
		return Multiline.serialize(input) + Multiline.serialize(output);
	}
}
