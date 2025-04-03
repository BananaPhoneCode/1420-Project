package com.example._1420project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.ArrayList;

public class StudentController {
    @FXML private TextField studentIdField;
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
        String studentId = studentIdField.getText();
        ArrayList<Student> students = studentListHandler.Generate();

        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
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
                return;
            }
        }
        studentNameText.setText("Student not found");
    }

    @FXML
    private void editStudentProfile() throws IOException {
        String studentId = studentIdField.getText();
        String newAddress = studentAddressText.getText();
        String newTelephone = studentTelephoneText.getText();

        studentListHandler.editStudent(studentId, newAddress, newTelephone);
    }
}

