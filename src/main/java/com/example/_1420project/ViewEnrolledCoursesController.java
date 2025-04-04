package com.example._1420project;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Arrays;

public class ViewEnrolledCoursesController {

    @FXML private ListView<String> coursesListView;

    private final EditStudentList studentListHandler;

    public ViewEnrolledCoursesController() throws IOException {
        studentListHandler = new EditStudentList();
    }

    @FXML
    public void initialize() throws IOException {
        String studentId = UserSession.getInstance().getUserId();
        Student student = studentListHandler.viewStudentProfile(studentId);

        if (student != null && student.getStudentSub() != null) {
            String[] courses = student.getStudentSub().split(",");
            coursesListView.getItems().addAll(Arrays.asList(courses));
        }
    }

    @FXML
    private void handleClose(ActionEvent event) {
        Stage stage = (Stage) coursesListView.getScene().getWindow();
        stage.close();
    }
}
