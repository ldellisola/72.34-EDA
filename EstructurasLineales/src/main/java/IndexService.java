
public interface IndexService<T extends Comparable<? super T>> {
	
	int SearchIndex(T key);
	
	T[] Range(T left, T right, Boolean leftClosed, Boolean rightClosed);
	
	

}
