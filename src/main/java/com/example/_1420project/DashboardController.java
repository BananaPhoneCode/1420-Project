package com.example._1420project;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

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
    @FXML
    private AnchorPane pane1,pane2;
    private static final Duration ANIMATION_DURATION = Duration.seconds(0.5);
    @FXML
    private TableView<Event> DashboardTable = new TableView<>();
    @FXML
    private TableColumn<Event, String> DashboardEvent = new TableColumn<>();
    @FXML
    private TableColumn<Event, String> DashboardStudent = new TableColumn<>();

    ObservableList<Event> DashboardTableViewList = FXCollections.observableArrayList();
    ObservableList<Event> EventSearchTableViewList = FXCollections.observableArrayList();
    // ArrayList<Event> currentDashboardList = new EditDashboardList().Generate();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Initialize Admin Buttons and Event List
       //  DashboardTableViewList.addAll(currentDashboardList);
        DashboardEvent.setCellValueFactory(new PropertyValueFactory<Event, String>("DashboardEvent"));
        DashboardStudent.setCellValueFactory(new PropertyValueFactory<Event, String>("DashboardStudent"));
        DashboardTable.setItems(DashboardTableViewList);

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

    //Menu Buttons
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
}
