package com.example._1420project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ManagementController {

    @FXML
    Label nameLabel;

    public void displayName(String username) {
        nameLabel.setText("Hello: " + username);
    }
}