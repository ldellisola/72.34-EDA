import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PostfijoParser implements IMathParser {
	
	private Stack<Float> stack = new Stack<Float>();

	public Float ParseAndResolve(String ecuation) {
		
		List<String> elements = Arrays.asList(ecuation.split(" "));
		
		
		
		for (String element : elements) {
			
			Float number = 0f;
			boolean isNumber = true;
			try {
				number = Float.valueOf(element);
			}catch(NumberFormatException e) {
				isNumber = false;
			}
			
			
			if(isNumber) {
				stack.push(number);
			}else {
				
				Float secondElement = stack.pop();
				Float firstElement = stack.pop();
				
				switch (element) {
				case "+":
					stack.push( firstElement + secondElement);
					break;
				case "-":
					stack.push(firstElement - secondElement);
					break;
				case "*":
					stack.push(firstElement * secondElement);
					break;
				case "/":
					stack.push(firstElement / secondElement);
					break;
				case "^":
					stack.push((float)Math.pow(firstElement, secondElement));
					break;
				}
			}			
		}
		
		
		return stack.pop();	
	}
}
