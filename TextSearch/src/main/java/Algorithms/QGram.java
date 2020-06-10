package Algorithms;


import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.management.RuntimeErrorException;

public class QGram {
	
	private List<String> TriGramA,TrigramB;
	// El largo de cada string
	private int n;
	
	private List<String> GenerateTrigram(String input) {
		List<String> temp = new LinkedList();
		// QGramas basicamente separa a la palabra en tokens de longitud Q
		// Si los tokens no llegan a tener longitud Q, entonces se los rellena con #
		// Un ejemplo de esto es cuando comienza el string, donde la palabra va a se '##L'
		
		// Para calcular la similaridad entre dos conjuntos de tokens se calcula la cantidad de
		// elementos sin repetir dividido la cantidad de elementos con los repetidos. Si 
		// Cuanto mas parecidos sean los strings este numero va a estar mas cerca a 1.
		for(int i = 1-n; i < input.length(); i++) {
			StringBuilder bld = new StringBuilder();
			
			if(i<0) {
				int t = i;
				while(t < 0) {
					bld.append('#');
					t++;
				}
				while(Math.abs(t-i) <n) {
					bld.append(input.charAt(t++));
				}
			}else if(i > input.length() - n) {
				int t = i;
				while(t < input.length()) {
					bld.append(input.charAt(t++));
				}
				while(Math.abs(t++-i) <n) {
					bld.append('#');
				}
			}
			else {
				int t = i;
				while(Math.abs(t-i) <n) {
					bld.append(input.charAt(t++));
				}
			}
			
			temp.add(bld.toString());

			
		}
		
		
		
		return temp;
		
	}
	
	
	private int GetNonIntesectors() {
		Dictionary<String, Integer> recorder = new Hashtable();
		int nonIntersectors = 0;
		
		List<String> selected = (TriGramA.size() > TrigramB.size()? TriGramA : TrigramB);
		List<String> notSelected = (TriGramA.size() > TrigramB.size()? TrigramB : TriGramA);
		

		for (String str : selected) {
			
			int rep = Collections.frequency(notSelected, str);
			
			
			if(rep == 0) {
				nonIntersectors++;
			}else {
				Integer recordedValue = recorder.get(str);
				
				if(recordedValue == null) {
					recorder.put(str, 1);
				}else if(recordedValue < rep ) {
					recorder.put(str,recordedValue+1 );
				}
				else {
					nonIntersectors++;
				}
			}
		}
		
		return nonIntersectors;
	}
	
	public QGram(String a, String b,int n) throws Exception {
		
		if(a == null || a == "" || b == null || b == "" || n <2) {
			throw new InvalidParameterException("Invalid Argument");
		}
		
		this.n = n;
		
		TriGramA = GenerateTrigram(a);
		TrigramB = GenerateTrigram(b);
		

	}
	
public QGram(String a, String b) throws Exception {
		
	if(a == null || a.isEmpty()|| b == null || b.isEmpty()) {
		throw new InvalidParameterException("Invalid Argument");
	}
		
		this.n = 3;
		
		TriGramA = GenerateTrigram(a);
		TrigramB = GenerateTrigram(b);
		

	}

	public Double getSimilarity() {
		Double a= (double) (TriGramA.size() + TrigramB.size() - GetNonIntesectors());
		Double b = (double)(TriGramA.size() + TrigramB.size());
		return Double.valueOf(a /b );

	}
	
	public String toString() {
		StringBuilder bld = new StringBuilder();
		
		bld.append("TriGram A: ");
		for (String str : TriGramA) {
			bld.append("- ").append(str).append(" ");
		}
		
		bld.append("-");

		
		
		bld.append("\n TriGram B: ");
		for (String str : TrigramB) {
			bld.append("- ").append(str).append(" ");;
		}
		
		bld.append("-");
		
		
		return bld.toString();
		
	}
}







