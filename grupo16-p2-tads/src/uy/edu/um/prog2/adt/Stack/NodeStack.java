package uy.edu.um.prog2.adt.Stack;

public class NodeStack<T> {
    private T data;
    private NodeStack next;

    public NodeStack(T data ) {
        this.data = data ;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeStack getNext() {
        return next;
    }

    public void setNext(NodeStack next) {
        this.next = next;
    }
}
