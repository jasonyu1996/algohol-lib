package algohol.judge.data.raw;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;

import algohol.com.DataIOInterface;

public class RawProblemTestData {
	private Vector<RawTestcaseData> testcases;
	private boolean redirect;
	private String inFile, outFile;
	private RawCode specialChecker;
	private int checkerType;
	public RawProblemTestData(DataIOInterface io) throws IOException{
		int tcnt = Integer.valueOf(io.readLine());
		testcases = new Vector<RawTestcaseData>();
		for(int i = 0; i < tcnt; i ++)
			testcases.add(new RawTestcaseData(io));
		int r = Integer.valueOf(io.readLine());
		redirect = r != 0;
		if(redirect){
			inFile = io.readLine();
			outFile = io.readLine();
		}
		checkerType = Integer.parseInt(io.readLine());
		if(checkerType == 1)
			specialChecker = new RawCode(io);
	}
	public void write(DataIOInterface io) throws IOException{
		int tcnt = testcases.size();
		io.writeLine(String.valueOf(tcnt));
		for(int i = 0; i < tcnt; i ++)
			testcases.elementAt(i).write(io);
		if(redirect)
			io.writeLine("1");
		else
			io.writeLine("0");
		if(redirect){
			io.writeLine(inFile);
			io.writeLine(outFile);
		}
		io.writeLine(String.valueOf(checkerType));
		if(checkerType == 1)
			specialChecker.write(io);
	}
	public RawTestcaseData[] getTestcases() {
		return (RawTestcaseData[])testcases.toArray();
	}
	public boolean isRedirect() {
		return redirect;
	}
	public String getInFile() {
		return inFile;
	}
	public String getOutFile() {
		return outFile;
	}
	public RawCode getSpecialChecker() {
		return specialChecker;
	}
	public int getCheckerType() {
		return checkerType;
	}
	
	public String getMD5(){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(toString().getBytes());
		StringBuilder hex = new StringBuilder(); 
		byte[] bin = md.digest();
		for(int i = 0; i < bin.length; i ++){
			String c = Integer.toHexString(bin[i]);
			if(c.length() == 1)
				hex.append("0");
			hex.append(c);
		}
		return hex.toString();
	}
}
