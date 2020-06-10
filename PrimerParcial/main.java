

public class main {

	public static void main(String[] args) {
		
		Evaluator ev = new Evaluator();
		
		
		//IMathParser parser = new InFijoParser();
		
		String ecuation = "3 + 10 * 2 / 1";
		
		System.out.println(ev.evaluateInfija(ecuation));;

	

	
	}

}