package com.lingpipe.book.applucene;

import org.apache.lucene.index.Term;

import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanQuery;

import org.apache.lucene.search.BooleanClause.Occur;



class FragmentsLucene {

    private FragmentsLucene() { /* no instances */ }

    void frag1() {
        
        /*x FragmentsLucene.1 */
        BooleanQuery bq1 = new BooleanQuery.Builder()
        		.add(new TermQuery(new Term("text","biology")), Occur.MUST)
        		.add(new TermQuery(new Term("text","cell")), Occur.SHOULD).build();
        
        BooleanQuery bq2 = new BooleanQuery.Builder()
        		.add(new TermQuery(new Term("text","micro")), Occur.SHOULD)
        		.add(bq1,Occur.MUST).build();
        /*x*/
    }
    
}