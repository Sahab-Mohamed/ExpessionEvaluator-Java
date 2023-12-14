package expressionEvaluatorPackage;
import java.util.Scanner;
import exceptionPackage.StackOverflowException;
import exceptionPackage.StackUnderflowException;
import stacks.UnboundedStack;

public class ExpressionEvaluatorClass <T>{
	
	 // Function to check if one operator has the same or greater precedence than another.
    public static boolean hasSameOrGreaterPrecedence(String op1, String op2) {
        
        int precedenceOp1 = getOperatorPrecedence(op1);
        int precedenceOp2 = getOperatorPrecedence(op2);

        return precedenceOp1 >= precedenceOp2;
    }

    // Function to assign precedence values to operators.
    public static int getOperatorPrecedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
                
            case "%":
            	return 2;
            case "*":
            case "/":
            
                return 3;
           default:
           		return 0;
        }
    }
    public static boolean isInteger(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


	
    public static void main(String[] args) throws StackOverflowException, StackUnderflowException {
      try (Scanner scanner = new Scanner(System.in)) {
		while (true) {
			  System.out.println("Enter the mathematical expression seperated by single spaces only... \nAllowed tokens are: \n+ addition\r\n"
					+ "- subtraction\r\n"
					+ "* multiplication\r\n"
					+ "/ division\r\n"
					+ "% modulus\n"
					+ "1 - 9 (Integers only)\n" 
					+ "( , ) ");
		      String input = scanner.nextLine();

		      try {
		          int result = evaluateExpression(input);
		          System.out.println("Result: " + result);
		      } catch (IllegalArgumentException e) {
		          System.out.println("Invalid input: " + e.getMessage());
		      }

		      System.out.print("Do you want to evaluate another expression? (yes/no): ");
		      String choice = scanner.nextLine().toLowerCase();
		      if (!choice.equals("yes") && !choice.equals("y")) {
		          break;
		      }
		  }
	}
  }
		
		
    public static int evaluateExpression(String input) throws StackOverflowException, StackUnderflowException {
        String[] parts = input.split(" ");
        UnboundedStack<Integer> valStack = new UnboundedStack<Integer>();
        UnboundedStack<String> opStack = new UnboundedStack<String>();

        for (int index = 0; index < parts.length; index++) {
            if (isInteger(parts[index])) {
                // push it into the valStack
                try {
                    valStack.push(Integer.parseInt(parts[index]));
                    
                } catch (NumberFormatException e) {
                    System.out.println("Invalid entry !");
                    return 0;
                }
            } else if (parts[index].equals("(")) {
                // push it into the opStack
                opStack.push(parts[index]);
            } else if (parts[index].equals(")")) {
                try {
                    
                    while (!opStack.isEmpty() && !opStack.top().equals("(")) { // Fix here
                    	
                        String operator = opStack.top(); // Get the value from the Node
                        opStack.pop();
                        
                        int opr1 = valStack.top();
                        valStack.pop();
                       
                        int opr2 = valStack.top();
                        valStack.pop();
                        
                        // the order: val = opr2 operator op1
                        int val = 10;
                        
                        switch (operator) {
                            case "+":
                                val = opr2 + opr1;
                                break;
                            case "-":
                                val = opr2 - opr1;
                                break;
                            case "*":
                                val = opr2 * opr1;
                                break;
                            case "/":
                                val = opr2 / opr1;
                                break;
                            case "%":
                                val = opr2 % opr1;
                                break;
                        }
                        
                        valStack.push(val);
                    }
                    

                    opStack.pop();

                } catch (StackUnderflowException e) {
                    throw new StackUnderflowException();
                }
            } else if (parts[index].equals("+") || parts[index].equals("-") || parts[index].equals("/") || parts[index].equals("*") || parts[index].equals("%")) {
                while (!opStack.isEmpty() && hasSameOrGreaterPrecedence(opStack.top(), parts[index])) {
                	// Fix here
                    int val = 0;
                    String operator = opStack.top(); // Get the value from the Node
                    opStack.pop();
                    int opr1 = valStack.top();
                    valStack.pop();
                    int opr2 = valStack.top();
                    valStack.pop();
                    // the order: val = opr2 operator op1
                 // Adjust the subtraction case
                    

                    switch (operator) {
                        case "+":
                            val = opr2 + opr1;
                            break;
                        case "-":
                            val = opr2 - opr1;
                            break;
                        case "*":
                            val = opr2 * opr1;
                            break;
                        case "/":
                            val = opr2 / opr1;
                            break;
                        case "%":
                            val = opr2 % opr1;
                            break;
                    }
                    valStack.push(val);
                }
                opStack.push(parts[index]);
            } else {
                System.out.println("Invalid entry !");
                return 0;
                
            }
        }
        
        while (!opStack.isEmpty()) {
            int val = 0;
            String operator = opStack.top(); // Get the value from the Node
            opStack.pop();
            
            int opr1 = valStack.top();
            valStack.pop();
            
            int opr2 = valStack.top();
            valStack.pop();
            
            // the order: val = opr2 operator op1

            switch (operator) {
                case "+":
                    val = opr2 + opr1;
                    break;
                case "-":
                    val = opr2 - opr1;
                    break;
                case "*":
                    val = opr2 * opr1;
                    break;
                case "/":
                    val = opr2 / opr1;
                    break;
                case "%":
                    val = opr2 % opr1;
                    break;
            }
            valStack.push(val);
        }

        return valStack.top();
    }

}


	
