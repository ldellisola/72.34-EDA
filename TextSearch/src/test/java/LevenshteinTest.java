import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.*;

import Algorithms.LevenshteinDistance;


public class LevenshteinTest {
	
	@Test
	public void CorrectLevenshteinDistance() {
		LevenshteinDistance lev = new LevenshteinDistance("bigdata","big data");
		
		assertEquals(1,lev.getDistance());
	}
	
	@Test
	public void IncorrectLevenshteinDistance() {
		LevenshteinDistance lev = new LevenshteinDistance("bigdata","big data");
		
		assertNotEquals(123,lev.getDistance());

	}
	
	@Test
	public void OneSideEmptyLevenshteinDistance() {
		
		Exception ex = assertThrows(InvalidParameterException.class,()->{
			
			LevenshteinDistance lev = new LevenshteinDistance("","big data");
			
		});
		
		assertTrue(ex.getMessage().contains("Invalid Arguments"));
		
		
		
	}

}
