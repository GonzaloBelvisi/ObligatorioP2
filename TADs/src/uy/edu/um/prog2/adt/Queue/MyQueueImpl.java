package uy.edu.um.prog2.adt.Queue;

public class MyQueueImpl<T> implements MyQueue<T> {
    private NodeQueue<T> first;
    private NodeQueue<T> last;
    private int size;

    public MyQueueImpl(){
        first=last=null;
        size =0;
    }

    @Override
    public boolean isEmpty() {
        return first==null;
    }

    @Override
    public void enqueue(T element) {
        NodeQueue<T> newNode = new NodeQueue(element);
        if(isEmpty()){
            first = newNode;
        }
        else{
            last.setNext(newNode);
        }
        last = newNode;
        size++;

    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if(size==0){
            throw new EmptyQueueException();
        }
        T aux = first.getData();
        first = first.getNext();
        size--;
        return aux;
    }

    @Override
    public T getFirstElement() {
        return first.getData();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public T getFirst() {
        return first.getData();
    }

    @Override
    public T getLast() {
        return last.getData();
    }

    @Override
    public boolean contain(T value) {
        int position = 0;
        NodeQueue<T> node0 = first;
        boolean vale = false;
        while (position < getSize() && !vale){
            vale = node0.getData().equals(value);
            node0 = node0.getNext();
            position++;
        }
        return vale;
    }
}
