package algohol.com;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public final class DataIOInterface {
	private Reader reader;
	private Writer writer;
	private InputStream in;
	private OutputStream out;
	
	public DataIOInterface(InputStream in, OutputStream out) throws IOException{
		this.in = new BufferedInputStream(in);
		this.reader = new InputStreamReader(this.in, "UTF-8");
		
		this.out = out;
		this.writer = new OutputStreamWriter(this.out, "UTF-8");
	}
	
	public void close() throws IOException{
		reader.close();
		in.close();
		writer.close();
		out.close();
	}
	
	public String readLine() throws IOException{
		StringBuilder s = new StringBuilder();
		int c;
		do{
			c = reader.read();
			s.append((char)c);
		} while(c != '\n');
		return s.toString();
	}
	
	public void writeLine(String s) throws IOException{
		writer.write(s + "\n");
	}
	
	public byte[] readBytes() throws IOException{
		int size = Integer.valueOf(readLine());
		byte[] cont = new byte[size];
		for(int bread = 0; bread < size; ){
			int c = in.read(cont, bread, size - bread);
			if(c == -1)
				break;
			bread += c;
		}
		return cont;
	}
	public void writeBytes(byte[] bytes) throws IOException{
		writer.write(bytes.length + "\n");
		out.write(bytes);
	}
}
