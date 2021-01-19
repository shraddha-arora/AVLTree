package com.company;

import java.util.ArrayList;

import static java.lang.Math.max;

public class Opertional<E extends Comparable<E>> extends AbstractTree<E> {


    public static class TreeNode<E extends Comparable<E>> {

        //Declarations
        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }

        public TreeNode() {
            super();
        }
    }

    protected TreeNode<E> root;
    protected int size = 0;

    public Opertional() {
    }

    @Override
    public boolean search(E e) {        //returns true if elements exist

        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return true; // Element is found
            }
        }
        return false;
    }

    @Override
    public boolean insert(E e) {     //returns true if element is inserted successful
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);                  //new node is created
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    @Override
    public Comparable height() {
        return null;
    }

//    @Override
//    public <E1 extends Comparable<E>> E1 height() {
//        return null;
//    }

//    public <E1 extends Comparable<E>> int height(TreeNode<E> current) {
//        if (current == null)
//            return 0;
//        else
////               return updateHeight(node) + 1;
//            return max(height((AVL.AVLTreeNode<E>) current.left), height((AVL.AVLTreeNode<E>) current.right)) + 1;
//    }

//    @Override
//    public <E1 extends Comparable<E>> E1 height() {
//        return null;
//    }

    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }                               // helper method of inorder traversal
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }



    @Override
    public int getSize() {
        return size;        //size (no of nodes) of the tree
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public ArrayList<TreeNode<E>> path(E e) {

        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) {                                      //path of root to element
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return list; // Return an array of nodes
    }


    @Override
    public boolean delete(E e) {                         //  If the element is deleted successfully then returns true else false

        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed by current
            }
        }
        if (current == null) {
            return false; // Element is not in the tree
        }
        // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;                 //if the node has a left child

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; // Element inserted
    }


    @Override
    public java.util.Iterator iterator() {
        return inorderIterator();                   //iterator
    }

    public java.util.Iterator inorderIterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {

        // Store the elements in a list
        private java.util.ArrayList<E> list = new java.util.ArrayList<>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        private void inorder() {
            inorder(root);
        }

        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);                   //from subtree
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext() {
            if (current < list.size()) {
                return true;                   //true if next elements exist
            }
            return false;
        }

        @Override
        public Object next() {
            return list.get(current++);        //returns current element and goes to next element
        }


        @Override
        public void remove() {                           //clears the list
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    public void clear() {
        root = null;           //removes all the element
        size = 0;
    }
}



