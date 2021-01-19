
package com.company;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.max;

public class AVL<E extends Comparable<E>> extends Opertional<E> {

    int height;
    ArrayList<Integer> height1 = new ArrayList<>();
    AVLTreeNode<E> current = (AVLTreeNode<E>) root;
    Label heightLabel = new Label();
    int heightF = 0;

    int flag = 0;
    void addHeightToDetailPane(AVLTreeNode node, Pane detailsPane){
        if(flag == 1){
            detailsPane.getChildren().remove(heightLabel);
        }

        heightLabel.setText("Height of the Tree: "+ updateHeight(node)+ "\t\t\tTotal nodes in the Tree:");
//        heightLabel.setStyle("-fx-font: 25px Tahoma; "
//                + "-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);"
//                + "-fx-stroke: black; -fx-stroke-width: 1;");
        detailsPane.getChildren().add(heightLabel);
        flag = 1;
    }

    public AVL() {  //Creates a default AVL Tree
    }



    @Override//Override createNewNode to create an AVLTreeNode
    public AVLTreeNode<E> createNewNode(E node) {
        return new AVLTreeNode<E>(node);
    }

    public int isLeaf(TreeNode<E> E)
    {
        if(E == null)
            return 0;
        else if(E.left == null && E.right == null)
            return 1;
        else
            return 0;
    }

    public int getVertices(TreeNode<E> E){
        TreeNode<E> current = root;
        int vertices=0;
        if(current==null)
            return 0;
        getVertices(current.left);
        getVertices(current.right);
        if(current==root){
            vertices++;
        }
        if(isLeaf(current)==1) {
            if (current.left != null)
                vertices++;
            if (current.right != null)
                vertices++;
        }
        return vertices;
    }

    @Override   //Insert an element and re-balance, if required
    public boolean insert(E element) {
        boolean successful = super.insert(element);
        if (!successful) {
            return false; // element already present in the tree
        } else {
            balancePath(element);// Balance from element to the root, if required
//            updateHeight((AVLTreeNode<E>) element);
        }

        return true; // element inserted
    }


    public int updateHeight(AVLTreeNode<E> node) {  //Updating height of a specified node


            if (node.left == null && node.right == null) // node is a leaf
            {
                node.height = 0;
            } else if (node.left == null) // node has no left subtree
            {
                node.height = 1 + ((AVLTreeNode<E>) (node.right)).height;
            } else if (node.right == null) // node has no right subtree
            {
                node.height = 1 + ((AVLTreeNode<E>) (node.left)).height;
            } else {
                node.height = 1 + max(((AVLTreeNode<E>) (node.right)).height, ((AVLTreeNode<E>) (node.left)).height);
            }
            return node.height;
    }

    public void balancePath(E o) {        //Balances the nodes of  the tree in the path from the particular node to the root when required
        java.util.ArrayList<TreeNode<E>> path = path(o);
        for (int i = path.size() - 1; i >= 0; i--) {
            AVLTreeNode<E> A = (AVLTreeNode<E>) (path.get(i));
            updateHeight(A);
            AVLTreeNode<E> parentOfA = (A == root) ? null
                    : (AVLTreeNode<E>) (path.get(i - 1));

            switch (balanceFactor(A)) {
                case -2:
                    if (balanceFactor((AVLTreeNode<E>) A.left) <= 0) {
                        balanceLL(A, parentOfA); // Perform LL rotation
                    } else {
                        balanceLR(A, parentOfA); // Perform LR rotation
                    }
                    break;
                case +2:
                    if (balanceFactor((AVLTreeNode<E>) A.right) >= 0) {
                        balanceRR(A, parentOfA); // Perform RR rotation
                    } else {
                        balanceRL(A, parentOfA); // Perform RL rotation
                    }
            }
        }

    }


