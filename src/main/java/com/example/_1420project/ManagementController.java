package com.example._1420project;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
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

    @FXML
    private AnchorPane pane1,pane2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
    }

    public void subject(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Subject.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
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
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
    }
}