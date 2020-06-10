import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.*;

import Algorithms.Soundex;



public class SoundexTest {
	private class parameters{
		public String text;
		public String encoding;
		public boolean processed;
		
		public parameters(String text,String encoding,boolean processed) {
			this.text = text;
			this.encoding = encoding;
			this.processed = processed;
		}
	}

	private List<parameters> ValidParameters() {
		  
		  List<parameters> ret = new LinkedList<parameters>();		  
		  
		  ret.add(new parameters("ROBERT","R163",true));
		  ret.add(new parameters("Robert","R163",false));
		  ret.add(new parameters("RUBIN","R150",true));
		  ret.add(new parameters("Rubin","R150",false));
		  ret.add(new parameters("ASHCROFT","A261",true));
		  ret.add(new parameters("Ashcroft","A261",false));
		  ret.add(new parameters("TYMCZAK","T522",true));
		  ret.add(new parameters("tymczak","T522",false));
		  ret.add(new parameters("PFISTER","P236",true));
		  ret.add(new parameters("pfister","P236",false));
		  ret.add(new parameters("HONEYMAN","H555",true));
		  ret.add(new parameters("honeyman","H555",false));
		  
		  return ret;
	}
	
	private List<parameters> InValidParameters() {
		  
		  List<parameters> ret = new LinkedList<parameters>();		  
		  
		  ret.add(new parameters("ROrereBrERT","R163",true));
		  ret.add(new parameters("Rqewqwobeert","R163",false));
		  ret.add(new parameters("RUqeqweBIwN","R150",true));
		  ret.add(new parameters("Rqweqweuzbin","R150",false));
		  ret.add(new parameters("AqweqSHCROFT","A161",true));
		  ret.add(new parameters("Aqweqeshfcroft","A261",false));
		  ret.add(new parameters("TrdsaYMCvZAK","T522",true));
		  ret.add(new parameters("tdasdaymcttfzak","T522",false));
		  ret.add(new parameters("PasdaFISttTER","P236",true));
		  ret.add(new parameters("pdasdafisttter","P236",false));
		  ret.add(new parameters("HasdasdONEttYMAN","H555",true));
		  ret.add(new parameters("hasdasonettyman","H555",false));
		  
		  return ret;
	}
	
	
	

	@Test
	public void ValidSoundexTest() throws Exception {
		
		for (parameters param : ValidParameters()) {
			System.out.println("Analizing text: " + param.text);
			String encoding = Soundex.GetEncoding(param.text, param.processed);
			System.out.println("\tValid Encoding: " + param.encoding + " - Obtained Encoding: " + encoding);
			assertEquals(param.encoding, encoding);
		}
		
		
	}
		
	@Test
	public void InValidSoundexTest() throws Exception {
		
		for (parameters param : InValidParameters()) {
			System.out.println("Analizing text: " + param.text);
			String encoding = Soundex.GetEncoding(param.text, param.processed);
			System.out.println("\tINValid Encoding: " + param.encoding + " - Obtained Encoding: " + encoding);
			assertNotEquals(param.encoding, encoding);
		}
	}
	
	@Test
	public void EncodingInvaludInput() {
		Exception ex = assertThrows(InvalidParameterException.class,()->{
			
			Soundex.GetEncoding(null,null);
			
		});
		
		assertTrue(ex.getMessage().contains("Invalid Arguments"));
		
	}
	

	@Test
	public void SimilarityInvaludInput() {
		Exception ex = assertThrows(InvalidParameterException.class,()->{
			
			Soundex.GetSimilarity(null,null, null);
			
		});
		
		assertTrue(ex.getMessage().contains("Invalid Arguments"));
		
	}
	
	@Test
	public void SoundexSimilarityTest() throws Exception {
		List<String> args = new LinkedList<String>();
		args.add("threshold");
		args.add("hold");
		args.add("phone");
		args.add("foun");
		
		List<Double> results = new LinkedList<Double>();
		results.add(0.0);
		results.add(0.75);
		
		
		for(int i = 0; i < args.size() ; i+=2) {
			System.out.println("Similarity between: " + args.get(i) + " and: " + args.get(i+1) );
			Double sim = Soundex.GetSimilarity(args.get(i), args.get(i+1), false);
			System.out.println("\t Sim: " + sim);
			assertEquals(results.get(i/2),sim );
			
		}
		
		
	}
	
	
	
	
	
	

}
