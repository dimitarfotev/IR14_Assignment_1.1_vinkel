# IR14_Assignment_1.1_vinkel
SearchingInXmlFile

Maven is used for project building.

Follow these steps to start the project:

1.Start the command promt 
2.Enter the directory of the projects "pom" file . Use the cmd command pushd and after that enter the directory of the pom (e.g. pushd C:\Users\Dimitar\Desktop\lucene-project\lucene-project)
3.To execute the project enter the following command -> mvn compile exec:java

 ------------------------------------------------------------------------

Using the project

Our program has 6 classes:  Indexer,LEWIS,REUTERS,Search,TEXT,XmlLucene, where XmlLucene the main class is.
The classes LEWIS,REUTERS and TEXT are used for parsing of the xml document with JAXB*. Their names are written on the same way, as the nodes in the mldocument, that we need to parse (only with capital letters), to make the understanding easier. Each ot fhe classes gets the value of the current node and converts it to a string. We need not one, but three vclasses, because our xml document has a tree structure. 
*JAXB(Java Architecture for XML Binding) allows Java developers to map Java classes to XML representations. JAXB provides two main features: the ability to marshal Java objects into XML and the inverse, i.e. to unmarshal** XML back into Java objects.
**The Unmarshaller class governs the process of deserializing XML data into newly created Java content trees, optionally validating the XML data as it is unmarshalled. It provides an overloading of unmarshal methods for many different input kinds.

The Indexer class is used for the indexing of the parsed xml document. The first thing, that we need to index a document, is to create an object Indexer and IndexWriter /included in Lucene/. To  construct it, we need a directory, where the index documents are going to be written, in our case indexFolder. We need also an IndexWriterConfig- a lucene class, that holds all the configuration that is used to create a IndexWriter. It need an Analyzer- we use  Lucene‘s StandardAnalyzer, because it gives us enough good analyzing of the document, because we don‘t need any special analyzing options.
The Indexer class has 4 methods: numberOfIndexedDocs,deleteAllIndex,closeWriter i indexReuter.
numberOfIndexedDocs is used to give us the count of the indexed documents and allows us to tell the user if there is created indexing and he can choose to use it or to create an new indexing, or when there is no idexing, the program automatically creates a new one.

 deleteAllIndex – a metod, that is used to delete all indexed documents. It is callde when tehre is an existing indexing, but the user wants to craeate a new one.

closeWriter – This method is called when the indexing is finished to close the IndexWriter.

indexReuter – This method receives the data, that we need to index: newID,Date,Title and Body. It creates a new Lucene document with 4 fields- for newID,Date,Title and Body. It writes and stores all of the sent data. Becaouse there are some documents, that not contain Title or Body, they are surrounded with a try-catch statement, that catches a  NullPointerException.


The class Search is used for searching in the indexed document. It has the same structure as the class Indexer. We need to create an IndexReader and an new object Seacrh, witch opens the indexWriter directory.
The only method in this class is called indexSearch. When called, it takes the seach string  (in our program s ). 
New index searcher is created , that searches in the already read from the indexReader data. Crucial part of our search is the right parsing of the search text and its transformation in query. To achieve that we use StandartAnalyzer and for multifield search MultiFieldQueryParser. In our project we search in 3 fields - body , title and date.
To get the top 10 relevant documents we use TopDocs. We create topDocs object and receives the value of the search. After that we need to get the number of relevant documents. To achieve that we need to use the scoreDocs method which is part of TopDocs. The data is saved in ScoreDocs[] hits. Then a check is made to find how many relevent documents are found. There are 3 possible cases for the count of the possible found files - 
1. no files are found  
  topDocs.totalHits=0, totalHits gives the number of top documents.  A message informes the user that there are no files found that are relevant to the query.
2. relevant documents are found , but their number may be less then 10. To get the exact number we create the numberOfDocsToPrint variable with value equal to 10.If the number of relevant documents is less then 10 numberOfDocsToPrint get new value which equals that number. After that for-loop is used to show the relevant documents. The number of loops is equal to numberOfDocToPrint. Rank , ID , Title and relevance score are being printed in the console. The relevance scori is found with the ".score" method that is part of ScoreDoc class.

Class XmlLucene

Hier take place our main function.First we import the XML file. With the help of JAXB we unmarshall our file and now its ready for indexing. A new indexer is created. A check if the file is indexed is made. In case that it's indexed the user is asked does he want a new indexing. If a new indexing will be done , and such already exist , before the start of the new indexing the old one is deleted with the deleteAllIndex method.
 In case that there is no indexing such is made automaticly.
To make a search by date possible after the parsing "data" is transformed into string with "resolution day". NewId , title and body are parsed automaticly and send for indexing.
After the indexing is done the closeWriter method is called.
Now the file is indexed and we can enter a search query. The query is entered from the console and send to the search method , which take place in the Search class.

