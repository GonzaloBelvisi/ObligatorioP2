package uy.edu.um.prog2.adt.LinkedList;

public interface MyLinkedList<T> {
    public void add(T value);
    public boolean remove(int position);
    public T get(int position);
    boolean isEmpty();
    int getSize();
    boolean contains(T value);
    public void addFirst(T value);
    void addLast(T value);
}
