/**
 * Mar 20, 2011 
 * SearchHit.java 
 */
package com.mrb.tool.lucene;

/**
 * @author Administrator
 *10:42:24 PM
 */
public class SearchHit {

	private String content;
	private String fileName;
	private float score;
	
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}


	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}


	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}


	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	/**
	 * @return the score
	 */
	public float getScore() {
		return score;
	}


	/**
	 * @param score the score to set
	 */
	public void setScore(float score) {
		this.score = score;
	}



}
