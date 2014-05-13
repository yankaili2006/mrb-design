/**
 * Mar 20, 2011 
 * Searcher.java 
 */
package com.mrb.tool.lucene;

import java.io.IOException;
import java.util.ArrayList;

import net.paoding.analysis.analyzer.PaodingAnalyzer;
import net.paoding.analysis.examples.gettingstarted.BoldFormatter;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.mrb.Log4JConfigure;
import com.mrb.util.TimeFormatUtil;


/**
 * @author Administrator
 *10:07:03 PM
 *
 *在索引目录中，搜索我们要找的文档，返回文档信息
 *
 */
public class Search {


	private static String FIELD_CONENT = "content";
	private static String FIELD_FILENAME = "fileName";
	
	private static String QUERY = "package";
	
	private static final String indexPath = "E:\\lucene\\index";
	
	private Logger logger = Logger.getLogger("seacher");
	
	/*
	 * 构造函数
	 * 
	 * Searcher会与库函数重名
	 * */
	public Search(){
		//配置Log4J
		Log4JConfigure.configure();
	}
	
	public ArrayList<SearchHit> search(String searchKey){
		
		//开始时间
		long startTime = System.currentTimeMillis();
		
		Analyzer analyzer = new PaodingAnalyzer();
		Directory dir = null;
		IndexReader reader = null;
		
		try {
			dir = FSDirectory.getDirectory(indexPath);
			reader = IndexReader.open(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//construct query
		String queryString = QUERY;
		QueryParser parser = new QueryParser(FIELD_CONENT, analyzer);
		Query query = null;
		try {
			query = parser.parse(queryString);
			query = query.rewrite(reader);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		// search
		Searcher searcher = null;
		try {
			searcher = new IndexSearcher(dir);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("Searching for: " + query.toString(FIELD_CONENT));
		//把查询关键字写入日志
		logger.info("Searching for: " + query.toString(FIELD_CONENT));
		
		//best top hits
		TopDocs topDocs = null;
		try {
			topDocs = searcher.search(query ,null ,1000);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//存放搜索结果
		ArrayList<SearchHit> result = new ArrayList<SearchHit>();
		
		//文档得分
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for (int i = 0; i<topDocs.totalHits; i++) {
			Document document = null;
			try {
				document = searcher.doc(scoreDocs[i].doc);
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//获取文档信息
			String content = document.getField(FIELD_CONENT).stringValue();
			String fileName = document.getField(FIELD_FILENAME).stringValue();
			float score = scoreDocs[i].score;
			
			//输出文档名称以及得分值
			//System.out.println(fileName + " , "+ score);
			
			//把搜索结果写入日志
			logger.info(fileName + " , "+ score);
			
			SearchHit shit = new SearchHit();
			shit.setContent(content);
			shit.setFileName(fileName);
			shit.setScore(score);
			
			result.add(shit);
		}
		
		//结束时间
		long endTime = System.currentTimeMillis();
		//记录搜索时间
		logger.info("time cosumed:"+TimeFormatUtil.ms2String(endTime - startTime));
		
		//关闭读入数据流
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Search search = new Search();
		String searchKey = Search.QUERY;
		search.search(searchKey);
	}

}
