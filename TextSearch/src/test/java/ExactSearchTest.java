import org.junit.jupiter.api.*;

import Algorithms.ExactSearch;

import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;


public class ExactSearchTest {
	
	@Test
	public void InvalidParameters() {
		

		Exception ex = assertThrows(InvalidParameterException.class,()->{
			
			ExactSearch.indexOf("".toCharArray(),"big data".toCharArray());
			
		});
		
		assertTrue(ex.getMessage().contains("Invalid Arguments"));
	}
	
	@Test
	public void StringExist() {
		
		int val = ExactSearch.indexOf("hola".toCharArray(), "la".toCharArray());
		
		assertEquals(val, 2);
	}
	
	@Test
	public void StringDoesntExist() {
		
		int val = ExactSearch.indexOf("hola".toCharArray(), "las".toCharArray());
		
		assertEquals(val, -1);
	}
}
