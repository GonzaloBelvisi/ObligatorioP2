package uy.edu.um.prog2.adt.Hash2;

import uy.edu.um.prog2.adt.ArrayList.MyArrayList;
import uy.edu.um.prog2.adt.ArrayList.MyArrayListImpl;
import uy.edu.um.prog2.adt.Hash2.ClosedHashNode2;

public class MyClosedHashImpl2<K, V> {

    private int tableSize;
    private int currentSize;
    private int occupiedSize;
    private MyArrayList<K> keyList;
    private MyArrayList<ClosedHashNode2<K, V>>[] tableHash;
    private static final int DEFAULT_INITIAL_TABLE_HASH_SIZE = 100000;

    private int actualHashSize;

    private void initVector() {
        for (int i = 0; i < tableSize; i++) {
            tableHash[i] = null;
        }
        currentSize = 0;
        actualHashSize = 0;
    }

    public MyClosedHashImpl2(int maxExpectedSize) {
        tableHash = new MyArrayList[maxExpectedSize];
        tableSize = maxExpectedSize;
        this.keyList = new MyArrayListImpl<>(maxExpectedSize);
        initVector();
    }

    public MyClosedHashImpl2() {
        tableHash = new MyArrayList[DEFAULT_INITIAL_TABLE_HASH_SIZE];
        tableSize = DEFAULT_INITIAL_TABLE_HASH_SIZE;
        this.keyList = new MyArrayListImpl<>(100000);
        initVector();
    }

    private void reSize() {
        int newTableSize = tableSize * 2;
        MyClosedHashImpl2<K, V> newHash = new MyClosedHashImpl2<>(newTableSize);

        for (int i = 0; i < tableSize; i++) {
            MyArrayList<ClosedHashNode2<K, V>> list = tableHash[i];
            if (list != null) {
                for (int j = 0; j < list.size(); j++) {
                    ClosedHashNode2<K, V> node = list.get(j);
                    newHash.put(node.getKey(), node.getValue());
                }
            }
        }

        tableSize = newTableSize;
        tableHash = newHash.tableHash;
        occupiedSize = newHash.occupiedSize;
        keyList = newHash.keyList;
    }

    public void put(K key, V value) {
        if ((float) actualHashSize / tableSize >= 0.7) {
            reSize();
        }

        int aux = key.hashCode();
        if (key.hashCode() < 0) {
            aux = -key.hashCode();
        }
        int hash = aux % tableSize;

        if (tableHash[hash] == null) {
            tableHash[hash] = new MyArrayListImpl<>(2000);
            actualHashSize++;
        }
        tableHash[hash].add(new ClosedHashNode2<>(key, value));

        currentSize++;
        occupiedSize++;
        keyList.add(key);
    }

    public V get(K key) {
        int aux = key.hashCode();
        if (key.hashCode() < 0) {
            aux = -key.hashCode();
        }
        int hash = aux % tableSize;
        MyArrayList<ClosedHashNode2<K, V>> list = tableHash[hash];
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                ClosedHashNode2<K, V> node = list.get(i);
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

        MyArrayList<ClosedHashNode2<K, V>> list = tableHash[hash];
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
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

    public MyArrayListImpl<V> getPosition(int position) {
        K key = keyList.get(position);
        return getAll(key);
    }

    public int getActualHashSize() {
        return actualHashSize;
    }
}
