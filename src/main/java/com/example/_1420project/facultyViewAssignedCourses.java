package com.example._1420project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.IOException;

public class facultyViewAssignedCourses {
    @FXML
    public TableView<facultyViewAssignedCourses> tableView;
    @FXML
    public TableColumn<facultyViewAssignedCourses, String> courseNameFaculty;
    @FXML
    public TableColumn<facultyViewAssignedCourses, String> courseCodeFaculty;




    private static final String EXCEL_FILE_PATH = "src/UMS_Data.xlsx";
    public DataFormatter formatter = new DataFormatter();
    public String path = "src/UMS_Data.xlsx";
    public FileInputStream fileInputStream;
    {
        try {
            fileInputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public XSSFWorkbook xssfWorkbook;

    {
        try {
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Sheet sheet = xssfWorkbook.getSheet("Faculties ");
    CreationHelper createHelper = xssfWorkbook.getCreationHelper();

    public void facultyViewAssignedCourses(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAssignedCourses.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (
                IOException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
        for (Row row : sheet) {
            courseNameFaculty.setCellValueFactory(new PropertyValueFactory<facultyViewAssignedCourses, String>(String.valueOf(row.getCell(0))));
            courseCodeFaculty.setCellValueFactory(new PropertyValueFactory<facultyViewAssignedCourses, String>(String.valueOf(row.getCell(1))));

        }
    }
}