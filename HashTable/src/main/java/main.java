import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;



public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ClosedHashTable<String, Integer> hash = new ClosedHashTable<String, Integer>(t->(int)t.charAt(0));
		
		hash.insert("HOLA", 1);
		hash.insert("CHAU", 3);
		hash.insert("PAPA", 1);
		hash.insert("HOLUS", 99);
		hash.insert("DEDO", 1);
		hash.insert("CAMARA", 1);
		
		hash.delete("HOLA");
		hash.delete("DEDO");
		
		hash.getValue("HOLUS");
		hash.getValue("CAMARA");

		

	}

}
