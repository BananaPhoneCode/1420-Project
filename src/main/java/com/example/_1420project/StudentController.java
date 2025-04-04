package com.example._1420project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class StudentController {
//sets up the buttons on the page
    @FXML private Button viewProfileButton;
    @FXML private Button editProfileButton;
    @FXML private Button changePasswordButton;
    @FXML private Button addStudentButton;
    @FXML private Button deleteStudentButton;

    //role-based views management
    @FXML
    public void initialize() {
        String role = UserSession.getInstance().getRole();

        // hide everything by default
        viewProfileButton.setVisible(false);
        editProfileButton.setVisible(false);
        changePasswordButton.setVisible(false);
        addStudentButton.setVisible(false);
        deleteStudentButton.setVisible(false);

        //based on role, show/hide buttons by changing visibility settings
        switch (role) {
            case "admin":
                addStudentButton.setVisible(true);
                deleteStudentButton.setVisible(true);
                break;
            case "student":
                viewProfileButton.setVisible(true);
                editProfileButton.setVisible(true);
                changePasswordButton.setVisible(true);
                break;
            case "faculty":
                viewProfileButton.setVisible(true);
                break;
        }
    }

    //fxml formatting for all of the student buttons
    @FXML
    private void editStudent() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/_1420project/EditStudentProfile.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Edit Profile");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void viewStudent() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/_1420project/ViewStudentProfile.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("View Student Profile");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void openChangePasswordWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentPassword.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Change Password");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openAddStudentWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddStudent.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Student");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openDeleteStudentWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteStudent.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Delete Student");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
