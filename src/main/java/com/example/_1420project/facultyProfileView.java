package com.example._1420project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class facultyProfileView {

    @FXML
    Label facultyName;
    @FXML
    Label facultyEmail;
    @FXML
    Label areasOfExpertise;
    @FXML
    Label coursesOffered;
    @FXML
    Label officeLocation;
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

    public void facultyViewProfile(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FacultyProfile.fxml"));
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
        for (Row row : sheet) {
            facultyName.setText(String.valueOf(row.getCell(1)));
            facultyEmail.setText(String.valueOf(row.getCell(4)));
            areasOfExpertise.setText(String.valueOf(row.getCell(3)));
            coursesOffered.setText(String.valueOf(row.getCell(6)));
            officeLocation.setText(String.valueOf(row.getCell(5)));
        }
    }

    public void facultyProfileView() throws IOException {

    }

}
