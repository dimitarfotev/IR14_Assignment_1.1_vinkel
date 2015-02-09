package com.project.lucene;

import com.project.lucene.domain.LEWIS;
import com.project.lucene.domain.REUTERS;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.queryparser.classic.ParseException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;



public class XmlLucene {

	public static void main(String[] args) throws java.text.ParseException {
		Scanner scr = new Scanner(System.in);
		File file = new File("reut2-000.xml"); // importing the xml file. The
												// file is placed in the project
												// folder. If you want to use
												// another file, copy it to the
												// project folder and write the
												// name /for example
												// "ExampleXMlFIle" /

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(LEWIS.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			LEWIS lewis = (LEWIS) jaxbUnmarshaller.unmarshal(file);

			List<REUTERS> reuters = lewis.getREUTERS();

			Indexer indexer = new Indexer("indexFolder");

			int numberOfIndex = indexer.numberOfIndexedDocs("indexFolder");
			if (numberOfIndex == 0) // nothing indexed
			{
				System.out.println("No existing index found. Indexing...");
				for (REUTERS reuter : reuters) {

					// getting values of current REUTERS node
					String newId = reuter.getNEWID();
					Date data = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss.S",
							Locale.ENGLISH).parse(reuter.getDATE()); // parsing
																		// the
																		// DATE
																		// and
																		// converting
																		// it to
																		// data
					String Date = DateTools.dateToString(data, Resolution.DAY); // converting
																				// date
																				// to
																				// string
																				// with
																				// accuracity
																				// to
																				// day
					String Title = reuter.getTEXT().getTITLE();
					String Body = reuter.getTEXT().getBODY();
					indexer.indexReuter(newId, Date, Title, Body); // calls
																	// indexReuter
																	// method
																	// from
																	// Indexer

				}
				indexer.closeWriter("indexFolder");
				System.out.println("Indexing done!");
			} else {
				System.out
						.println("Do you want to create an new index or to use the existing? Type y and press Enter to create a new index. Type something else and press enter to use the existing indexing. ");
				String answer = scr.next();

				if (answer.equals("y")) {
					indexer.deleteAllIndex("indexFolder"); // deletes existing
															// indexes
					System.out.println("Indexing...");
					for (REUTERS reuter : reuters) {
						// getting values of current REUTERS node
						String newId = reuter.getNEWID();
						Date data = new SimpleDateFormat(
								"dd-MMM-yyyy hh:mm:ss.S", Locale.ENGLISH)
								.parse(reuter.getDATE()); // parsing the DATE
															// and converting it
															// to data
						String Date = DateTools.dateToString(data,
								Resolution.DAY); // converts date to string
													// with type yyyyMMdd
						String Title = reuter.getTEXT().getTITLE();
						String Body = reuter.getTEXT().getBODY();
						indexer.indexReuter(newId, Date, Title, Body); // calls
																		// indexReuter
																		// method
																		// from
																		// Indexer

					}

					indexer.closeWriter("indexFolder");
					System.out.println("Indexing done!");
				}
			}
			String s= "." ; //random value, string must not be null here 
			while(!s.equals("*end*"))
			{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Search currentSearch;
		try {
			currentSearch = new Search("indexFolder");
			System.out.println("Enter the search query  (type \"*end*\" to stop ):");
			 s = br.readLine();
			try {
				if(!s.equals("*end*") && !s.isEmpty())
				{
				currentSearch.indexSearch(s);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
