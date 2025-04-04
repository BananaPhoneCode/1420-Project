package com.example._1420project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

public class AddStudentController {
    //fxml formatting
    @FXML private TextField nameField, idField, addressField, telephoneField, emailField, semesterField, subjectsField, thesisField, progressField, passwordField;
    @FXML private ComboBox<String> levelComboBox;
    @FXML private Label statusLabel;

    //manages excel file
    private final EditStudentList studentListHandler;

    public AddStudentController() throws IOException {
        studentListHandler = new EditStudentList();
    }

    //internal drop down boxes
    @FXML
    public void initialize() {
        levelComboBox.getItems().addAll("Undergraduate", "Graduate");
        levelComboBox.setOnAction(e -> {
            String level = levelComboBox.getValue();
            thesisField.setDisable(!"Graduate".equals(level));
        });
    }

    //accepts text fields from admin
    @FXML
    private void handleAddStudent() throws IOException {
        String name = nameField.getText();
        String id = idField.getText();
        String address = addressField.getText();
        String telephone = telephoneField.getText();
        String email = emailField.getText();
        String level = levelComboBox.getValue();
        String semester = semesterField.getText();
        String subjects = subjectsField.getText();
        String thesis = thesisField.getText();
        String progress = progressField.getText();
        String password = passwordField.getText();

        //turnary operator to decide tuition based on academic level
        String tuition = level.toLowerCase().contains("undergrad") ? "$5000" : "$4000";

        //doesnt accept blank fields
        if (name.isEmpty() || id.isEmpty() || level == null || password.isEmpty()) {
            statusLabel.setText("Please fill in all required fields.");
            return;
        }

        Student newStudent = new Student(name, id, address, telephone, tuition, subjects, email, password, semester, subjects, level, thesis, progress);
        studentListHandler.addStudent(newStudent);
        //confirmation message
        statusLabel.setText("Student added successfully.");
    }
}