package exceptionPackage;

@SuppressWarnings("serial")
public class StackUnderflowException extends Exception {

	public StackUnderflowException(){
		
		super("You have reached the bottom of the Stack!");
	}
}
