package com.example._1420project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.soap.Text;

import javax.swing.text.html.ImageView;
import java.io.*;

public class facultyController {


    public facultyController() throws IOException {
    }

    public class changePass {
        @FXML
        private TextField oldpass;
        @FXML
        private TextField newpass;
        @FXML
        private TextField newpassconf;
        @FXML
        private Text error;
        @FXML
        private ImageView profilePic;

        private static final String EXCEL_FILE_PATH = "src/UMS_Data.xlsx";
        public DataFormatter formatter = new DataFormatter();
        public String path = "src/UMS_Data.xlsx";
        public FileInputStream fileInputStream = new FileInputStream(path);
        public XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        public Sheet sheet = xssfWorkbook.getSheet("Faculties");
        CreationHelper createHelper = xssfWorkbook.getCreationHelper();

        String Oldpass;
        String Newpass;
        String Newpassconf;

        public changePass() throws IOException {
        }

        public void changePass(ActionEvent event) throws IOException {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProfile.fxml"));
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
            Oldpass = oldpass.getText();
            Newpass = newpass.getText();
            Newpassconf = newpassconf.getText();


            for (Row row : sheet) {
                Cell password = row.getCell(7);
                if (Oldpass == password.getStringCellValue() && Newpass == Newpassconf) {
                    password.setCellValue(Newpass);
                    try (OutputStream fileOut = new FileOutputStream(path)) {
                        xssfWorkbook.write(fileOut);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    error.setTextContent("Error changing password. Make sure passwords match");
                }
            }
        }
    }
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
    public class viewStudentsList {
        @FXML
        public TableColumn<viewStudentsList, String> studentsList;


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

        public Sheet sheet = xssfWorkbook.getSheet("Students");
        CreationHelper createHelper = xssfWorkbook.getCreationHelper();

        public void viewStudentsList(ActionEvent event) throws IOException {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("viewStudentsLists.fxml"));
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
                studentsList.setCellValueFactory(new PropertyValueFactory<viewStudentsList, String>(String.valueOf(row.getCell(1))));

            }
        }
    }
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
                if (row.getRowNum() != 0) {
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
    }
}
