/**
 * 
 */
package com.ky.ext;

import java.io.IOException;

import org.apache.lucene.index.IndexableField;
import org.apache.lucene.search.SortField;
import org.apache.solr.response.TextResponseWriter;
import org.apache.solr.schema.FieldType;
import org.apache.solr.schema.SchemaField;
import org.apache.solr.uninverting.UninvertingReader;

/**
 * <p>
 * <b>VecTextFieldType</b> 是
 * </p>
 *
 * @since 2013-2-4
 * @author czhcc
 * @version $Id: VecTextFieldType.java 12517 2013-08-27 00:49:11Z czh $
 *
 */
public class VecTextFieldType extends FieldType
{

	/* (non-Javadoc)
	 * @see org.apache.solr.schema.FieldType#write(org.apache.solr.response.TextResponseWriter, java.lang.String, org.apache.lucene.index.IndexableField)
	 */
	@Override
	public void write(TextResponseWriter writer, String name, IndexableField f)
			throws IOException
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.apache.solr.schema.FieldType#getSortField(org.apache.solr.schema.SchemaField, boolean)
	 */
	@Override
	public SortField getSortField(SchemaField field, boolean top)
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.solr.schema.FieldType#getUninversionType(org.apache.solr.schema.SchemaField)
	 */
	@Override
	public UninvertingReader.Type getUninversionType(SchemaField field)
	{
		return null;
	}

}
