package com.example._1420project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
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
    @FXML private Label progressLabel;
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
            levelLabel.setText("Academic Level: " + student.getStudentLev());
            semesterLabel.setText("Semester: " + student.getStudentSem());
            subjectsLabel.setText("Subjects: " + student.getStudentSub());
            progressLabel.setText("Progress: " + student.getStudentProg());

            String level = student.getStudentLev().toLowerCase().trim();
            String tuitionAmount;
            String thesisTitle;

            if (level.contains("undergraduate")) {
                tuitionAmount = "$5000";
                thesisTitle = "N/A"; // undergrads don’t have thesis titles
            } else if (level.contains("graduate")) {
                tuitionAmount = "$4000";
                thesisTitle = student.getStudentTitle();
            } else {
                tuitionAmount = "Unknown";
                thesisTitle = "Unknown";
            }

            tuitionLabel.setText("Tuition: " + tuitionAmount);
            titleLabel.setText("Thesis Title: " + thesisTitle);

        } else {
            nameLabel.setText("Student not found.");
        }
    }

    // ✅ Opens Enrolled Courses Window
    @FXML
    private void openEnrolledCoursesWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewEnrolledCourses.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Enrolled Courses");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Failed to open enrolled courses window.");
        }
    }

    // 🔴 Reusable alert for errors
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
