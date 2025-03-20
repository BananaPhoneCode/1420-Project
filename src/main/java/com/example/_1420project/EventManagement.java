package com.example._1420project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private static final String FILE_PATH = new File("UMS_Data.xlsx").getAbsolutePath();

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/_1420project/Event.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("University Event Management");
            primaryStage.show();
        } catch (IOException e) {
            logger.error("Error loading FXML file: ", e);
        }
    }

    private void loadEventData() {
        try (FileInputStream fis = new FileInputStream(new File(FILE_PATH));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Events");
            if (sheet == null) {
                logger.warn("Sheet 'Events' not found in the Excel file!");
                return;
            }

            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) rowIterator.next(); // Skip header row

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                String eventName = getCellValue(row, 0);
                String eventDate = getCellValue(row, 1);
                String location = getCellValue(row, 2);

                if (eventName != null && eventDate != null && location != null) {
                    logger.info("Loaded event: " + eventName);
                } else {
                    logger.warn("Skipping row " + row.getRowNum() + " due to missing values!");
                }
            }

        } catch (IOException e) {
            logger.error("Error reading Excel file: ", e);
        }
    }

    private String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        return (cell != null) ? cell.getStringCellValue().trim() : null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
