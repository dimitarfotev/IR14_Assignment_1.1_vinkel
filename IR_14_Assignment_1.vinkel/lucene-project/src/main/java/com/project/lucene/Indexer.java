package com.project.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

public class Indexer {
	private IndexWriter writer;

	public Indexer(String indexDirectoryPath) throws IOException {
		// this directory will contain the indexes
		FSDirectory dir = FSDirectory.open(new File("indexFolder"));
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LATEST, analyzer);
		iwc.setOpenMode(OpenMode.CREATE_OR_APPEND); // CREATE_OR_APPEND prevents
													// from overwriting an index

		writer = new IndexWriter(dir, iwc);
	}

	public int numberOfIndexedDocs(String indexDirectoryPath)
			throws IOException {
		int i = writer.numDocs();
		return i;
	}

	public void deleteAllIndex(String indexDirectoryPath) throws IOException {
		writer.deleteAll();
	}

	public void closeWriter(String indexDirectoryPath) throws IOException {
		writer.close();
	}

	public void indexReuter(String newId, String Date, String Title, String Body)
			throws IOException {

		{

			Document doc = new Document();

			try {
				doc.add(new StringField("newId", newId, Field.Store.YES));
				doc.add(new StringField("date", Date, Field.Store.YES));

				try { // there are some documents without title and /or/ body
					doc.add(new TextField("title", Title, Field.Store.YES));
					doc.add(new TextField("body", Body, Field.Store.YES));

				} catch (IllegalArgumentException e) {
					// TODO the value of title or body is illegal
					/* e.printStackTrace(); */
				} catch (NullPointerException e) {
					// the value of title or body is null
					/* e.printStackTrace(); */
				}

				writer.addDocument(doc);
				writer.commit(); // Commits all changes to the index

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
