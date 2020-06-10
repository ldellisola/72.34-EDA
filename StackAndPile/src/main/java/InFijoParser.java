import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class InFijoParser implements IMathParser {

	private Boolean[][] matrix = new Boolean[6][7];
	private final int Plus = 0;
	private final int Minus = 1;
	private final int Product = 2;
	private final int Division = 3;
	private final int Power = 4;
	private final int Open = 5;
	private final int Close = 6;
	
	
	public InFijoParser() {
		matrix[Plus][Plus] = false;
		matrix[Plus][Minus] = false;
		matrix[Plus][Product] = false;
		matrix[Plus][Division] = false;
		matrix[Plus][Open] = false;
		matrix[Plus][Close] = true;
		
		matrix[Minus][Plus] = false;
		matrix[Minus][Minus] = false;
		matrix[Minus][Product] = false;
		matrix[Minus][Division] = false;
		matrix[Minus][Power] = false;
		matrix[Minus][Open] = false;
		matrix[Minus][Close] = true;
		
		matrix[Product][Plus] = true;
		matrix[Product][Minus] = true;
		matrix[Product][Product] = false;
		matrix[Product][Division] = false;
		matrix[Product][Power] = false;
		matrix[Product][Open] = false;
		matrix[Product][Close] = true;
		
		matrix[Division][Plus] = true;
		matrix[Division][Minus] = true;
		matrix[Division][Product] = false;
		matrix[Division][Division] = false;
		matrix[Division][Power] = false;
		matrix[Division][Open] = false;
		matrix[Division][Close] = true;
		
		matrix[Power][Plus] = true;
		matrix[Power][Minus] = true;
		matrix[Power][Product] = true;
		matrix[Power][Division] = true;
		matrix[Power][Power] = false;
		matrix[Power][Open] = false;
		matrix[Power][Close] = true;
		
		matrix[Open][Plus] = false;
		matrix[Open][Minus] = false;
		matrix[Open][Product] = false;
		matrix[Open][Division] = false;
		matrix[Open][Power] = false;
		matrix[Open][Open] = false;
		matrix[Open][Close] = false;
		
		
		
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
	public Float ParseAndResolve(String ecuation) {
		
		return new PostfijoParser().ParseAndResolve(ConvertToPostFija(ecuation));
		
		
		
	}
	
	private String ConvertToPostFija(String ecuacion) {
		Stack<String> stack = new Stack<String>();
		
		List<String> elements = Arrays.asList(ecuacion.split(" "));
		
		String postfijo = "";
		
		String currentOP;
		
		for (String element : elements)  {
			
			Float number = 0f;
			boolean isNumber = true;
			try {
				number = Float.valueOf(element);
			}catch(NumberFormatException e) {
				isNumber = false;
			}
			
			if(!isNumber)
			{
				currentOP = element;
				if(stack.empty())
					stack.push(currentOP);
				else {
					
					while(!stack.isEmpty() && stack.peek() != ")" && HasPrecedence(stack.peek(), currentOP)) {
						postfijo += stack.pop() + " ";
					}
					
					stack.push(currentOP);
				}
			}
			else {
				postfijo += element + " ";
			}
			
		}
		
		while(!stack.isEmpty())
		{
			postfijo += stack.pop() + " ";

		}
		
		return postfijo;		
	}
	
	private Boolean HasPrecedence(String operator1, String operator2) {
		int op1 = -1, op2 = -1;
		
		switch (operator1) {
		case "+": op1 = 0; break;
		case "-": op1 = 1; break;
		case "*": op1 = 2; break;
		case "/": op1 = 3; break;
		case "^": op1 = 4; break;
		case "(": op1 = 5; break;
		case ")": op1 = 6; break;
		}
		
		switch (operator2) {
		case "+": op2 = 0; break;
		case "-": op2 = 1; break;
		case "*": op2 = 2; break;
		case "/": op2 = 3; break;
		case "^": op2 = 4; break;
		case "(": op2 = 5; break;
		case ")": op2 = 6; break;

		}
		
		return matrix[op1][op2];
		
	}
	


}












