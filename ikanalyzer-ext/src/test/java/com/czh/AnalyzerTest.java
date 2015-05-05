/**
 * 
 */
package com.czh;

import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.junit.Ignore;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ky.util.AnalyzerUtils;

/**
 * <p>
 * <b>AnalyzerTest</b> 是
 * </p>
 *
 * @since 2013-8-16
 * @author czhcc
 * @version $Id: AnalyzerTest.java 12963 2015-05-05 06:08:43Z czh $
 *
 */
public class AnalyzerTest
{
	@Ignore
	@Test
	public void test1() throws Exception
	{
		QueryParser contentquery = new QueryParser(Version.LUCENE_36,"content", new CJKAnalyzer(Version.LUCENE_36));
		Query parse = contentquery.parse("ABC");
		System.out.println(parse.toString());
	}
	
	@Test
	public void test2() throws Exception
	{
		String s1 = "2007年3月9日晚上8时多，事主张小平报称其停放在长江路华信花园170号铺面门口的粤DJD877红色光威牌GW125-6摩托车被盗";
//		String s1 = "张小平对这事主张和平解决";
		IKAnalyzer ikAnalyzer = new IKAnalyzer(true);
		AnalyzerUtils.displayTokens(ikAnalyzer, s1);
	}
}
