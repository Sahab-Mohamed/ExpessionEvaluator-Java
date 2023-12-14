package stacks;

import exceptionPackage.StackOverflowException;
import exceptionPackage.StackUnderflowException;

public class UnboundedStack<T> implements StackInterface<T> {

    private StackNode<T> top;

    @Override
    public void push(T element) throws StackOverflowException {
        StackNode<T> newNode = new StackNode<>(element);

        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    @Override
    public void pop() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException();
        } else {
            top = top.next;
        }
    }

    @Override
    public T top() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException();
        } else {
            return top.getData();
        }
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public boolean isFull() {
        return false; // Since it's an unbounded stack, it's never full
    }
}
