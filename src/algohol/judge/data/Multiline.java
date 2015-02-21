package algohol.judge.data;

import java.io.BufferedReader;
import java.io.IOException;

public final class Multiline {
	private Multiline() {}
	public static String getLines(BufferedReader in) throws IOException{
		int lineNumber = Integer.valueOf(in.readLine());
		StringBuilder cont = new StringBuilder();
		for(int i = 0; i < lineNumber; i ++)
			cont.append(in.readLine());
		return cont.toString();
	}
	public static String serialize(String s){
		int lineNumber = 0, slen = s.length();
		for(int i = 0; i < slen; i ++)
			if(s.charAt(i) == '\n')
				++ lineNumber;
		return lineNumber + "\n" + s;
	}
}
