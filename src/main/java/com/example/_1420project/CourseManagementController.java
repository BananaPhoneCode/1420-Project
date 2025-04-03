package com.example._1420project;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;

public class CourseManagementController {
    @FXML
    private ListView<String> coursesListView; //allows user to scroll through courses
    @FXML
    private ListView<String> EnrollmentListView; //allows user to scroll through enrollment

    // Course Table
    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, String> CourseName;
    @FXML private TableColumn<Course, String> CourseCode;

    String [] courses = {"Calculus 1", "Literature Basics", "Literature Basics", "Literature Basics", "Introduction to Programming", "Introduction to Chemistry", "Introduction to Chemistry", "Introduction to Chemistry", "Introduction to French", "Introduction to French", "Water Resources"};

    @FXML
    public void CourseMethod() {
        coursesListView.getItems().addAll(courses);

    }
}
