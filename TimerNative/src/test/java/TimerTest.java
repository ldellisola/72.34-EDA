import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import Utils.Timer;

class TimerTest {
	
	private int repeatValue = 100;

	@Test
	void RegularTimer() throws InterruptedException{
		
		System.out.println("RegularTimer");
		Utils.Timer timer = new Timer();
				
		Thread.sleep(repeatValue);

		timer.stop();

		System.out.println(timer.toString());
	}
	
	@Test
	void CustomStartTimer()throws InterruptedException {
		System.out.println("CustomStartTimer");
		Utils.Timer timer = new Timer(100);
		
		Thread.sleep(repeatValue);

		timer.stop();

		System.out.println(timer.toString());
	}	
	@Test
	void CustomEndTimer() throws InterruptedException {
		System.out.println("CustomEndTimer");
		Utils.Timer timer = new Timer();
		
		Thread.sleep(repeatValue);

		timer.stop(200);
		
		System.out.println(timer.toString());
	}	
	@Test
	void CustomStartAndEndTimer() throws InterruptedException {
		System.out.println("CustomStartAndEndTimer");
		Utils.Timer timer = new Timer(10);
		
		Thread.sleep(repeatValue);

		
		timer.stop(110);

		System.out.println(timer.toString());
	}	
	
	@BeforeAll
	static void initAll() {
	   System.out.println("Empiezan los tests");
	}

	@BeforeEach
	void init() {
	   System.out.println("Empieza un test");
	}

	@AfterEach
	void tearDown() {
	   System.out.println("Termina un test");
	}

	@AfterAll
	static void tearDownAll() {
	   System.out.println("Terminaron todos los tests");
	}


}
