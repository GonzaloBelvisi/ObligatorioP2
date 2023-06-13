package uy.edu.um.prog2.adt.Stack;

public class MyStackImpl<T> implements MyStack<T> {

    private NodeStack<T> top;
    private int size;

    public MyStackImpl(){
        top = null;
        size = 0;
    }


    @Override
    public boolean isEmpty() {
        return top==null;
    }

    @Override
    public void push(T element) {
        NodeStack<T> newNode = new NodeStack<>(element);
        newNode.setNext(top);
        top = newNode;
        size ++;

    }

    @Override
    public T pop() throws EmptyStackException {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        T aux = top.getData();
        top = top.getNext();
        size --;
        return aux;
    }

    @Override
    public T top() {
        return top.getData();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void makeEmpty() throws EmptyStackException {
        while(!isEmpty()){
            pop();
        }
    }

    @Override
    public boolean contain(T value) {
        int position = 0;
        NodeStack<T> node0 = top;
        boolean vale = false;
        while (position < getSize() && !vale){
            vale = node0.getData().equals(value);
            node0 = node0.getNext();
            position++;
        }
        return vale;
    }
}
