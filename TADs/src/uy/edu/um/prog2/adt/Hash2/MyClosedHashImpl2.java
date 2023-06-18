package uy.edu.um.prog2.adt.Hash2;
import uy.edu.um.prog2.adt.ArrayList.MyArrayList;
import uy.edu.um.prog2.adt.ArrayList.MyArrayListImpl;
import uy.edu.um.prog2.adt.LinkedList.MyLinkedListImpl;

import java.util.LinkedList;

public class MyClosedHashImpl2<K, V> {

    private int tableSize;
    private int currentSize;
    private int occupiedSize;
    private MyArrayList<K> KeyList;
    private MyLinkedListImpl<ClosedHashNode2<K,V>>[] tableHash;
    private static final int DEFAULT_INITIAL_TABLE_HASH_SIZE = 100000;


    private void initVector(){
        for (int i = 0; i< tableSize; i++){
            tableHash[i] = null;
        }
        currentSize = 0;
    }

    public MyClosedHashImpl2(int maxExpectedSize) {
        tableHash = new MyLinkedListImpl[maxExpectedSize];
        tableSize = maxExpectedSize;
        this.KeyList = new MyArrayListImpl<>(maxExpectedSize);
        initVector();
    }

    public MyClosedHashImpl2(){
        tableHash = new MyLinkedListImpl[DEFAULT_INITIAL_TABLE_HASH_SIZE];
        tableSize = DEFAULT_INITIAL_TABLE_HASH_SIZE;
        this.KeyList = new MyArrayListImpl<>(100000);
        initVector();
    }



    private void reSize() {
        int newTableSize = tableSize * 2;
        MyClosedHashImpl2<K, V> newHash = new MyClosedHashImpl2<>(newTableSize);

        for (int i = 0; i < tableSize; i++) {
            MyLinkedListImpl<ClosedHashNode2<K, V>> list = tableHash[i];
            if (list != null) {
                for (int J = 0; J < list.getSize() ; J++) {
                    ClosedHashNode2<K,V> node = list.get(J);
                    newHash.put(node.getKey(), node.getValue());
                }
            }
        }

        tableSize = newTableSize;
        tableHash = newHash.tableHash;
        occupiedSize = newHash.occupiedSize;
        KeyList = newHash.KeyList;
    }


    public void put(K key, V value) {
        if ((float) occupiedSize / tableSize >= 0.7) {
            reSize();
        }

        int aux = key.hashCode();
        if (key.hashCode() < 0) {
            aux = -key.hashCode();
        }
        int hash = aux % tableSize;

        if (tableHash[hash] == null) {
            tableHash[hash] = new MyLinkedListImpl<>();
        }
        tableHash[hash].addFirst(new ClosedHashNode2<>(key, value));

        currentSize++;
        occupiedSize++;
        KeyList.add(key);
    }

    public V get(K key) {
        int aux = key.hashCode();
        if (key.hashCode() < 0) {
            aux = -key.hashCode();
        }
        int hash = aux % tableSize;
        MyLinkedListImpl<ClosedHashNode2<K,V>> list = tableHash[hash];
        if (list != null) {
            for (int i = 0; i < list.getSize() ; i++) {
                ClosedHashNode2<K,V> node = list.get(i);
                if (node.getKey().equals(key) && !node.isDeleted()) {
                    return node.getValue();
                }

            }
        }
        return null;
    }

    public MyArrayListImpl<V> getAll(K key) {
        MyArrayListImpl<V> values = new MyArrayListImpl<>(10000);

        int aux = key.hashCode();
        if (key.hashCode() < 0) {
            aux = -key.hashCode();
        }
        int hash = aux % tableSize;

        MyLinkedListImpl<ClosedHashNode2<K, V>> list = tableHash[hash];
        if (list != null) {
            for (int i = 0; i < list.getSize(); i++) {
                ClosedHashNode2<K, V> node = list.get(i);
                if (node.getKey().equals(key) && !node.isDeleted()) {
                    values.add(node.getValue());
                }
            }
        }

        return values;
    }

    public int getCurrentSize() {
        return currentSize;
    }
}
