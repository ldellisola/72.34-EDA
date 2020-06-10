package Entrega;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

import Algorithms.*;

public class ItemFind implements Comparable<ItemFind>{
	public String name;
	private List<AlgorithmValue> scores = new LinkedList<ItemFind.AlgorithmValue>();
	
	public ItemFind(String name, String searchedName) throws Exception {
		
		if(name == null || name.isEmpty()|| searchedName == null || searchedName.isEmpty()) {
			throw new InvalidParameterException("Invalid Argument");
		}
		
		scores.add(new AlgorithmValue(1/(double)(1+ new LevenshteinDistance(name, searchedName).getDistance())
										, "Levenshtein"));
		scores.add(new AlgorithmValue(Soundex.GetSimilarity(name, searchedName,true)
										, "Soundex"));
		scores.add(new AlgorithmValue(Metaphone.GetSimilarity(name, searchedName)
				, "Metaphone"));
		
		scores.add(new AlgorithmValue(new QGram(name,searchedName).getSimilarity()
				, "QGram"));
		
		this.name = name;
		
		scores.sort((o,t)->t.score.compareTo(o.score));

	
	}
	
	public AlgorithmValue GetScore() {
		return scores.get(0);
	}
	
	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		
		bld.append("Score: ").append(String.format("%.03f", this.GetScore().score)).append('\t');
		bld.append(" Name: ").append(this.name).append("\t\t");
		bld.append(" Algorithm: ").append(this.GetScore().name);
		
		
		
		return bld.toString();
	}
	
	
	class AlgorithmValue {
		public Double score;
		public String name;
		
		public AlgorithmValue(double d, String name) {
			this.score = d;
			this.name = name;
		}
	}


	@Override
	public int compareTo(ItemFind o) {
		return this.GetScore().score.compareTo(o.GetScore().score);
	}
}