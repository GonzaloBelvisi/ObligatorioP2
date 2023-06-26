package uy.edu.um.prog2.adt.ArrayList;

public interface MyArrayList <T> {
    void add (T value);
    T get (int position);
    void delete(T value);
    boolean contain(T value);
    int size();


}
