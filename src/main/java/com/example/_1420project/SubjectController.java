package com.example._1420project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class SubjectController implements Initializable{
    @FXML
    private ListView<String> myListView;
    @FXML
    private Label myLabel;
    String[] subject = {"mechanics", "calc", "french"};
    String currentSubject;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myListView.getItems().addAll(subject);
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentSubject = myListView.getSelectionModel().getSelectedItem();
                myLabel.setText(currentSubject);
            }
        });
    }
}
