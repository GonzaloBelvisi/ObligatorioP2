package uy.edu.um.prog2.adt.Heap;

public class MyHeapImpl <T extends Comparable<T>> implements MyHeap<T> {


    private T[] value;
    private int heapSize;

    public MyHeapImpl(int size) {
        this.value = (T[]) new Comparable[size];
        this.heapSize = 0;
    }

    private int getFatherPosition(int position) {
        return(position - 1) / 2;
    }

    private int getLeftChildPosition(int position){
        return 2 * position + 1;
    }

    private int getRightChildPosition(int position){
        return 2 * position + 2;
    }

    @Override
    public void insert(T newValue) {
        int position = heapSize;
        heapSize++;
        value[position] = newValue;
        while(value[position].compareTo(value[getFatherPosition(position)])>0 && position !=0){
            value[position] = value[getFatherPosition(position)];
            value[getFatherPosition(position)] = newValue;
            position = getFatherPosition(position);
        }

    }

    @Override
    public T delete() throws EmptyHeapException {
        T returnValue = null;
        if (heapSize == 0){
            throw new EmptyHeapException();
        }
        returnValue = value[0];
        if (heapSize == 1) {
            value[0] = null;
        } else {
            value[0] = value[heapSize - 1];
            int position = 0;
            int childMaxPosition = maxPosition(getLeftChildPosition(position), getRightChildPosition(position));
            while (value[position].compareTo(value[childMaxPosition]) <0 && position < heapSize && childMaxPosition !=0){
                T temp = value[position];
                value[position] = value[childMaxPosition];
                value[childMaxPosition] = temp;
                position = childMaxPosition;
                childMaxPosition = maxPosition(getLeftChildPosition(position), getRightChildPosition(position));
            }
            }

        heapSize--;
        return returnValue;
    }

    @Override
    public int size() {
        return heapSize;
    }

    private int maxPosition(int index1, int index2){
        if (index1 > heapSize && index2 > heapSize){
            return 0;
        }
        if(index1 <= heapSize && index1 > heapSize){
            return index1;
        }
        if (index1 > heapSize && index2 <= heapSize){
            return index2;
        }
        if (value[index1].compareTo(value[index2])>0){
            return index1;
        } else {
            return index2;
        }
    }
}
