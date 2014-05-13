/**
 * Feb 26, 2011 
 * Pdf2Txt.java 
 */
package com.mrb;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @author Administrator 1:36:58 PM
 * 
 * Convert PDF file to Txt format file
 * 
 * 被上传文件的servlet upload调用
 */
public class Pdf2Txt extends Thread {

	// get properties Instance
	private Properties config = ConfigProperties.getInstance();

	// input file
	private String inFileName;
	// output file
	private String outFileName;

	public Pdf2Txt(String inName, String outName) {
		inFileName = inName;
		outFileName = outName;
	}

	public void run() {
		toText(inFileName, outFileName);
	}

	private void toText(String inFileName, String outFileName) {
		// The execute XPDF path
		String PATH_TO_XPDF = config.getProperty("pdf2txtPath");
		// input directory
		String inFilePath = config.getProperty("pdf2txtInFilePath");
		// output directory
		String outFilePath = config.getProperty("pdf2txtOutFilePath");

		// convert command
		String[] command = new String[] { PATH_TO_XPDF, "-cfg", "xpdfrc", "-q", "-enc", "GBK", "-eol", "dos", "-nopgbrk", inFilePath + inFileName,
				outFilePath + outFileName };

		// the process the run the command
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedInputStream bis = new BufferedInputStream(p.getInputStream());
		InputStreamReader reader;
		try {
			reader = new InputStreamReader(bis, "UTF-8");
			int ch = 0;
			while ((ch = reader.read()) != -1) {
				System.out.println((char) ch);
			}
			reader.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
