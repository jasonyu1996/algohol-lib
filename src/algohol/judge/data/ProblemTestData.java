package algohol.judge.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;

public class ProblemTestData {
	private Vector<TestcaseData> testcases;
	private boolean redirect;
	private String inFile, outFile;
	private Code specialChecker;
	private int checkerType;
	public ProblemTestData(BufferedReader in) throws IOException{
		int tcnt = Integer.valueOf(in.readLine());
		testcases = new Vector<TestcaseData>();
		for(int i = 0; i < tcnt; i ++)
			testcases.add(new TestcaseData(in));
		int r = Integer.valueOf(in.readLine());
		redirect = r != 0;
		if(redirect){
			inFile = in.readLine();
			outFile = in.readLine();
		}
		checkerType = Integer.parseInt(in.readLine());
		if(checkerType == 1)
			specialChecker = new Code(in);
	}
	public String toString(){
		StringBuilder res = new StringBuilder();
		int tcnt = testcases.size();
		res.append(tcnt + "\n");
		for(int i = 0; i < tcnt; i ++)
			res.append(testcases.elementAt(i));
		if(redirect)
			res.append("1\n");
		else
			res.append("0\n");
		if(redirect){
			res.append(inFile + "\n");
			res.append(outFile + "\n");
		}
		res.append(checkerType + "\n");
		if(checkerType == 1)
			res.append(specialChecker);
		return res.toString();
	}
	public TestcaseData[] getTestcases() {
		return (TestcaseData[])testcases.toArray();
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
	public Code getSpecialChecker() {
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
