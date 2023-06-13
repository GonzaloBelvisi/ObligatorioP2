package uy.edu.um.prog2.adt.BinaryTree;

import uy.edu.um.prog2.adt.LinkedList.MyLinkedListImpl;

public interface MyBinarySearchTree<K extends Comparable <K>, T>{
    T find (K key);
    void insert (K key, T data);
    void delete (K key);
    int size();

    MyLinkedListImpl inOrder();
    MyLinkedListImpl preOrder();
    MyLinkedListImpl postOrder();


}