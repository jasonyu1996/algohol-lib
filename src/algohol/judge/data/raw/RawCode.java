package algohol.judge.data.raw;

import java.io.IOException;

import algohol.com.DataIOInterface;

public class RawCode {
	private byte[] contents;
	private String type;
	public RawCode(byte[] contents, String type){
		this.contents = contents;
		this.type = type;
	}
	public RawCode(DataIOInterface io) throws IOException{
		this.type = io.readLine();
		this.contents = io.readBytes();
	}
	
	public byte[] getContents() {
		return contents.clone();
	}
	
	public String getType() {
		return type;
	}
	
	public void write(DataIOInterface io) throws IOException{
		io.writeLine(type);
		io.writeBytes(contents);
	}
}
