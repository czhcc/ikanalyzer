/**
 * 
 */
package com.ky.lucene;

import java.io.Reader;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.wltea.analyzer.lucene.IKTokenizer;

/**
 * <p>
 * <b>IKTokenizerFactory</b> 是
 * </p>
 *
 * @since 2013-2-4
 * @author czhcc
 * @version $Id: IKTokenizerFactory.java 12517 2013-08-27 00:49:11Z czh $
 *
 */
public class IKTokenizerFactory extends TokenizerFactory
{

	/* (non-Javadoc)
	 * @see org.apache.lucene.analysis.util.TokenizerFactory#create(java.io.Reader)
	 */
	@Override
	public Tokenizer create(Reader input)
	{
		String smart = this.getArgs().get("smart");
		boolean useSmart = false;
		if("true".equals(smart)){
			useSmart = true;
		}
		Tokenizer _IKTokenizer = new IKTokenizer(input , useSmart);
		
		return _IKTokenizer;
	}

}
