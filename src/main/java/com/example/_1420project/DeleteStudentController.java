package com.example._1420project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeleteStudentController {

    @FXML private TextField studentIdField;
    @FXML private Label statusLabel;

    private EditStudentList studentListHandler;

    public DeleteStudentController() {
        studentListHandler = new EditStudentList(); // safe, doesn't throw
    }

    @FXML
    private void handleDeleteStudent() {
        String studentId = studentIdField.getText().trim();

        if (studentId.isEmpty()) {
            statusLabel.setText("Student ID cannot be empty.");
            return;
        }

        try {
            boolean deleted = studentListHandler.deleteStudent(studentId);
            if (deleted) {
                statusLabel.setText("Student deleted successfully.");
            } else {
                statusLabel.setText("Student not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error occurred during deletion.");
        }
    }
}
