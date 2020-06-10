import java.util.Arrays;
import java.util.function.Function;

public class HashTable<K,V> {
	
	
	protected int initialLookupSize= 10;
	protected int maxLoadFactor = (int) ((int) initialLookupSize/1.5);
	protected int loadFactor = 0;
	
	// estática. No crece. Espacio suficiente...
	protected Node<K,V>[] LookUp= new Node[initialLookupSize];
	
	protected Function<? super K, Integer> prehash;
	
	public HashTable( Function<? super K, Integer> mappingFn)
	{
		prehash= mappingFn;
	}
	
	// ajuste al tamaño de la tabla
	 protected int hash(K key) 
	 {
		 if (key == null)
			 throw new RuntimeException("No key provided");
		 
		 return prehash.apply(key) % LookUp.length;
	}
	
	 
	public V getValue(K key)
	{
		Node<K, V> entry = get(key);
		if (entry == null)
			return null;
		
		return entry.value;
	}

	protected Node<K,V> get(K key)
	{
		return LookUp[  hash( key) ];
	}
	
	// insert = update
	public void insert(K key, V value)
	{
		loadFactor++;
		if(loadFactor >= maxLoadFactor) {
			expandTable();
		}
		LookUp[  hash( key) ] = new Node(key,value);
		
	}
	

	public void delete(K key)
	{
		loadFactor--;
		LookUp[  hash( key) ] = null;
	}

	public void dump()
	{
		for(int rec= 0; rec < LookUp.length; rec++)
			if (LookUp[rec] == null)
				System.out.println(String.format("slot %d is empty", rec));
			else
				System.out.println(String.format("slot %d contains %s", rec, LookUp[rec]));
	}
	
	protected void expandTable() {
		Node<K, V>[] nodes = LookUp;
		initialLookupSize = initialLookupSize * 2;
		maxLoadFactor = (int) ((int) initialLookupSize/1.5);
		loadFactor = 0;
		
		LookUp = new Node[initialLookupSize];
		
		for(int i = 0 ; i < nodes.length ; i++) {
			if(nodes[i] != null)
				insert(nodes[i].key, nodes[i].value);
		}
		
	}

	
	
	static class Node<K,V> 
	{
		final K key;
		V value;
		public boolean softDeleted;

		Node(K theKey, V theValue)
		{
			key= theKey;
			value= theValue;
			softDeleted = false;
		}
		
		
		public String toString()
		{
			return String.format("key=%s, value=%s", key, value );
		}
	}

}
