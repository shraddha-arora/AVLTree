package com.company;

public abstract class AbstractTree<E extends Comparable<E>>
        implements Tree<E> {

    @Override
    public void inorder() { //Inorder traversal from root
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    } //returns true if tree is empty

    public abstract <E extends Comparable<E>> E height();
}


