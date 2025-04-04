package com.example._1420project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

public class StudentPasswordController {

    @FXML private PasswordField currentPasswordField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label statusLabel;

    private final EditStudentList studentListHandler;

    public StudentPasswordController() throws IOException {
        studentListHandler = new EditStudentList();
    }

    @FXML
    public void handleStudentPassword() throws IOException {
        String studentId = UserSession.getInstance().getUserId();
        Student student = studentListHandler.viewStudentProfile(studentId);

        String current = currentPasswordField.getText();
        String newPass = newPasswordField.getText();
        String confirm = confirmPasswordField.getText();

        if (student == null) {
            statusLabel.setText("User not found.");
        } else if (!student.getStudentPassword().equals(current)) {
            statusLabel.setText("Current password is incorrect.");
        } else if (!newPass.equals(confirm)) {
            statusLabel.setText("Passwords do not match.");
        } else {
            studentListHandler.updateStudentPassword(studentId, newPass);
            statusLabel.setText("Password changed successfully.");
        }
    }
}
