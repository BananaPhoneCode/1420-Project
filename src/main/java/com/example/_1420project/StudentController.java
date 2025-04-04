package com.example._1420project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentController {

    @FXML private Label studentIdText;
    @FXML private Text studentNameText;
    @FXML private Text studentAddressText;
    @FXML private Text studentTelephoneText;
    @FXML private Text studentTuitionText;
    @FXML private Text studentCoursesText;
    @FXML private Text studentEmailText;
    @FXML private Text studentSemText;
    @FXML private Text studentSubText;
    @FXML private Text studentLevText;
    @FXML private Text studentTitleText;
    @FXML private Text studentProgText;

    @FXML private Button viewProfileButton;
    @FXML private Button editProfileButton;
    @FXML private Button changePasswordButton;
    @FXML private Button addStudentButton;

    private EditStudentList studentListHandler;

    public StudentController() throws IOException {
        studentListHandler = new EditStudentList();
    }

    @FXML
    public void initialize() {
        String role = UserSession.getInstance().getRole();
        if (role.equals("admin")){
            viewProfileButton.setVisible(false);
            editProfileButton.setVisible(false);
            changePasswordButton.setVisible(false);
            addStudentButton.setVisible(true);

        }else if (role.equals("student")){
            viewProfileButton.setVisible(true);
            editProfileButton.setVisible(true);
            changePasswordButton.setVisible(true);
            addStudentButton.setVisible(false);

        }else if (role.equals("faculty")){
            viewProfileButton.setVisible(true);
        }
    }

    @FXML
    private void viewStudentProfile() throws IOException {
        String studentId = UserSession.getInstance().getUserId();
        Student student = studentListHandler.viewStudentProfile(studentId);

        if (student != null) {
            studentIdText.setText(student.getStudentId());
            studentNameText.setText(student.getStudentName());
            studentAddressText.setText(student.getStudentAddress());
            studentTelephoneText.setText(student.getStudentTelephone());
            studentTuitionText.setText(student.getStudentTuition());
            studentCoursesText.setText(student.getStudentSub());
            studentEmailText.setText(student.getStudentEmail());
            studentSemText.setText(student.getStudentSem());
            studentSubText.setText(student.getStudentSub());
            studentLevText.setText(student.getStudentLev());
            studentTitleText.setText(student.getStudentTitle());
            studentProgText.setText(student.getStudentProg());
        } else {
            studentNameText.setText("Student not found");
        }
    }

    @FXML
    private void editStudentProfile() throws IOException {
        String studentId = UserSession.getInstance().getUserId();
        String newAddress = studentAddressText.getText();
        String newTelephone = studentTelephoneText.getText();

        studentListHandler.editStudent(studentId, newAddress, newTelephone);
    }

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

    @FXML private void dashboard() {}
    @FXML private void subject() {}
    @FXML private void course() {}
    @FXML private void student() {}
    @FXML private void faculty() {}
    @FXML private void event() {}
}
