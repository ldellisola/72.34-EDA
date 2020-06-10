import java.util.function.Function;


public class ClosedHashTable<K,V> extends HashTable<K,V> {
	
	public ClosedHashTable(Function<? super K, Integer> mappingFn) {
		super(mappingFn);
		// TODO Auto-generated constructor stub
	}

	protected Node<K,V> get(K key)
	{
		int hash = hash(key);
		
		while(LookUp[hash % initialLookupSize] != null && !LookUp[hash % initialLookupSize].key.equals(key) ) {
			hash++;
		}
		
		return LookUp[hash];
	}
	
	// insert = update
	public void insert(K key, V value)
	{
		if(loadFactor >= maxLoadFactor) {
			expandTable();
		}
		loadFactor++;

		
		int hash = hash(key);
		
		while(LookUp[hash % initialLookupSize] != null && !LookUp[hash % initialLookupSize].softDeleted ) {
			hash++;
		}
		
		LookUp[hash] = new Node(key,value);
		
	}
	

	public void delete(K key)
	{
		loadFactor--;
		
		int hash = hash(key);
		
		while(LookUp[hash % initialLookupSize] != null && !LookUp[hash % initialLookupSize].key.equals(key) ) {
			hash++;
		}
		Node next  = LookUp[(hash + 1) % initialLookupSize];
		
		if (next == null){
			LookUp[hash] = null; 
		}
		else {
			LookUp[hash].softDeleted = true;
		}
	}

}
