package uy.edu.um.prog2.adt.Hash;
import uy.edu.um.prog2.adt.ArrayList.MyArrayList;
import uy.edu.um.prog2.adt.ArrayList.MyArrayListImpl;

public class MyClosedHashImpl <K, V> implements MyHash<K, V> {

    private int tableSize;
    private int currentSize;
    private int occupiedSize;
    private MyArrayList<K> KeyList;

    private ClosedHashNode[] tableHash;
    private static final int DEFAULT_INITIAL_TABLE_HASH_SIZE = 100000;
    public void setTableHash(ClosedHashNode[] tableHash) {
        this.tableHash = tableHash;
    }
    public void setOccupiedSize(int occupiedSize) {
        this.occupiedSize = occupiedSize;
    }
    public void setKeyList(MyArrayList<K> KeyList) {
        this.KeyList = KeyList;
    }
    private void initVector(){
        for (int i = 0; i< tableSize; i++){
            tableHash[i] = null;
        }
        currentSize = 0;
    }
    public MyClosedHashImpl(int maxExpectedSize){
        tableHash = new ClosedHashNode[maxExpectedSize];
        tableSize = maxExpectedSize;
        this.KeyList = new MyArrayListImpl<>(maxExpectedSize);
        initVector();
    }
    public MyClosedHashImpl(){
        tableHash = new ClosedHashNode[DEFAULT_INITIAL_TABLE_HASH_SIZE];
        tableSize = DEFAULT_INITIAL_TABLE_HASH_SIZE;
        this.KeyList = new MyArrayListImpl<>(100000);
        initVector();
    }

    private void reSize() {
        this.tableSize = tableSize * 2;
        MyClosedHashImpl newHash = new MyClosedHashImpl(tableSize);
        for (int i = 0; i < tableSize/2;
             i++){
            if (tableHash[i] != null && tableHash[i].isDeleted() == false){
                newHash.put(tableHash[i].getKey(), tableHash[i].getValue());
            }
        }
        setTableHash(newHash.tableHash);
        setOccupiedSize(newHash.occupiedSize);
        setKeyList(newHash.KeyList);
    }



    @Override
    public void put(K key, V value) {
        if ((float) occupiedSize / tableSize >= 0.7){
            reSize();
        }

        int aux = key.hashCode();
        if (key.hashCode()<0){
            aux = -key.hashCode();
        }
        int hash = aux % tableSize;

        while (tableHash[hash] != null && !(tableHash[hash].isDeleted())){
            hash = (hash + 1) % tableSize;
        }
        tableHash[hash] = new ClosedHashNode<>(key, value);
        currentSize ++;
        occupiedSize ++;
        KeyList.add(key);

    }


    @Override
    public V get(K key) {
        int aux = key.hashCode();
        if (key.hashCode()<0){
            aux = -key.hashCode();
        }
        int hash = aux % tableSize;
        while (tableHash[hash] != null){
            if (tableHash[hash].getKey().equals(key) && !tableHash[hash].isDeleted()){
                return(V) tableHash[hash].getValue();
            }
            hash = (hash + 1) % tableSize;
        }
        return null;
    }

    @Override
    public void delete(K key) {
        int hash = key.hashCode() % tableSize;
        while (tableHash[hash] != null){
            if (tableHash[hash].getKey().equals(key) && !tableHash[hash].isDeleted()){
                tableHash[hash].setDeleted(true);
                currentSize --;
                KeyList.delete(key);
            }
            hash = (hash + 1) % tableSize;
        }
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public K KeyListKeys(int spot) {
        return null;
    }

    public V getPosition(int position){
        K key = KeyList.get(position);
        return get(key);
    }

    public MyArrayList<K> getKeyList() {
        return KeyList;
    }

    public K ArrayListKey (int positionArrayList){
        return KeyList.get(positionArrayList);
    }
}
