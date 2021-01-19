package com.company;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class AVLInterface extends Pane {
    private Opertional<Integer> newtree = new Opertional<>();
    private final double vertical = 80; // Gap between two levels in a tree

    public void text_display(String line) {
        Text Text = new Text(800,37.5, line);
        Font font = Font.font("Roboto", FontWeight.BOLD, FontPosture.ITALIC,22);
        Text.setFont(font);
        Text.setFill(Color.GOLD);
        getChildren().add(Text );
    }

    public void text_displayAL(ArrayList<Integer> nodes) {
        Text Text = new Text(900,37.5, nodes.toString());
        Font font = Font.font("Roboto", FontWeight.BOLD, FontPosture.ITALIC,22);
        Text.setFont(font);
        Text.setFill(Color.GOLD);
        getChildren().add(Text );
    }

    public void text_displayHeight(String height) {
        Text Text = new Text(650,700, height);
        Font font = Font.font("Roboto", FontWeight.BOLD, FontPosture.ITALIC,28);
        Text.setFont(font);
        Text.setFill(Color.GOLD);
        getChildren().add(Text);
    }

    public void text_displayVertices(String height) {
        Text Text = new Text(1100,700, height);
        Font font = Font.font("Roboto", FontWeight.BOLD, FontPosture.ITALIC,28);
        Text.setFont(font);
        Text.setFill(Color.GOLD);
        getChildren().add(Text);
    }

    AVLInterface(Opertional<Integer> tree) {
        this.newtree = tree;
        text_display("EMPTY.");
    }

    public void displayAVLTree() {
        this.getChildren().clear(); // Clear the pane
        if (newtree.getRoot() != null) {
            // Display tree recursively
            displayAVLTree(newtree.getRoot(), getWidth() / 2, vertical,getWidth() / 2);
        }

    }

    public void displayAVLTree(com.company.AVL.TreeNode<Integer> root, double x, double y, double horizontal) {
        if (root.left != null) {
            // Draw a line to the left node
            getChildren().add(new Line(x - horizontal/4, y + vertical, x, y));
            // Draw the left subtree recursively
            displayAVLTree(root.left, x - horizontal/4, y + vertical, horizontal / 2 );
        }

        if (root.right != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + horizontal/4, y + vertical, x, y));
            // Draw the right subtree recursively
            displayAVLTree(root.right, x + horizontal/4, y + vertical, horizontal / 2);
        }


        // Display a node
        // Tree node radius
        double radius = 22;
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.ORANGE);
        circle.setStroke(Color.BLACK);
//        getChildren().addAll(circle, new Text(x - 4, y + 4, root.element + ""));
        Text t=new Text(x - 4, y + 4, root.element + "");
        t.setFill(Color.BLACK);
        t.setFont(Font.font ("verdana",FontWeight.BOLD,15));
        getChildren().addAll(circle,t);
    }
}



