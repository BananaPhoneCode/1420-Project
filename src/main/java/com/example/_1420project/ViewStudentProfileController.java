package com.example._1420project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

public class ViewStudentProfileController {

    @FXML private Label nameLabel;
    @FXML private Label idLabel;
    @FXML private Label addressLabel;
    @FXML private Label telephoneLabel;
    @FXML private Label emailLabel;
    @FXML private Label levelLabel;
    @FXML private Label semesterLabel;
    @FXML private Label subjectsLabel;
    @FXML private Label titleLabel;
    @FXML private Label programLabel;
    @FXML private Label coursesLabel;
    @FXML private Label gradesLabel;
    @FXML private Label tuitionLabel;

    private final EditStudentList studentListHandler;

    public ViewStudentProfileController() throws IOException {
        studentListHandler = new EditStudentList();
    }

    @FXML
    public void initialize() throws IOException {
        String studentId = UserSession.getInstance().getUserId();
        Student student = studentListHandler.viewStudentProfile(studentId);

        if (student != null) {
            nameLabel.setText("Name: " + student.getStudentName());
            idLabel.setText("ID: " + student.getStudentId());
            addressLabel.setText("Address: " + student.getStudentAddress());
            telephoneLabel.setText("Telephone: " + student.getStudentTelephone());
            emailLabel.setText("Email: " + student.getStudentEmail());
            levelLabel.setText("Level: " + student.getStudentLev());
            semesterLabel.setText("Semester: " + student.getStudentSem());
            subjectsLabel.setText("Subjects: " + student.getStudentSub());
            gradesLabel.setText("Grades: " + student.getStudentGrade());
            coursesLabel.setText("Courses: " + student.getStudentCourses());

            String level = student.getStudentLev().toLowerCase().trim();
            String tuitionAmount;
            String thesisTitle;
            String programDisplay;

            if (level.contains("undergraduate")) {
                tuitionAmount = "$5000";
                thesisTitle = "N/A"; // ✅ hide thesis
                programDisplay = student.getStudentProg(); // ✅ show % as "Program"
            } else if (level.contains("graduate")) {
                tuitionAmount = "$4000";
                thesisTitle = student.getStudentTitle(); // ✅ show real thesis
                programDisplay = student.getStudentProg(); // ✅ show program name
            } else {
                tuitionAmount = "Unknown";
                thesisTitle = "Unknown";
                programDisplay = "Unknown";
            }

            titleLabel.setText("Thesis Title: " + thesisTitle);
            programLabel.setText("Program: " + programDisplay);
            tuitionLabel.setText("Tuition: " + tuitionAmount);

        } else {
            nameLabel.setText("Student not found.");
        }
    }
}