    private int balanceFactor(AVLTreeNode<E> node) {    //balance factor of the particular node
        if (node.right == null) // node has no right subtree
        {
            return -node.height;
        } else if (node.left == null) // node has no left subtree
        {
            return +node.height;
        } else {
            return ((AVLTreeNode<E>) node.right).height - ((AVLTreeNode<E>) node.left).height;
        }
    }

    private int balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) {     //LL balancing of the tree
        TreeNode<E> B = A.left; // A is left-heavy and B is left-heavy

        if (A == root) {
            root = B;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = B;
            } else {
                parentOfA.right = B;
            }
        }

        A.left = B.right; // Make T2 the left subtree of A
        B.right = A; // Make A the left child of B
        int heightA = updateHeight((AVLTreeNode<E>) A);
        int heightB = updateHeight((AVLTreeNode<E>) B);
        return max(heightA, heightB);
    }


    private int balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {  //LR balancing of the tree
        TreeNode<E> B = A.left; // A is left-heavy
        TreeNode<E> C = B.right; // B is right-heavy

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        A.left = C.right; // Make T3 the left subtree of A
        B.right = C.left; // Make T2 the right subtree of B
        C.left = B;
        C.right = A;

        // Adjust heights
        int heightA = updateHeight((AVLTreeNode<E>) A);
        int heightB = updateHeight((AVLTreeNode<E>) B);
        int heightC = updateHeight((AVLTreeNode<E>) C);
        return max(max(heightA, heightB),max(heightB, heightC));
    }



//    public boolean contains(E value) {
////        Node current = root;
//       while (current != null) {
//            int comparison = value.compareTo(current.value);
//            if (comparison == 0) {
//                return true;
//            } else if (comparison < 0) {
//                current = (AVLTreeNode<E>) current.left;
//            } else { //comparison > 0
//                current = (AVLTreeNode<E>) current.right;
//            }
//        }
//
//        return false;
//    }

    private int balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {  //RR balancing of the tree
        TreeNode<E> B = A.right; // A is right-heavy and B is right-heavy

        if (A == root) {
            root = B;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = B;
            } else {
                parentOfA.right = B;
            }
        }

        A.right = B.left; // Make T2 the right subtree of A
        B.left = A;
        int heightA = updateHeight((AVLTreeNode<E>) A);
        int heightB = updateHeight((AVLTreeNode<E>) B);
        return max(heightA, heightB);
    }

    private int balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {  //RL balancing of the tree
        TreeNode<E> B = A.right; // A is right-heavy
        TreeNode<E> C = B.left; // B is left-heavy

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        A.right = C.left; // Make T2 the right subtree of A
        B.left = C.right; // Make T3 the left subtree of B
        C.left = A;
        C.right = B;

        // Adjust heights
        int heightA = updateHeight((AVLTreeNode<E>) A);
        int heightB = updateHeight((AVLTreeNode<E>) B);
        int heightC = updateHeight((AVLTreeNode<E>) C);
        return max(max(heightA, heightB),max(heightB, heightC));
    }


    @Override
    public boolean delete(E element) {    //If the element is deleted successfully then returns true else false
        if (root == null) {
            return false; // Element is not in the tree
        }
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (element.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (element.compareTo(current.element) > 0) {
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
                if (element.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }

            // Balance the tree if necessary
            balancePath(parent.element);
        } else {
            TreeNode<E> parentOfRightMost = current; //if the node has a left child
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost is current
            {
                parentOfRightMost.left = rightMost.left;
            }

            // Balance the tree if necessary
            balancePath(parentOfRightMost.element);
        }

        size--;
        return true; // Element inserted
    }

    public static class AVLTreeNode<E extends Comparable<E>> extends Opertional.TreeNode<E> {
        //TreeNode with height
        int height = 0; // New data field

        public AVLTreeNode(E o) {
            super(o);
        }

        public AVLTreeNode() {
            super();
        }


//        public AVLTreeNode() {
//            super();
//        }
    }

}



