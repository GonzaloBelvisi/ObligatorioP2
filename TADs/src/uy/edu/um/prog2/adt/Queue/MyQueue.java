package uy.edu.um.prog2.adt.Queue;

public interface MyQueue<T> {
    boolean isEmpty();
    void enqueue(T element);
    T dequeue() throws EmptyQueueException;
    T getFirstElement();
    int getSize();
    T getFirst();
    T getLast();
    boolean contain(T value);
}