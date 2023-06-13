package uy.edu.um.prog2.adt.LinkedList;

public class NodeLinkedList<T>{
    private T value = null;
    private NodeLinkedList next;

    public NodeLinkedList(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public NodeLinkedList getNext() {
        return next;
    }

    public void setNext(NodeLinkedList next) {
        this.next = next;
    }
}