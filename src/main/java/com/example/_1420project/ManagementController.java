package com.example._1420project;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ManagementController implements Initializable {
    private static final Duration ANIMATION_DURATION = Duration.seconds(0.5);
    @FXML
    ImageView menu;
    @FXML
    ImageView DashManagement;
    @FXML
    ImageView SubManagement;
    @FXML
    ImageView CouManagement;
    @FXML
    ImageView StuManagement;
    @FXML
    ImageView FacManagement;
    @FXML
    ImageView EveManagement;

    //Subject Class @FXML:
    @FXML
    private TableView<Subject> subjectTable = new TableView<>();
    @FXML
    private TableColumn<Subject, String> SubjectName = new TableColumn<>();
    @FXML
    private TableColumn<Subject, String> SubjectCode = new TableColumn<>();
    Button subjectAdd = new Button();
    @FXML
    TextField subjectCodeTxt = new TextField();
    @FXML
    TextField subjectNameTxt = new TextField();
    @FXML
    TextField subjectDeleteTxt = new TextField();
    @FXML
    TextField subjectSearchBar = new TextField();
    @FXML
    AnchorPane subjectAddButtonMenu, subjectDeleteButtonMenu, subjectEditButtonMenu;

    //COURSE PAGE - AISHA
    //Course Class @ FXML;
    //Course Table
    @FXML
    private TableView<Course> courseTable = new TableView<>();
    @FXML
    private TableColumn<Course, String> CourseName = new TableColumn<>();
    @FXML
    private TableColumn<Course, String> CourseCode = new TableColumn<>();
    @FXML
    private TableColumn<Course, String> CourseLecture = new TableColumn<>();
    @FXML
    private TableColumn<Course, String> CourseSection = new TableColumn<>();
    @FXML
    private TableColumn<Course, String> CourseSubjectName = new TableColumn<>();

    @FXML
    AnchorPane courseAddButtonMenu, courseDeleteButtonMenu, courseEditButtonMenu;

    @FXML
    TextField courseCodeTxt = new TextField();
    @FXML
    TextField courseNameTxt = new TextField();
    @FXML
    TextField courseLectureTxt = new TextField();
    @FXML
    TextField courseSectionTxt = new TextField();
    @FXML
    TextField courseDeleteTxt = new TextField();
    @FXML
    TextField courseSearchBar = new TextField();

    @FXML
    //private AnchorPane pane3, pane4;
    ObservableList<Course> courseTableViewList = FXCollections.observableArrayList();
    ObservableList<Course> courseSearchTableViewList = FXCollections.observableArrayList();
    ArrayList<Course> currentCourseList = new EditCourseList().Generate();

    @FXML
    private ListView<String> coursesListView = new ListView<>(); //allows user to scroll through courses

    //old way
    @FXML
    private ListView<String> EnrollmentListView = new ListView<>(); //allows user to scroll through enrollment
    //private String[] Courses = {"Calculus 1", "Literature Basics", "Introduction to Programming", "Introduction to Chemistry", "Introduction to French", "Water Resources"};

    @FXML
    private AnchorPane pane1, pane2;
    ObservableList<Subject> subjectTableViewList = FXCollections.observableArrayList();
    ObservableList<Subject> subjectSearchTableViewList = FXCollections.observableArrayList();
    ArrayList<Subject> currentSubjectList = new EditSubjectList().Generate();

    public ManagementController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Initialize Subject List and Subject Admin Menu Buttons
        subjectTableViewList.addAll(currentSubjectList);
        SubjectCode.setCellValueFactory(new PropertyValueFactory<Subject, String>("SubjectCode"));
        SubjectName.setCellValueFactory(new PropertyValueFactory<Subject, String>("SubjectName"));
        subjectTable.setItems(subjectTableViewList);

        //COURSE
        //Course Table Setup
        courseTableViewList.addAll(currentCourseList);
        CourseCode.setCellValueFactory(new PropertyValueFactory<Course, String>("CourseCode"));
        CourseName.setCellValueFactory(new PropertyValueFactory<Course, String>("CourseName"));
        CourseSection.setCellValueFactory(new PropertyValueFactory<Course, String>("CourseSection"));
        CourseLecture.setCellValueFactory(new PropertyValueFactory<Course, String>("CourseLecture"));
        courseTable.setItems(courseTableViewList);

        pane1.setVisible(false);

        // Initial animations
        createFadeTransition(pane1, 1, 0, ANIMATION_DURATION).play();
        createTranslateTransition(pane2, -600, ANIMATION_DURATION).play();

        // Menu click event
        menu.setOnMouseClicked(event -> {
            pane1.setVisible(true);
            createFadeTransition(pane1, 0, 0.15, ANIMATION_DURATION).play();
            createTranslateTransition(pane2, +600, ANIMATION_DURATION).play();
        });

        // Pane1 click event
        pane1.setOnMouseClicked(event -> {
            FadeTransition fadeTransition = createFadeTransition(pane1, 0.15, 0, ANIMATION_DURATION);
            fadeTransition.play();
            fadeTransition.setOnFinished(event1 -> pane1.setVisible(false));
            createTranslateTransition(pane2, -600, ANIMATION_DURATION).play();
        });

    }

    private FadeTransition createFadeTransition(Node node, double from, double to, Duration duration) {
        FadeTransition fadeTransition = new FadeTransition(duration, node);
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);
        return fadeTransition;
    }

    private TranslateTransition createTranslateTransition(Node node, double byX, Duration duration) {
        TranslateTransition translateTransition = new TranslateTransition(duration, node);
        translateTransition.setByX(byX);
        return translateTransition;
    }

    public void dashboard(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
    }

    public void subject(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SubjectAdmin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
    }

    //course
    public void course(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Course.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
    }

    public void student(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Student.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
    }

    public void faculty(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Faculty.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
    }

    public void event(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Event.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
    }

    //Subject Page Buttons
    public void subjectAddButton(ActionEvent event) throws IOException {
        subjectTable.getItems().add(new Subject(subjectCodeTxt.getText(), subjectNameTxt.getText()));
        new EditSubjectList().Add(subjectCodeTxt.getText(), subjectNameTxt.getText());
    }

    public void subjectRemoveButton(ActionEvent event) throws IOException {
        if (subjectTable.getSelectionModel().getSelectedItem().toString().isBlank()) {
        } else {
            System.err.println("DELETE " + subjectTable.getSelectionModel().getSelectedItem().getSubjectName());
            new EditSubjectList().Remove(subjectTable.getSelectionModel().getSelectedItem());
            currentSubjectList.remove(subjectTable.getSelectionModel().getSelectedItem());
            subjectTableViewList.setAll(currentSubjectList);
            subjectTable.setItems(subjectTableViewList);
        }
    }

    public void searchSubjectList(ActionEvent event) throws IOException {
        subjectSearchTableViewList.clear();
        for (Subject subject : currentSubjectList) {
            if (subject.getSubjectName().toUpperCase().contains(subjectSearchBar.getText().toUpperCase())
                    || subject.getSubjectCode().toUpperCase().contains(subjectSearchBar.getText().toUpperCase())) {
                subjectSearchTableViewList.add(subject);
            }
        }
        if (subjectSearchBar.getText().equals("")) {
            subjectTable.setItems(subjectTableViewList);
        } else {
            subjectTable.setItems(subjectSearchTableViewList);
        }
    }

    static boolean subjectAddButtonState = false;
    static boolean subjectDeleteButtonState = false;
    static boolean subjectEditButtonState = false;

    public void showAddButton(ActionEvent event) throws IOException {
        if (!subjectAddButtonState) {
            subjectAddButtonState = true;
            subjectDeleteButtonState = false;
            subjectEditButtonState = false;
            subjectAddButtonMenu.setVisible(true);
            subjectDeleteButtonMenu.setVisible(false);
            subjectEditButtonMenu.setVisible(false);
        } else {
            subjectAddButtonState = false;
            subjectAddButtonMenu.setVisible(false);
        }
    }

    public void showDeleteButton(ActionEvent event) throws IOException {
        if (!subjectDeleteButtonState) {
            subjectAddButtonState = false;
            subjectDeleteButtonState = true;
            subjectEditButtonState = false;
            subjectAddButtonMenu.setVisible(false);
            subjectDeleteButtonMenu.setVisible(true);
            subjectEditButtonMenu.setVisible(false);
        } else {
            subjectDeleteButtonState = false;
            subjectDeleteButtonMenu.setVisible(false);
        }
    }

    public void showEditButton(ActionEvent event) throws IOException {
        if (!subjectEditButtonState) {
            subjectAddButtonState = false;
            subjectDeleteButtonState = false;
            subjectEditButtonState = true;
            subjectAddButtonMenu.setVisible(false);
            subjectDeleteButtonMenu.setVisible(false);
            subjectEditButtonMenu.setVisible(true);
        } else {
            subjectEditButtonState = false;
            subjectEditButtonMenu.setVisible(false);
        }
    }
    //End of Subject Page Buttons


    //Start of Course Page Buttons
    //Course Add Method
    public void courseAddButton(ActionEvent event) throws IOException {
        courseTable.getItems().add(new Course(courseCodeTxt.getText(), courseNameTxt.getText(), courseSectionTxt.getText(), courseLectureTxt.getText()));
        new EditSubjectList().Add(courseCodeTxt.getText(), courseNameTxt.getText());
    }

    //Course Remove Method
    public void courseRemoveButton(ActionEvent event) throws IOException {
        if (courseTable.getSelectionModel().getSelectedItem().toString().isBlank()) {
        } else {
            System.err.println("DELETE " + subjectTable.getSelectionModel().getSelectedItem().getSubjectName());
            new EditSubjectList().Remove(subjectTable.getSelectionModel().getSelectedItem());
            currentSubjectList.remove(subjectTable.getSelectionModel().getSelectedItem());
            subjectTableViewList.setAll(currentSubjectList);
            subjectTable.setItems(subjectTableViewList);
        }
    }
    //Method to search course
    public void searchCourseList(ActionEvent event) throws IOException {
        courseSearchTableViewList.clear();
        for (Course course : currentCourseList) {
            if (course.getCourseName().toUpperCase().contains(courseSearchBar.getText().toUpperCase())
                    || course.getCourseCode().toUpperCase().contains(courseSearchBar.getText().toUpperCase())) {
                courseSearchTableViewList.add(course);
            }
        }
        //course search bar
        if (courseSearchBar.getText().equals("")) {
            subjectTable.setItems(subjectTableViewList);
        } else {
            courseTable.setItems(courseSearchTableViewList);
        }
    }

    static boolean courseAddButtonState = false;
    static boolean courseDeleteButtonState = false;
    static boolean courseEditButtonState = false;

    public void CourseshowAddButton(ActionEvent event) throws IOException {
        if (!courseAddButtonState) {
            courseAddButtonState = true;
            courseDeleteButtonState = false;
            courseEditButtonState = false;
            courseAddButtonMenu.setVisible(true);
            courseDeleteButtonMenu.setVisible(false);
            courseEditButtonMenu.setVisible(false);
        } else {
            courseAddButtonState = false;
            courseAddButtonMenu.setVisible(false);
        }
    }


    public void CourseshowDeleteButton(ActionEvent event) throws IOException {
        if (!courseDeleteButtonState) {
            courseAddButtonState = false;
            courseDeleteButtonState = true;
            courseEditButtonState = false;
            courseAddButtonMenu.setVisible(false);
            courseDeleteButtonMenu.setVisible(true);
            courseEditButtonMenu.setVisible(false);
        } else {
            courseDeleteButtonState = false;
            courseDeleteButtonMenu.setVisible(false);
        }
    }

    public void CourseEditButton(ActionEvent event) throws IOException {
        if (!courseEditButtonState) {
            courseAddButtonState = false;
            courseDeleteButtonState = false;
            courseEditButtonState = true;
            courseAddButtonMenu.setVisible(false);
            courseDeleteButtonMenu.setVisible(false);
            courseEditButtonMenu.setVisible(true);
        } else {
            courseEditButtonState = false;
            courseEditButtonMenu.setVisible(false);
        }
    }
}
