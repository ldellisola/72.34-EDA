package Core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class Search {
	
	private static IndexSearcher indexSearcher ;
	
	public static void searchIndex(String field, String query) throws FileNotFoundException, IOException, ParseException 	{
		
		QueryParser parser = new QueryParser(field, new StandardAnalyzer());

		Query aQuery= parser.parse(query);
		
			
		TopDocs topDocs = indexSearcher.search(aQuery,100);
		
		System.out.println(String.format("Searching '%s' in field %s", query, field));
		System.out.println("Number of hits: " + topDocs.totalHits);
		ScoreDoc[] resultSet = topDocs.scoreDocs;
		for(ScoreDoc scoredoc: resultSet){
			System.out.println("---");

			// mostrar resultados
		    Document doc = indexSearcher.doc(scoredoc.doc);
		    System.out.println("owner: "+doc.getField("owner").stringValue());
		    System.out.println("path: "+ doc.getField("path").stringValue());
			System.out.println("content: " + doc.getField("content"));
		}
		
		
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// donde está el índice?
		String databaseDir = Utils.getDir();

		IndexReader indexReader = DirectoryReader.open(
				FSDirectory.open(Paths.get(databaseDir + "/index/")));
		indexSearcher = new IndexSearcher(indexReader);
		
		// Busqueda por frase
//		searchIndex("content", "\"ver otro\"");
//		System.out.println("-.-.-.-.-.-.-");
//		searchIndex("content", "\"otro ver\"");
//		System.out.println("-.-.-.-.-.-.-");
//
//		searchIndex("content", "\"zver otro\"");
		
		// Busqueda por rango
		
//		searchIndex("content", "{jose TO josefa]");
//		System.out.println("-.-.-.-.-.-.-");
//
//		searchIndex("content", "[josefa TO jose}");
//		System.out.println("-.-.-.-.-.-.-");
//
//		searchIndex("content", "[jose TO jw}");
//		System.out.println("-.-.-.-.-.-.-");
//
//		searchIndex("content", "[jose TO jw]");
//		System.out.println("-.-.-.-.-.-.-");
		
		// Por wildcard

//		searchIndex("content", "a*s");
		
		// Por Expresiones Booleanas

//		searchIndex("content", "alumno OR datos");
//		System.out.println("-.-.-.-.-.-.-");
//		searchIndex("content", "alumno AND datos");

//		Por FuzzySearch
		
		searchIndex("content", "josefina~2");
		System.out.println("-.-.-.-.-.-.-");
		searchIndex("content", "josefina~");
		System.out.println("-.-.-.-.-.-.-");
		searchIndex("content", "josefina~1");




		}
	

}