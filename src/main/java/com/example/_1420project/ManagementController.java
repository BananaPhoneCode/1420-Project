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
import java.util.*;

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
    TextField subjectCodeEditTxt = new TextField();
    @FXML
    TextField subjectNameEditTxt = new TextField();
    @FXML
    TextField subjectSearchBar = new TextField();
    @FXML
    AnchorPane subjectAddButtonMenu, subjectDeleteButtonMenu, subjectEditButtonMenu, subjectAdminMenu;
    @FXML
    Label subjectAddAdminLabel = new Label();
    @FXML
    Label subjectEditAdminLabel = new Label();
    @FXML
    Label subjectDeleteAdminLabel = new Label();
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
    private TableColumn<Course, String> CourseCapacity = new TableColumn<>();
    @FXML
    private TableColumn<Course, String> CourseLocation = new TableColumn<>();
    @FXML
    private TableColumn<Course, String> CourseTeacher = new TableColumn<>();

    @FXML
    AnchorPane courseAddButtonMenu, courseDeleteButtonMenu, courseEditButtonMenu;

    //all data attributes text field initialization
    @FXML
    TextField courseCodeTxt = new TextField();
    @FXML
    TextField courseNameTxt = new TextField();
    @FXML
    TextField courseLectureTxt = new TextField();
    @FXML
    TextField courseSectionTxt = new TextField();
    @FXML
    TextField courseCapacityTxt = new TextField();
    @FXML
    TextField courseLocationTxt = new TextField();
    @FXML
    TextField courseTeacherTxt = new TextField();

    @FXML
    TextField courseDeleteTxt = new TextField();
    @FXML
    TextField courseSearchBar = new TextField();

    //Editing text fields
    @FXML
    TextField courseCodeEditTxt = new TextField();
    @FXML
    TextField courseNameEditTxt = new TextField();
    @FXML
    TextField courseLectureEditTxt = new TextField();
    @FXML
    TextField courseSectionEditTxt= new TextField();
    @FXML
    TextField courseCapacityEditTxt= new TextField();
    @FXML
    TextField courseLocationEditTxt= new TextField();
    @FXML
    TextField courseTeacherEditTxt= new TextField();

    @FXML
    //private AnchorPane pane3, pane4;
    ObservableList<Course> courseTableViewList = FXCollections.observableArrayList();
    ObservableList<Course> courseSearchTableViewList = FXCollections.observableArrayList();
    ArrayList<Course> currentCourseList = new EditCourseList().Generate();

    @FXML
    private ListView<String> coursesListView = new ListView<>(); //allows user to scroll through courses

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
        currentSubjectList.sort(new Comparator<Subject>() {
            @Override
            public int compare(Subject o1, Subject o2) {
                return o1.getSubjectName().compareToIgnoreCase(o2.getSubjectName());
            }
        });
        subjectTableViewList.addAll(currentSubjectList);
        SubjectCode.setCellValueFactory(new PropertyValueFactory<Subject, String>("SubjectCode"));
        SubjectName.setCellValueFactory(new PropertyValueFactory<Subject, String>("SubjectName"));
        subjectTable.setItems(subjectTableViewList);
        //if(!Objects.equals(UserSession.getInstance().getRole(), "admin")){subjectAdminMenu.setVisible(false);}

        //COURSE
        //Course Table Setup
        courseTableViewList.addAll(currentCourseList);
        CourseCode.setCellValueFactory(new PropertyValueFactory<Course, String>("CourseCode"));
        CourseName.setCellValueFactory(new PropertyValueFactory<Course, String>("CourseName"));
        CourseSection.setCellValueFactory(new PropertyValueFactory<Course, String>("CourseSection"));
        CourseLecture.setCellValueFactory(new PropertyValueFactory<Course, String>("CourseLecture"));
        CourseCapacity.setCellValueFactory(new PropertyValueFactory<Course, String>("CourseCapacity"));
        CourseLocation.setCellValueFactory(new PropertyValueFactory<Course, String>("CourseLocation"));
        CourseTeacher.setCellValueFactory(new PropertyValueFactory<Course, String>("CourseTeacher"));

        courseTable.setItems(courseTableViewList);

        pane1.setVisible(false);

        // Initial animations
        createFadeTransition(pane1, 1, 0, ANIMATION_DURATION).play();
        createTranslateTransition(pane2, -600, ANIMATION_DURATION).play();

        // Menu click event
        menu.setOnMouseClicked(event -> {
            pane1.setVisible(true);
            pane2.setVisible(true);
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

    public void event(ActionEvent event) {
        try {
            // Load the FXML file for the Event Management interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Event.fxml"));
            Parent root = loader.load();

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

            // You can show a simple error dialog to the user if something goes wrong
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("FXML Loading Error");
            alert.setContentText("There was an error loading the Event Management page. Please try again.");
            alert.showAndWait();
        }
    }


    //Subject Page Buttons
    public void subjectAddButton(ActionEvent event) throws IOException {
        boolean copy = false;
        for(Subject subject : currentSubjectList){
            if(Objects.equals(subjectCodeTxt.getText(), subject.getSubjectCode())){
                copy = true;
            }
        }
        if(copy){subjectAddAdminLabel.setText("Invalid Subject: subject code is not unique");}
        else if(subjectCodeTxt.getText().isEmpty()||subjectNameTxt.getText().isEmpty()){subjectAddAdminLabel.setText("Invalid Subject: missing parameter");}
        else{
            Subject tempSubject = new Subject(subjectCodeTxt.getText(), subjectNameTxt.getText());
            subjectTable.getItems().add(tempSubject);
            new EditSubjectList().Add(subjectCodeTxt.getText(), subjectNameTxt.getText());
            currentSubjectList.add(tempSubject);
            subjectAddAdminLabel.setText("Enter the subject code and name for the new subject.");
        }
    }

    public void subjectRemoveButton(ActionEvent event) throws IOException {
        if (subjectTable.getSelectionModel().getSelectedItem().getSubjectName().isBlank()){subjectDeleteAdminLabel.setText("Invalid Delete: no selected subject");}
        else {
            System.err.println("DELETE " + subjectTable.getSelectionModel().getSelectedItem().getSubjectName());
            new EditSubjectList().Remove(subjectTable.getSelectionModel().getSelectedItem());
            currentSubjectList.remove(subjectTable.getSelectionModel().getSelectedItem());
            subjectTableViewList.setAll(currentSubjectList);
            subjectTable.setItems(subjectTableViewList);
            subjectDeleteAdminLabel.setText("Select subject from list and click delete.");
        }
    }
    public void subjectEditButton(ActionEvent event) throws IOException {
        boolean copy = false;
        for(Subject subject : currentSubjectList){
            if(Objects.equals(subjectCodeEditTxt.getText(), subject.getSubjectCode())){
                copy = true;
            }
        }
        if(copy){subjectEditAdminLabel.setText("Invalid Edit: subject code is not unique");}
        else if (subjectTable.getSelectionModel().getSelectedItems().isEmpty()){
            subjectEditAdminLabel.setText("Invalid Edit: no selected subject");
        }
        else{
            System.err.println("EDIT " + subjectTable.getSelectionModel().getSelectedItem().getSubjectName());
            new EditSubjectList().Edit(subjectTable.getSelectionModel().getSelectedItem(), subjectCodeEditTxt.getText(), subjectNameEditTxt.getText());
            for (Subject subject : currentSubjectList) {
                if(Objects.equals(subject.getSubjectCode(), subjectTable.getSelectionModel().getSelectedItem().getSubjectCode())
                 ||Objects.equals(subject.getSubjectName(), subjectTable.getSelectionModel().getSelectedItem().getSubjectName())
                ) {currentSubjectList.remove(subject); break;}
            }
            currentSubjectList.remove(new Subject(subjectTable.getSelectionModel().getSelectedItem().getSubjectCode(), subjectTable.getSelectionModel().getSelectedItem().getSubjectName()));
            Subject tempSubject = getSubject();
            currentSubjectList.add(tempSubject);
            subjectTableViewList.set(subjectTable.getSelectionModel().getSelectedIndex(), tempSubject);
            subjectTable.setItems(subjectTableViewList);
            subjectEditAdminLabel.setText("Select subject from list and enter the new subject code/name");
        }
    }

    private Subject getSubject() {
        Subject tempSubject;
        if(Objects.equals(subjectCodeEditTxt.getText(), "")){tempSubject = new Subject(subjectTable.getSelectionModel().getSelectedItem().getSubjectCode(), subjectNameEditTxt.getText());}
        else if(Objects.equals(subjectNameEditTxt.getText(), "")){tempSubject = new Subject(subjectCodeEditTxt.getText(),subjectTable.getSelectionModel().getSelectedItem().getSubjectName());}
        else{tempSubject = new Subject(subjectCodeEditTxt.getText(),subjectNameEditTxt.getText());}
        return tempSubject;
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
        courseTable.getItems().add(new Course(courseCodeTxt.getText(), courseNameTxt.getText(), courseSectionTxt.getText(), courseLectureTxt.getText(), courseCapacityTxt.getText(), courseLocationTxt.getText(), courseTeacherTxt.getText()));
        new EditCourseList().Add(courseCodeTxt.getText(), courseNameTxt.getText(), courseSectionTxt.getText(), courseLectureTxt.getText(), courseCapacityTxt.getText(), courseLocationTxt.getText(), courseTeacherTxt.getText());
    }

    //Course Remove Method
    public void courseRemoveButton(ActionEvent event) throws IOException {
        if (courseTable.getSelectionModel().getSelectedItem().toString().isBlank()) {
        } else {
            System.err.println("DELETE " + courseTable.getSelectionModel().getSelectedItem().getCourseName());
            new EditCourseList().Remove(courseTable.getSelectionModel().getSelectedItem());
            currentCourseList.remove(courseTable.getSelectionModel().getSelectedItem());
            courseTableViewList.setAll(currentCourseList);
            courseTable.setItems(courseTableViewList);
        }
    }
    //constructor for course
    private Course getCourse() {
        Course selected = courseTable.getSelectionModel().getSelectedItem();

        String code = courseCodeEditTxt.getText().isEmpty() ? selected.getCourseCode() : courseCodeEditTxt.getText();
        String name = courseNameEditTxt.getText().isEmpty() ? selected.getCourseName() : courseNameEditTxt.getText();
        String section = courseSectionEditTxt.getText().isEmpty() ? selected.getCourseSection() : courseSectionEditTxt.getText();
        String lecture = courseLectureEditTxt.getText().isEmpty() ? selected.getCourseLecture() : courseLectureEditTxt.getText();
        String capacity = courseCapacityEditTxt.getText().isEmpty() ? selected.getCourseCapacity() : courseCapacityEditTxt.getText();
        String location = courseLocationEditTxt.getText().isEmpty() ? selected.getCourseLocation() : courseLocationEditTxt.getText();
        String teacher = courseTeacherEditTxt.getText().isEmpty() ? selected.getCourseTeacher() : courseTeacherEditTxt.getText();

        return new Course(code, name, section, lecture, capacity, location, teacher);
    }

    //Search Course
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
    public void CourseshowEditButton(ActionEvent event) throws IOException {
        if (!courseEditButtonState) {
            courseAddButtonState = false;
        }
    }
    //new edit button working
    public void courseEditButton(ActionEvent event) throws IOException {
        if (!courseTable.getSelectionModel().getSelectedItems().isEmpty()) {
            Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
            int selectedIndex = courseTable.getSelectionModel().getSelectedIndex();

            Course updatedCourse = getCourse();

            // Update Excel file
            EditCourseList editCourseList = new EditCourseList();
            editCourseList.Edit(
                    selectedCourse,
                    updatedCourse.getCourseCode(),
                    updatedCourse.getCourseName(),
                    updatedCourse.getCourseSection(),
                    updatedCourse.getCourseLecture(),
                    updatedCourse.getCourseCapacity(),
                    updatedCourse.getCourseLocation(),
                    updatedCourse.getCourseTeacher()
            );

            // Update in lists
            currentCourseList.set(selectedIndex, updatedCourse);
            courseTableViewList.set(selectedIndex, updatedCourse);

            courseTable.refresh();
        }
    }

}


