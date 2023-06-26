package uy.edu.um.prog2.adt.BinaryTree;

public class NodeBST<K, T> {
    /* NodeBinarySearchTree = NodeBST */
    private K key;
    private T data;
    private NodeBST<K,T> leftChild;
    private NodeBST<K,T> rightChild;

    public NodeBST(K key, T data) {
        this.key = key;
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    public K getKey() {
        return this.key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeBST<K, T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(NodeBST<K, T> leftChild) {
        this.leftChild = leftChild;
    }

    public NodeBST<K, T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(NodeBST<K, T> rightChild) {
        this.rightChild = rightChild;
    }

    public String toString() {
        return this.key.toString() + this.data.toString();
    }
}
