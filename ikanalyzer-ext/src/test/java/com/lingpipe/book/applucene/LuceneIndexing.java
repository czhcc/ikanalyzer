package com.lingpipe.book.applucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ky.ext.VecTextField;

public class LuceneIndexing {

    /*x LuceneIndexing.1 */
    public static void main(String[] args) 
        throws CorruptIndexException, LockObtainFailedException,
               IOException {

        File docDir = new File("D:/works/index_file3");
        File indexDir = new File("D:/temp/index_test4/");
        
        Path path = Paths.get("D:/temp/index_test4/");
        Directory fsDir = FSDirectory.open(path);

        Analyzer stdAn 
            = new IKAnalyzer(true);
        /*Analyzer ltcAn 
            = new LimitTokenCountAnalyzer(stdAn,Integer.MAX_VALUE);*/

        IndexWriterConfig iwConf 
            = new IndexWriterConfig(stdAn);
        iwConf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        IndexWriter indexWriter
            = new IndexWriter(fsDir,iwConf);
        /*x*/

        /*x LuceneIndexing.2 */        
        for (File f : docDir.listFiles()) {
            String fileName = f.getName();
//            String text = Files.readFromFile(f,"ASCII");
            Document d = new Document();
            d.add(new StringField("file",fileName,Store.YES));
            d.add(new VecTextField("text",new FileReader(f), Store.NO));//,Store.YES,Index.ANALYZED)
            indexWriter.addDocument(d);
        }
        int numDocs = indexWriter.numDocs();

        indexWriter.forceMerge(1);
        indexWriter.commit();
        indexWriter.close();
        /*x*/
        System.out.println("Index Directory=" + indexDir.getCanonicalPath());
        System.out.println("Doc Directory=" + docDir.getCanonicalPath());
        System.out.println("num docs=" + numDocs);
    }

}