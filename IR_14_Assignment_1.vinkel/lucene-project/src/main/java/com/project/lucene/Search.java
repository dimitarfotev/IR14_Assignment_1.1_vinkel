package com.project.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

public class Search {

	private IndexReader reader;

	public Search(String indexDirectoryPath) throws IOException {

		reader = DirectoryReader
				.open(FSDirectory.open(new File("indexFolder")));

	}

	public void indexSearch(String s) throws IOException, ParseException {

		IndexSearcher searcher = new IndexSearcher(reader);

		Analyzer analyzer = new StandardAnalyzer();

		Query queryParser = new MultiFieldQueryParser(new String[] { "body",
				"title", "date" }, analyzer).parse(s);

		TopDocs topDocs = searcher.search(queryParser, 10);

		ScoreDoc[] hits = topDocs.scoreDocs;
		if (topDocs.totalHits == 0) {
			System.out.println("No relevant documents found.");
		} else {

			int numberOfDocsToPrint=10; //to print the 10 most relevant docs
			System.out.println("Found " + topDocs.totalHits
					+ " relevant documents.");
			if(topDocs.totalHits<=10)
			{
				numberOfDocsToPrint=topDocs.totalHits;
			}
			
			System.out.println("The " + numberOfDocsToPrint + " most relevant documents are:");
			for (int i = 0; i < numberOfDocsToPrint; i++) {

				ScoreDoc dr = hits[i];
				int docId = dr.doc;
				Document d = searcher.doc(docId);
				System.out.println("rank:" + (i + 1) + ". " + "id:"
						+ d.get("newId") + "  " + "title:" + d.get("title")
						+ "  " + "relevance score=" + hits[i].score);
			}

		}
	}
}
