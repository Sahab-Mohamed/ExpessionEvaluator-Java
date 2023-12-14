package stacks;


public class StackNode<T> {
	
	    private T data;
	    //StackNode<T> prev; not used in implementing a stack
	    StackNode <T> next; 
	    public StackNode(T element){
	    	data = element;
	    }
	    
	    public T getData() {
	    	
	    	return data;
	    }
	  }

