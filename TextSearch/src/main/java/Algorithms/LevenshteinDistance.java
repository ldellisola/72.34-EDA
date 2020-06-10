package Algorithms;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

public class LevenshteinDistance {
	
	
	public int matrix[][];
	int iSize,jSize;
	
	public int getDistance() {
		return matrix[iSize-1][jSize-1];
	}
	
	public String toString() {
		
		StringBuilder bld = new StringBuilder();
		
		for(int i = 0; i < iSize ; i ++ ) {
			for(int j = 0; j < jSize ; j++) {
				
				bld.append('|').append('\t').append(matrix[i][j]).append('\t');
			}
			
			bld.append('|').append('\n');
		}
		
		return bld.toString();
		
	}
	
	public LevenshteinDistance(String a, String b) {
		
		if(a == null || a.isEmpty() || b == null || b.isEmpty()) {
			throw new InvalidParameterException("Invalid Arguments");
		}
		
		// SetUp
		iSize = a.length()+1;
		jSize = b.length()+1;
		
		// Genero una matriz, poniendo una palabra como titulo de las filas y la otra como titulo de las columnas
		
		/*
		 * 
		 * 	   C H A U 
		 * 	 0 1 2 3 4
		 * H 1 
		 * O 2
		 * L 3
		 * A 4
		 * 
		 */
		matrix = new int[iSize][jSize];
		
		
		for(int i = 0 ; i < a.length()+1 ; i ++) {
			matrix[i][0] = i;
		}
		for(int i = 0 ; i < b.length()+1 ; i ++)
		{
			matrix[0][i] = i;
		}
		
		a = a.toUpperCase();
		b = b.toUpperCase();
		
		int values[] = new int[3];
		
		/*
		 * 	   C H A U
		 *   0 1 2 3 4
		 * H 1 1 1 2 3
		 * O 2 2 2 2 3
		 * L 3 3 3 3 3
		 * A 4 4 4 3 4
		 * 
		 */
		
		
		for(int i = 1; i < a.length() + 1 ; i ++ ) {
			for(int j = 1; j < b.length()+1 ; j++) {
				
				// Si los caracteres anteriores son iguales, entonces la distancia puede ser
				// la distancia anterior. Si son distintos es la anterior +1
				values[0] = matrix[i-1][j-1] + (a.charAt(i-1) == b.charAt(j-1)? 0:1);
				// La distancia tambien puede ser la de arriba +1
				values[1] = matrix[i-1][j] + 1;
				// o la de abajo +1
				values[2] = matrix[i][j-1] + 1;
				
				// Elijo el menor valor de los 3
				Arrays.sort(values);
				matrix[i][j] =values[0];				
				
			}
		}
		
		
	}
	
	
	private class LevenshteinNotInitializedException extends RuntimeException{
		
		public LevenshteinNotInitializedException(){
			super("");
		}
		
	}

}
