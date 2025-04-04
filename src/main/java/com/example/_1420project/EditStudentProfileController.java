package com.example._1420project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

public class EditStudentProfileController {

    @FXML private TextField addressField;
    @FXML private TextField telephoneField;
    @FXML private Label statusLabel;

    //helps with excel sheet input/output
    private final EditStudentList studentListHandler;

    public EditStudentProfileController() throws IOException {
        studentListHandler = new EditStudentList();
    }

    //pre-fills popup box with current address and phone number
    @FXML
    public void initialize() throws IOException {
        String studentId = UserSession.getInstance().getUserId();
        Student student = studentListHandler.viewStudentProfile(studentId);

        if (student != null) {
            addressField.setText(student.getStudentAddress());
            telephoneField.setText(student.getStudentTelephone());
        }
    }

    //updates address and phone number
    @FXML
    private void handleSave() throws IOException {
        String studentId = UserSession.getInstance().getUserId();
        String newAddress = addressField.getText();
        String newPhone = telephoneField.getText();

        studentListHandler.editStudent(studentId, newAddress, newPhone);
        statusLabel.setText("Changes saved successfully!");
    }
}
