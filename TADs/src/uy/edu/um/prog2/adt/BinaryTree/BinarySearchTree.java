package uy.edu.um.prog2.adt.BinaryTree;

import uy.edu.um.prog2.adt.LinkedList.MyLinkedListImpl;

public class BinarySearchTree<K extends Comparable<K>, T> implements MyBinarySearchTree<K, T> {
    private int treeSize = 0;
    private NodeBST<K, T> root;

    public BinarySearchTree() {
    }

    public NodeBST<K, T> getRoot() {
        return this.root;
    }

    public void setRoot(NodeBST<K, T> root) {
        this.root = root;
    }

    private NodeBST<K, T> find(K key, NodeBST<K, T> root) throws NotInTreeException {
        NodeBST<K, T> result = null;
        if (root != null) {
            if (key.compareTo((K) root.getKey()) == 0) {
                result = root;
            } else if (key.compareTo((K) root.getKey()) < 0) {
                result = this.find(key, root.getLeftChild());
            } else {
                result = this.find(key, root.getRightChild());
            }
        }

        if (result == null) {
            throw new NotInTreeException();
        } else {
            return result;
        }
    }

    public T find(K key) {
        try {
            return this.find(key, this.root).getData();
        } catch (NotInTreeException var3) {
            return null;
        }
    }

    public void insert(K key, T data) {
        this.root = this.insert(key, data, this.root);
        ++this.treeSize;
    }

    private NodeBST<K, T> insert(K key, T data, NodeBST<K, T> subtree) {
        NodeBST<K, T> NewElement = new NodeBST(key, data);
        if (subtree == null) {
            return NewElement;
        } else {
            NodeBST newRight;
            if (((Comparable)subtree.getKey()).compareTo(key) > 0) {
                newRight = this.insert(key, data, subtree.getLeftChild());
                subtree.setLeftChild(newRight);
                return subtree;
            } else if (((Comparable)subtree.getKey()).compareTo(key) < 0) {
                newRight = this.insert(key, data, subtree.getRightChild());
                subtree.setRightChild(newRight);
                return subtree;
            } else {
                return null;
            }
        }
    }

    public void delete(K key) {
        try {
            this.root = this.delete(key, this.root);
            --this.treeSize;
        } catch (NotInTreeException var3) {
            System.out.println("Not in tree.");
        }

    }

    public NodeBST<K, T> delete(K key, NodeBST<K, T> subtree) throws NotInTreeException {
        if (subtree == null) {
            return null;
        } else if (this.find(key) == null) {
            throw new NotInTreeException();
        } else if (key.compareTo((K) subtree.getKey()) == 0) {
            if (subtree.getLeftChild() == null && subtree.getRightChild() == null) {
                return null;
            } else if (subtree.getLeftChild() == null) {
                return subtree.getRightChild();
            } else if (subtree.getRightChild() == null) {
                return subtree.getLeftChild();
            } else {
                K minKey = this.minKey(subtree.getRightChild());
                subtree.setData(this.find(minKey));
                subtree.setKey(minKey);
                subtree.setRightChild(this.delete(minKey, subtree.getRightChild()));
                return subtree;
            }
        } else {
            if (key.compareTo((K) subtree.getKey()) < 0) {
                subtree.setLeftChild(this.delete(key, subtree.getLeftChild()));
            } else {
                subtree.setRightChild(this.delete(key, subtree.getRightChild()));
            }

            return subtree;
        }
    }

    private K minKey(NodeBST<K, T> node) {
        if (node == null) {
            return null;
        } else {
            return node.getLeftChild() != null ? this.minKey(node.getLeftChild()) : (K) node.getKey();
        }
    }

    public int size() {
        return this.treeSize;
    }

    public MyLinkedListImpl<NodeBST<K, T>> inOrder() {
        MyLinkedListImpl<NodeBST<K, T>> list = new MyLinkedListImpl();
        this.inOrderHelper(this.root, list);
        return list;
    }

    private void inOrderHelper(NodeBST<K, T> node, MyLinkedListImpl<NodeBST<K, T>> list) {
        if (node != null) {
            this.inOrderHelper(node.getLeftChild(), list);
            list.add(node);
            this.inOrderHelper(node.getRightChild(), list);
        }

    }

    public MyLinkedListImpl<NodeBST<K, T>> preOrder() {
        MyLinkedListImpl<NodeBST<K, T>> list = new MyLinkedListImpl();
        this.preOrderHelper(this.root, list);
        return list;
    }

    private void preOrderHelper(NodeBST<K, T> node, MyLinkedListImpl<NodeBST<K, T>> list) {
        if (node != null) {
            list.add(node);
            this.preOrderHelper(node.getLeftChild(), list);
            this.preOrderHelper(node.getRightChild(), list);
        }

    }

    public MyLinkedListImpl<NodeBST<K, T>> postOrder() {
        MyLinkedListImpl<NodeBST<K, T>> list = new MyLinkedListImpl();
        this.postOrderHelper(this.root, list);
        return list;
    }

    private void postOrderHelper(NodeBST<K, T> node, MyLinkedListImpl<NodeBST<K, T>> list) {
        if (node != null) {
            this.postOrderHelper(node.getLeftChild(), list);
            this.postOrderHelper(node.getRightChild(), list);
            list.add(node);
        }

    }
}
