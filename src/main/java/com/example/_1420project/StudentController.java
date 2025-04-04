package com.example._1420project;

import javafx.event.ActionEvent;
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
    @FXML private Text studentGradeText;
    @FXML private Text studentSemText;
    @FXML private Text studentSubText;
    @FXML private Text studentLevText;
    @FXML private Text studentTitleText;
    @FXML private Text studentProgText;
    @FXML private Button viewProfileButton;
    @FXML private Button editProfileButton;

    private EditStudentList studentListHandler;

    public StudentController() throws IOException {
        studentListHandler = new EditStudentList();
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
            studentCoursesText.setText(student.getStudentCourses());
            studentEmailText.setText(student.getStudentEmail());
            studentGradeText.setText(student.getStudentGrade());
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
    private void editStudent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/_1420project/EditStudentProfile.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Edit Profile");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    private void viewStudent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/_1420project/ViewStudentProfile.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("View Student Profile");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }


    // navigation/event handlers (build later)
    @FXML private void dashboard(ActionEvent event) {}
    @FXML private void subject(ActionEvent event) {}
    @FXML private void course(ActionEvent event) {}
    @FXML private void student(ActionEvent event) {}
    @FXML private void faculty(ActionEvent event) {}
    @FXML private void event(ActionEvent event) {}
    @FXML private void viewEnrolledClasses(ActionEvent event) {}
}
