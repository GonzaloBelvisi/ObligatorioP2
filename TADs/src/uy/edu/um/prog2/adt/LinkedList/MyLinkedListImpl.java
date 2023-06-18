package uy.edu.um.prog2.adt.LinkedList;

public class MyLinkedListImpl <T> implements MyLinkedList<T> {

    NodeLinkedList<T> head;

    private int size;

    public MyLinkedListImpl(){
        this.head = null;
        this.size =0;

    }

    public MyLinkedListImpl(T value){
        this.head = new NodeLinkedList<>(value);
    }
    @Override
    public void add(T value) {
        if (this.head == null){
            this.head = new NodeLinkedList<>(value);
            size++;
        }
        else {
            NodeLinkedList<T> newNode = this.head;
            while (newNode.getNext() != null) {
                newNode = newNode.getNext();
            }
            newNode.setNext(new NodeLinkedList(value));
            size++;
        }
    }


    @Override
    public boolean remove(int position) {
        int count = 0;
        if (position > getSize()){
            return false;
        }
        if (position == 0){
            this.head = this.head.getNext();
            return true;
        }
        else {
            NodeLinkedList<T> newNode = this.head;
            while (count < position - 1){
                newNode = newNode.getNext();
                count++;
            }
            newNode.setNext(newNode.getNext().getNext());
            return true;
        }
    }

    @Override
    public T get(int position) {
        if (position >= getSize()){
            return null;
        }
        else {
            NodeLinkedList<T> newNode = this.head;
            while (position > 0){
                newNode = newNode.getNext();
                position = position - 1;
            }
            if (newNode == null){
                return null;
            }
            return newNode.getValue();
        }
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean contains(T value) {
        NodeLinkedList<T> newNode = this.head;
        while (newNode != null){
            if (newNode.getValue().equals(value)){
                return true;
            }
            newNode = newNode.getNext();
        }
        return false;
    }

    @Override
    public void addFirst(T value) {
        if (head == null){
            head = new NodeLinkedList(value);
            size++;
        }
        else {
            NodeLinkedList<T> NewAddNode = this.head;
            head = new NodeLinkedList(value);
            head.setNext(NewAddNode);
            size++;
        }
    }

    @Override
    public void addLast(T value) {
        add(value);

    }

}
