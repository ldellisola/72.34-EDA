

public class Main {

	public static void main(String[] args) {
		
		Timer timer = new Timer();
		
		for(int i = 0 ;i < 1000000 ; i++) {
			System.out.print("HOLA\n");
		}
		
		timer.stop();
		
		
		System.out.print(timer.toString());
		

	}

}
