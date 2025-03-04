package com.example._1420project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ManagementController {

    @FXML
    Label nameLabel;
    @FXML
    Label labelPassword;

    public void displayName(String username, String password) {
        nameLabel.setText("Username: " + username);
        labelPassword.setText("Password: " + password);
    }

    public void SubjectMethod() {

        System.out.println("You selected the new menu item!");
    }
}