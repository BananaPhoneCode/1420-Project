package com.example._1420project;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class CourseManagementController {
    @FXML
    private ListView<String> coursesListView; //allows user to scroll through courses
    @FXML
    private ListView<String> EnrollmentListView; //allows user to scroll through enrollment

    String [] courses = {"Calculus 1", "Literature Basics", "Literature Basics", "Literature Basics", "Introduction to Programming", "Introduction to Chemistry", "Introduction to Chemistry", "Introduction to Chemistry", "Introduction to French", "Introduction to French", "Water Resources"};

    @FXML
    public void CourseMethod() {
        coursesListView.getItems().addAll(courses);

    }
}
