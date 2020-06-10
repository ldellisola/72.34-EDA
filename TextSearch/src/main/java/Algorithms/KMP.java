package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMP {
	
	// Complejidad espacial: n
	// Complejidad temporal: n 
	public static int[] nextComputation(char[] query) {
		
		int next[] =new int[query.length];
		
		next[0] = 0;
		int border = 0;
		
		for(int rec = 1 ; rec < query.length ; rec++) {
			while((border > 0) && (query[border]!=query[rec]))
				border = next[border-1];
			if(query[border] == query[rec])
				border++;
			
			next[rec] = border;
		}
		
		return next;
	}
	
	public static  List<Integer>findAll(char[] query, char[]target){
		
		List<Integer> indexes = new ArrayList<Integer>();
		if (query == null || query.length == 0)
			throw new RuntimeException("Bad query string");
		if (target == null || target.length == 0)
			throw new RuntimeException("Bad target string");
		int[] next = nextComputation(query);
		int rec = 0;
		int pquery = 0;
		while (rec < target.length) {
			if (target[rec] == query[pquery]) {
				rec++;
				pquery++;
			}
			if (pquery == query.length) {  // Found!!!
				indexes.add(rec-pquery);
				
				pquery = next[pquery-1];
				
			} else // mismatch?
				if (rec < target.length && target[rec] != query[pquery]) {
					// no machea los i-1
					if (pquery != 0)
						pquery = next[pquery - 1];
					else
						rec++;
				}
		}

		return indexes;
		
	}
	
	public static int indexOf(char[] query, char[] target) {
		if (query == null || query.length == 0)
			throw new RuntimeException("Bad query string");
		if (target == null || target.length == 0)
			throw new RuntimeException("Bad target string");
		int[] next = nextComputation(query);
		int rec = 0;
		int pquery = 0;
		while (rec < target.length) {
			if (target[rec] == query[pquery]) {
				rec++;
				pquery++;
			}
			if (pquery == query.length) {  // Found!!!
				break;
			} else // mismatch?
				if (rec < target.length && target[rec] != query[pquery]) {
					// no machea los i-1
					if (pquery != 0)
						pquery = next[pquery - 1];
					else
						rec++;
				}
		}
		if (pquery == query.length)
			return rec - pquery;
		return -1;
	}
}
