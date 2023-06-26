package uy.edu.um.prog2.adt.ArrayList;


public class MyArrayListImpl <T> implements MyArrayList<T> {


    private T[] list;
    private int size;
    private int length;

    public MyArrayListImpl (int length){
        this.list = (T[]) new Object[length];
        this.length = length;
        this.size = 0;
    }


    @Override
    public void add(T value) {
        if(value!=null) {
            if(size == length){
                T[] newlist = (T[]) new Object[2*length];

                for(int i = 0; i < size; i ++){
                    newlist[i] = list[i];
                }
                this.list = newlist;
                length = 2*length;

            }

                list[size] = value;
                size = size + 1;

        }
    }

    @Override
    public T get(int position) {
        if (position<size) {
            return list[position];
        }else{
            return null;
        }
    }

    @Override
    public void delete(T value) {
        if (value != null && this.contain(value)) {
            int position = 0;
            for (int i = 0; i < size; i++) {
                if (list[position] != value) {
                    position = position + 1;
                }
            }
            for (int i = 0; i < (size - position) - 1; i++) {
                list[position + i] = list[position + i + 1];
            }
            list[size - 1] = null;
            size = size - 1;
        }

    }

    @Override
    public boolean contain(T value) {
        if(value != null) {
            for (int i = 0; i < size; i++) {
                if (list[i] == value) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    public T[] getList() {
        return list;
    }

    public void setList(T[] list) {
        this.list = list;
    }
}
