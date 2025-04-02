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

public class viewFaculties {
    @FXML
    public TableView<viewFaculties> tableView;
    @FXML
    public TableColumn<viewFaculties, String> facultyNumber;
    @FXML
    public TableColumn<viewFaculties, String> facultyEmail;
    @FXML
    public TableColumn<viewFaculties, String> facultyName;
    @FXML
    public TableColumn<viewFaculties, String> facultyOffice;
    @FXML
    public TableColumn<viewFaculties, String> facultyDegree;
    @FXML
    public TableColumn<viewFaculties, String> facultyCourses;
    @FXML
    public TableColumn<viewFaculties, String> facultyInterest;



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
    public Sheet sheet = xssfWorkbook.getSheet("Faculties");
    CreationHelper createHelper = xssfWorkbook.getCreationHelper();

    public void viewFaculties(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFaculties.fxml"));
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
            facultyNumber.setCellValueFactory(new PropertyValueFactory<viewFaculties, String>(String.valueOf(row.getCell(0))));
            facultyName.setCellValueFactory(new PropertyValueFactory<viewFaculties, String>(String.valueOf(row.getCell(1))));
            facultyDegree.setCellValueFactory(new PropertyValueFactory<viewFaculties, String>(String.valueOf(row.getCell(2))));
            facultyInterest.setCellValueFactory(new PropertyValueFactory<viewFaculties, String>(String.valueOf(row.getCell(3))));
            facultyEmail.setCellValueFactory(new PropertyValueFactory<viewFaculties, String>(String.valueOf(row.getCell(4))));
            facultyOffice.setCellValueFactory(new PropertyValueFactory<viewFaculties, String>(String.valueOf(row.getCell(5))));
            facultyCourses.setCellValueFactory(new PropertyValueFactory<viewFaculties, String>(String.valueOf(row.getCell(6))));
        }
    }
}

