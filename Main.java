
package com.company;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

public class Main extends Application {

    public BorderPane pane = new BorderPane();
    int count = 0;
    int k;

    public void start(Stage primaryStage) {
        Opertional<Integer> BSTtree = new Opertional<>();
        AVL<Integer> AVLTree = new AVL<>(); // Create an AVL tree

        ArrayList<Integer> nodes = new ArrayList<>();
        pane.setStyle("-fx-padding: 15;" + "-fx-border-style: solid ;" + "-fx-border-width: 4;" + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color:#C6FFDD;" + "-fx-background: linear-gradient(#000000,#414345,#000000)");

        AVLInterface view_avl = new AVLInterface(AVLTree);

        pane.setCenter(view_avl);
        view_avl.setPrefWidth(400);
        pane.setPrefWidth(250);

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(10);
        tfKey.setPromptText("Integers Only");
        tfKey.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#f2f2f2, #d6d6d6),\n" +
                "        linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),\n" +
                "        linear-gradient(#dddddd 0%, #f6f6f6 50%);\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-padding: 10 20 10 20;" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
        tfKey.setAlignment(Pos.BASELINE_CENTER);
        Button btInsert = new Button("Insert");
        Button btFind = new Button("Find");
        Button btDelete = new Button("Delete");
        Button btPrint = new Button("Print");
        Button btClear = new Button("Clear");
        Button btExit = new Button("Exit");

        btInsert.setStyle(" -fx-background-color: \n" +
                "        linear-gradient(#ff0084,#33001b);\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #ffffff;\n" +
                "    -fx-font-weight: 700;\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-padding: 10 20 10 20;");

        btFind.setStyle(" -fx-background-color: \n" +
                "        linear-gradient(#ff0084,#33001b);\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill:#ffffff;\n" +
                "    -fx-font-weight: 700;\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-padding: 10 20 10 20;");

        btDelete.setStyle(" -fx-background-color: \n" +
                "        linear-gradient(#ff0084,#33001b);\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #ffffff;\n" +
                "    -fx-font-weight: 700;\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-padding: 10 20 10 20;");

        btPrint.setStyle(" -fx-background-color: \n" +
                "        linear-gradient(#ff0084,#33001b);\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #ffffff;\n" +
                "    -fx-font-weight: 700;\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-padding: 10 20 10 20;"
        );

        btClear.setStyle(" -fx-background-color: \n" +
                "        linear-gradient(#ff0084,#33001b);\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #ffffff;\n" +
                "    -fx-font-weight: 700;\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-padding: 10 20 10 20;"
        );

        btExit.setStyle(" -fx-background-color: \n" +
                "       linear-gradient(#ff0084,#33001b);\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #FFFFFF;\n" +
                "    -fx-font-weight: 700;\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-padding: 10 20 10 20;");


        HBox hBox = new HBox(20);
        Label label = new Label("Enter a key");
        Font font = Font.font("verdana", FontWeight.NORMAL, 22);
        label.setTextFill(Color.WHITE);
        label.setFont(font);
        label.setUnderline(true);
        label.setTextAlignment(TextAlignment.LEFT);
        final TextField comment = new TextField();
        comment.setPrefColumnCount(15);
        comment.setPromptText("Enter a key");
        hBox.getChildren().addAll(label, tfKey, btInsert, btDelete, btFind, btPrint, btClear, btExit);
        hBox.setAlignment(Pos.TOP_CENTER);
        pane.setTop(hBox);
        hBox.setAlignment(Pos.TOP_CENTER);
        BorderPane.setMargin(hBox, new Insets(10, 10, 10, 10));
        HashSet<Integer> treeVal = new HashSet<>();

        btInsert.setOnAction(e -> {     //insert button
            if (emptyTextField(tfKey)) {
                invalidKey(tfKey, "No key entered!");
            } else {
                try {
                    int key = Integer.parseInt(tfKey.getText());
                    if (BSTtree.search(key)) { // key is in the tree already
                        keyExists(tfKey, key + " already exists");
                    } else {
                        BSTtree.insert(key); // Insert a new key
                        count++;

                        nodes.add(key);
                        treeVal.add(key); // Adds value to HashSet for building AVL tree
                        for (Integer i : treeVal) {
                            k = i;
                            AVLTree.insert(i); // Builds AVL tree
                        }
                        view_avl.displayAVLTree();
                        if (treeVal.isEmpty()) {
                            view_avl.text_display("Tree is empty"); // Keeps empty message if balance is hit with no nodes to balance
                        } else {
                            int height = 0;
                            view_avl.text_display(key + " is inserted in the tree.");
                            if (count == 1) {
                                height = 1;
                            } else if (count == 2 || count == 3) {
                                height = 2;
                            } else if (count > 3 && count <= 7) {
                                height = 3;
                            } else if (count > 7) {
                                height = 4;
                            }

                            view_avl.text_displayHeight("Height = " + height);
                            view_avl.text_displayVertices("Number of Vertices = " + String.valueOf(nodes.size()));
                        }
                    }
                    tfKey.setText("");
                    tfKey.requestFocus();
                } catch (NumberFormatException ex) {
                    invalidKey(tfKey, "Key must be an integer!");
                }
            }
        });

        btFind.setOnAction(e -> {
            if (emptyTextField(tfKey)) {

                invalidKey(tfKey, "No key entered!");
            } else {
                try {
                    int key = Integer.parseInt(tfKey.getText());
                    if (BSTtree.search(key)) { // key is not in the tree
                        keyExists(tfKey, key + " Found :) ");
                    } else {
                        keyUnavailale(tfKey, key + " Not Found :( ");
                    }
                    tfKey.setText("");
                } catch (NumberFormatException ex) {
                    invalidKey(tfKey, "Key must be an integer!");
                }
            }
        });
        int height1;
        btDelete.setOnAction(e -> {     //delete button
            if (emptyTextField(tfKey)) {
                invalidKey(tfKey, "No key entered!");
            } else {
                try {
                    int key = Integer.parseInt(tfKey.getText());
                    if (!BSTtree.search(key)) { // key is not in the tree
                        keyUnavailale(tfKey, key + " does not exist");
                    } else {
                        Alert delete = new Alert(Alert.AlertType.CONFIRMATION);
                        delete.setTitle("DELETE");
                        delete.setContentText("Are you sure you want to delete " + key + " ?");
                        Optional<ButtonType> result = delete.showAndWait();
                        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                            int n = nodes.indexOf(key);
                            nodes.remove(n);
                            count--;
                            BSTtree.delete(key); // Delete a key
                            AVLTree.delete(key);

                            int height = 0;
                            if (count == 1) {
                                height = 1;
                            } else if (count == 2 || count == 3) {
                                height = 2;
                            } else if (count > 3 && count <= 7) {
                                height = 3;
                            } else if (count > 7) {
                                height = 4;
                            }

                            treeVal.remove(key); // Removes key from HashSet for when tree is rebalanced

                            if (!AVLTree.isEmpty()) { // Removes key from AVL tree if it is currently displayed
                                if (AVLTree.getSize() == 1) { // Prevents NullPointerException when removing last node
                                    AVLTree.clear();
                                } else {
//                                AVLTree.delete(key);
                                    for (Integer i : treeVal) {
                                        AVLTree.insert(i); // Builds AVL tree
                                    }
                                    view_avl.displayAVLTree();
                                    view_avl.text_display(key + " is deleted from the tree");
                                    view_avl.text_displayHeight("Height = " + height);
                                    view_avl.text_displayVertices("Number of vertices = " + String.valueOf(nodes.size()));

                                    if (treeVal.isEmpty()) {
                                        view_avl.text_display("Tree is empty"); // Keeps empty message if balance is hit with no nodes to balance
                                    } else {
                                        view_avl.text_display("");
                                    }
                                }
                            }
                        }
                    }
                    tfKey.setText("");
                } catch (NumberFormatException ex) {
                    invalidKey(tfKey, "Key must be an integer!");
                }
            }
        });


        btPrint.setOnAction(e -> {
            view_avl.displayAVLTree();
            Collections.sort(nodes);
            view_avl.text_displayAL(nodes);
        });


        btClear.setOnAction(e -> {      //clear button
            tfKey.clear();
            BSTtree.clear();
            AVLTree.clear();
            treeVal.clear();
            view_avl.displayAVLTree();
            view_avl.text_display("AVL Tree deleted");
            count = 0;
            nodes.clear();
        });

        btExit.setOnAction(e -> {       //exit button
            Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
            exit.setTitle("EXIT");
            exit.setContentText("Press OK to exit.");
            Optional<ButtonType> result = exit.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                System.exit(0);
            }
        });

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 1500, 800);
        primaryStage.setTitle("JAVAFX PROJECT: SHRADDHA ARORA and JANHAVI SRIVASTAVA"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private void invalidKey(TextField key, String alertHeader) {    //returns warning if invalid key is entered
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(alertHeader);
        alert.setContentText("Please enter an integer in the key box and try again");
        key.requestFocus();
        alert.showAndWait();
    }

    private void keyExists(TextField key, String alertHeader) {    //returns warning if key already exists
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Key Exists");
        alert.setHeaderText(alertHeader);
        alert.setContentText("Key Exists.");
        key.requestFocus();
        alert.showAndWait();
    }

    private void keyUnavailale(TextField key, String alertHeader) {    //returns warning if key is unavailable
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Key Does not Exist");
        alert.setHeaderText(alertHeader);
        alert.setContentText("Key Unavailable");
        key.requestFocus();
        alert.showAndWait();
    }


    public static void main(String[] args) {

        launch(args);
    }

    boolean emptyTextField(TextField t) {    //returns true if text field is empty
        return t.getText().trim().equals("");
    }

}







