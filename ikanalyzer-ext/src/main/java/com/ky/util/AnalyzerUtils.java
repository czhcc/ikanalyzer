/**
 * 
 */
package com.ky.util;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PayloadAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.util.BytesRef;

/**
 * <p>
 * <b>AnalyzerUtils</b> 是
 * </p>
 * 
 * @since 2013年8月24日
 * @author czhcc
 * @version $Id: AnalyzerUtils.java 12517 2013-08-27 00:49:11Z czh $
 * 
 */
public class AnalyzerUtils
{
	public static void displayTokens(Analyzer analyzer, String text)
			throws IOException
	{
		displayTokens(analyzer.tokenStream("contents", new StringReader(text)));

	}

	public static void displayTokens(TokenStream stream) throws IOException
	{
		CharTermAttribute charTermAttribute = stream.addAttribute(CharTermAttribute.class);
		stream.reset();

		while (stream.incrementToken()) {
		    String term = charTermAttribute.toString();
			System.out.println("[" + term + "] ");
		}

	}

	public static void displayTokensWithPositions(Analyzer analyzer, String text)
			throws IOException
	{
		TokenStream stream = analyzer.tokenStream("contents", new StringReader(
				text));

		CharTermAttribute term = stream.addAttribute(CharTermAttribute.class);
		PositionIncrementAttribute posIncr = stream
				.addAttribute(PositionIncrementAttribute.class);

		int position = 0;
		while (stream.incrementToken()) {

			int increment = posIncr.getPositionIncrement();
			if (increment > 0) {
				position = position + increment;
				System.out.println();
				System.out.print(position + ":");
			}

			System.out.print("[" + term.toString() + "] ");

		}
		System.out.println();

	}

	public static void displayTokensWithFullDetails(Analyzer analyzer,
			String text) throws IOException
	{

		TokenStream stream = analyzer.tokenStream("contents", new StringReader(
				text));

		CharTermAttribute term = stream.addAttribute(CharTermAttribute.class);
		PositionIncrementAttribute posIncr = stream
				.addAttribute(PositionIncrementAttribute.class);
		OffsetAttribute offset = stream.addAttribute(OffsetAttribute.class);
		TypeAttribute type = stream.addAttribute(TypeAttribute.class);
		PayloadAttribute payload = stream.addAttribute(PayloadAttribute.class);

		int position = 0;
		while (stream.incrementToken()) {

			int increment = posIncr.getPositionIncrement();
			if (increment > 0) {
				position = position + increment;
				System.out.println();
				System.out.print(position + ":");
			}

			BytesRef pl = payload.getPayload();

			if (pl != null) {
				System.out.print("[" + term.toString() + ":" + offset.startOffset()
						+ "->" + offset.endOffset() + ":" + type.type() + ":"
						+ new String(pl.bytes) + "] ");

			} else {
				System.out.print("[" + term.toString() + ":" + offset.startOffset()
						+ "->" + offset.endOffset() + ":" + type.type() + "] ");

			}

		}
		System.out.println();
	}
}
