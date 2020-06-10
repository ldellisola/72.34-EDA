
public class main {

	public static void main(String[] args) {
		
		Evaluator ev = new Evaluator();
		
		
		//IMathParser parser = new InFijoParser();
		
		String ecuation = "( ( -2 + 7 ) ^ ( -2 - 1 ) ) * 0.4";
		
		System.out.println(ev.evaluateInfija(ecuation));;

	

	
	}

}


/*
 * 2 - 3 ^ -3    devuelve 1.9629

2 ^ 4 ^ 2     devuelve  65536  ( y no 256)
 
3 + 10 * 2 / 1    (toPostfija() da   3  10  2  *  1  /   +    )   y evalúa a 23
 
13  ^ 2 - 5 * 7   (toPostfija() da 13  2  ^ 5 7  * -  ) y evalúa a 134
 
5 ^ 2  ^ 3  -  1   (toPostfija() da  5  2  3  ^  ^ 1 -  ) y evalua a 390624 
 * 
 * */
 