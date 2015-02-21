package algohol.judge.data;

import java.io.BufferedReader;
import java.io.IOException;

public class Code {
	private String contents, type;
	public Code(String contents, String type){
		this.contents = contents;
		this.type = type;
	}
	public Code(BufferedReader in) throws IOException{
		this.type = in.readLine();
		this.contents = Multiline.getLines(in);
	}
	
	public String getContents() {
		return contents;
	}
	public String getType() {
		return type;
	}
	
	public String toString(){
		return type + "\n" + Multiline.serialize(contents);
	}
	
}
