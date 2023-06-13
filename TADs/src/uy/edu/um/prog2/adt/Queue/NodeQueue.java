package uy.edu.um.prog2.adt.Queue;

public class NodeQueue<T> {
    private T data;
    private NodeQueue next;

    public NodeQueue(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeQueue getNext() {
        return next;
    }

    public void setNext(NodeQueue next) {
        this.next = next;
    }
}

