package uy.edu.um.prog2.adt.Stack;

public interface MyStack<T> {
    boolean isEmpty();
    void push(T element);
    T pop() throws EmptyStackException;
    T top();
    int getSize();
    void makeEmpty() throws EmptyStackException;
    boolean contain(T value);
}
