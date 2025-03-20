package com.example._1420project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class EventManagement extends Application {
    private static final Logger logger = LogManager.getLogger(EventManagement.class);
    private final ObservableList<Event> eventList = FXCollections.observableArrayList();
    private static final String FILE_PATH = new File("UMS_Data.xlsx").getAbsolutePath();

    @Override
    public void start(Stage primaryStage) {
        TableView<Event> eventTable = new TableView<>();
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

            // Find the sheet dynamically (ignoring extra spaces)
            Sheet sheet = null;
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                if (workbook.getSheetName(i).trim().equalsIgnoreCase("Events")) {
                    sheet = workbook.getSheetAt(i);
                    break;
                }
            }

            if (sheet == null) {
                System.out.println("Sheet 'Events' not found! Available sheets:");
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    System.out.println("'" + workbook.getSheetName(i) + "'");
                }
                return;
            }

            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) rowIterator.next(); // Skip header row

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // **Check for null before reading cell values**
                String eventName = getCellValue(row, 0);
                String eventDate = getCellValue(row, 1);
                String location = getCellValue(row, 2);

                if (eventName != null && eventDate != null && location != null) {
                    eventList.add(new Event(eventName, eventDate, location));
                } else {
                    System.out.println("⚠️ Skipping row " + row.getRowNum() + " due to missing values!");
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading Excel file: " + e.getMessage());
        }
    }

    private String getCellValue(Row row, int cellIndex) {
        org.apache.poi.ss.usermodel.Cell cell = row.getCell(cellIndex); // 👈 Fully qualified name
        return (cell != null) ? cell.getStringCellValue().trim() : null;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
