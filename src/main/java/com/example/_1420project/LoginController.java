package com.example._1420project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.Arrays;

public class LoginController {
    @FXML
    TextField Username;
    @FXML
    TextField Password;
    private String currentRole;

    // hardcoded admin login
    private static final String ADMIN_USERNAME = "JohnAbrams";
    private static final String ADMIN_PASSWORD = "admin123";

    // login data from excel
    private static final String[] studentIDs = {
            "S20250001", "S20250002", "S20250003", "S20250004", "S20250005",
            "S20250006", "S20250007", "S20250008", "S20250009", "S20250010"
    };

    private static final String[] facultyIDs = {
            "F10001", "F10002", "F10003", "F10004", "F10005" // Example faculty IDs
    };

    private static final String DEFAULT_PASSWORD = "default123";

    // check if fields are blank
    private boolean areFieldsValid(String username, String password) {
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            showInvalidLoginAlert("Username or password cannot be empty.");
            return false;
        }
        return true;
    }

    private boolean authenticateUser(String username, String password) {
        //authentication
        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            assignRole("admin");
            return true;
        }

        if (Arrays.asList(studentIDs).contains(username) && password.equals(DEFAULT_PASSWORD)) {
            assignRole("student");
            return true;
        }

        if (Arrays.asList(facultyIDs).contains(username) && password.equals(DEFAULT_PASSWORD)) {
            assignRole("faculty");
            return true;
        }

        showInvalidLoginAlert("Invalid username or password. Please try again.");
        return false;
    }

    private void assignRole(String role) {
        this.currentRole = role;
    }

    public void login(ActionEvent event) throws IOException {
        String username = Username.getText();
        String password = Password.getText();

        if (!areFieldsValid(username, password)) {
            return; //checks for blanks
        }

        //authenticate the user
        if (authenticateUser(username, password)) {
            switch (this.currentRole) {
                case "admin":
                    navigateToManagement(event);
                    break;
                case "student":
                    navigateToUserDashboard(event);
                    break;
                case "faculty":
                    navigateToFacultyDashboard(event);
                    break;
            }
        }
    }

    private void navigateToManagement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Management.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void navigateToUserDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void navigateToFacultyDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FacultyDashboard.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showInvalidLoginAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
