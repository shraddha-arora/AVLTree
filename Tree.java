package com.company;

public interface Tree<E> extends Iterable<E> {

    boolean search(E e);    //returns true if element is found

    boolean insert(E e);    //Checks if element is inserted in the tree

    boolean delete(E e);    //Checks if element is deleted from the tree

    void inorder(); //Inorder traversal of tree

    int getSize();  //returns number of nodes in the tree

    boolean isEmpty();  //returns true if tree is empty

}
