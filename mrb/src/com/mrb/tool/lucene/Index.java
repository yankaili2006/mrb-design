/**
 * Mar 20, 2011 
 * Index.java 
 */
package com.mrb.tool.lucene;

import java.io.File;
import java.io.IOException;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;

import com.mrb.Log4JConfigure;
import com.mrb.util.PageShowUtil;
import com.mrb.util.TimeFormatUtil;

/**
 * @author Administrator 9:44:36 PM
 * 
 * 建立索引
 */
public class Index {

	// 索引存储的位置
	private static final String indexPath = "E:\\lucene\\index";

	// 索引三个字段：内容,文档全路径以及文件大小
	private static String FIELD_CONENT = "content";
	private static String FIELD_FILENAME = "fileName";
	private static String FIELD_FILESIZE = "size";

	private Logger log = Logger.getLogger("indexer");

	/*
	 * 构造函数
	 */
	public Index() {
		// 配置Log4J
		Log4JConfigure.configure();
	}

	/*
	 * 对某个文档建立索引，只能对txt文本文档建立索引
	 */
	public void addDoc(File file) {
		// 获取内容
		String content = ContentReader.readText(file);
		// 添加内容到索引
		Document doc = new Document();
		doc.add(new Field(FIELD_CONENT, content, Field.Store.YES, Field.Index.TOKENIZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
		doc.add(new Field(FIELD_FILENAME, file.getAbsolutePath() + "\\" + file.getName(), Field.Store.YES, Field.Index.TOKENIZED,
				Field.TermVector.WITH_POSITIONS_OFFSETS));
		doc.add(new Field(FIELD_FILESIZE, PageShowUtil.number2GMK(file.length()), Field.Store.YES, Field.Index.TOKENIZED,
				Field.TermVector.WITH_POSITIONS_OFFSETS));

		// 存取索引的目录
		Directory dir = null;
		try {
			dir = FSDirectory.getDirectory(indexPath);
		} catch (IOException e) {
			e.printStackTrace();
		} // new RAMDirectory();

		// 分词，写索引到文件系统，这里需要配置paoding-dic-home.properties
		Analyzer analyzer = new PaodingAnalyzer();
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(dir, analyzer);
			writer.addDocument(doc);
			writer.optimize();
			writer.close();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * 递归某个目录建立索引，只能对txt文本文档建立索引
	 * 
	 */
	public void indexDir(String dirParent) {
		File dir = new File(dirParent);
		if (dir.isDirectory()) {
			String list[] = dir.list();
			for (int i = 0; i < list.length; i++) {
				File file = new File(dirParent + "\\" + list[i]);
				if (file.isDirectory()) {
					indexDir(dirParent + "\\" + list[i]);
				} else {
					// 开始时间
					long startTime = System.currentTimeMillis();

					// 获取文件名
					String name = file.getName();
					String path = dirParent + "\\";
					path = path.replace("\\", "\\\\");
					String sourceFile = path + name;

					// 判断文件格式
					String lowerName = name.toLowerCase();
					if (lowerName != null && (lowerName.endsWith(".txt"))) {

						// 对文档建立索引
						addDoc(file);

						// 结束时间
						long endTime = System.currentTimeMillis();
						long costTime = endTime - startTime;
						log.info("[" + TimeFormatUtil.ms2String(costTime) + "] " + sourceFile);

					} else {
						continue;
					}
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Index indexer = new Index();
		String txtPath = "E:\\\\lucene\\txt";
		indexer.indexDir(txtPath);

	}

}
