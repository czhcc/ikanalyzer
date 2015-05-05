/**
 * 
 */
package com.ky;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.Field.TermVector;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * <p>
 * <b>LuceneIndexingBoost</b> 是
 * </p>
 *
 * @since 2013-8-2
 * @author czhcc
 * @version $Id: LuceneIndexingBoost.java 12517 2013-08-27 00:49:11Z czh $
 *
 */
public class LuceneIndexingBoost
{
	public static String[][] contents = {
		{"张三一","440106200101010550","123456一"},
		{"张三","440106200101010551","男,改进作风的任务非常繁重，八项规定是一个动员令，也是解决问题的开端和破题二"},
		{"张三强","440106200101010552","习近平总书记在十八届中央纪委二次全会上强调三"},
		{"张三","440106200101010551","男,改进作风的任务非常繁重，八项规定是一个动员令，也是解决问题的开端和破题四"},
		{"张三保","440106200101010553","12345,最根本的是要坚持和发扬艰苦奋斗精神五"},
		{"张三","4401062001010105501","男,改进作风的任务非常繁重，八项规定是一个动员令，也是解决问题的开端和破题六"},
		{"张三保","440106200101010553","最根本的是要坚持和发扬艰苦奋斗精神七"}};
	
	public static void main(String[] args) throws Exception
	{
		File indexDir = new File("D:/temp/index_ky/");
		indexDir.delete();
        
        Directory fsDir = FSDirectory.open(indexDir);

        Analyzer stdAn = new IKAnalyzer(true);

        IndexWriterConfig iwConf = new IndexWriterConfig(Version.LUCENE_40,stdAn);
        iwConf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        IndexWriter indexWriter = new IndexWriter(fsDir,iwConf);
        
        for(String[] record : contents)
        {
        	Document d = new Document();
        	String content = record[0] + "," + record[1] + "," + record[2];
        	System.out.println("content=" + content);
        	String subject = record[0] + "," + record[1];
        	d.add(new Field("xm", record[0], Store.NO, Index.ANALYZED, TermVector.NO));
        	d.add(new Field("gj", record[1], Store.NO, Index.ANALYZED, TermVector.NO));
        	d.add(new Field("subject", subject, Store.NO, Index.ANALYZED, TermVector.NO));
        	Field f = new Field("content", content, Store.NO, Index.ANALYZED, TermVector.NO);
        	if("张三".equals(record[0]))
        	{
        		f.setBoost(2f);
        	}
        	d.add(f);
        	d.add(new Field("content_display", content, Store.YES, Index.NO, TermVector.NO));
        	indexWriter.addDocument(d);
        }
        
        int numDocs = indexWriter.numDocs();

        indexWriter.forceMerge(1);
        indexWriter.commit();
        indexWriter.close();
	}
}
