package com.example._1420project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class EventManagement extends Application {
    @FXML
    private TableView<Event> eventTable;
    private ObservableList<Event> eventList = FXCollections.observableArrayList();

    private static final String FILE_PATH = "UMS_Data.xlsx";

    @Override
    public void start(Stage primaryStage) {
        eventTable = new TableView<>();
        loadEventData();

        TableColumn<Event, String> nameCol = new TableColumn<>("Event Name");
        nameCol.setCellValueFactory(data -> data.getValue().eventNameProperty());

        TableColumn<Event, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(data -> data.getValue().eventDateProperty());

        TableColumn<Event, String> locationCol = new TableColumn<>("Location");
        locationCol.setCellValueFactory(data -> data.getValue().locationProperty());

        eventTable.getColumns().addAll(nameCol, dateCol, locationCol);
        eventTable.setItems(eventList);

        VBox root = new VBox(10, new Label("Event Management"), eventTable);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("University Event Management");
        primaryStage.show();
    }

    private void loadEventData() {
        try (FileInputStream fis = new FileInputStream(new File(FILE_PATH));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet("Events");
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Skip header row

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String eventName = row.getCell(0).getStringCellValue();
                String eventDate = row.getCell(1).getStringCellValue();
                String location = row.getCell(2).getStringCellValue();
                eventList.add(new Event(eventName, eventDate, location));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}