package algohol.judge.data.raw;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
	
	public File writeToFile(String path) throws IOException{
		File t = new File(path + "." + type);
		Files.write(t.toPath(), contents);
		return t;
	}
}
