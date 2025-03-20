package com.example._1420project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    @FXML
    private TextField Username;
    @FXML
    private TextField Password;

    private String currentRole;
    private static final String EXCEL_FILE_PATH = "src/UMS_Data.xlsx";

    private final ArrayList<User> students = new ArrayList<>();
    private final ArrayList<User> faculties = new ArrayList<>();

    public LoginController() {
        System.out.println("LoginController called");
        loadUserCredentials();
    }

    private void loadUserCredentials() {
        //checking if file is locatable
        File file = new File(EXCEL_FILE_PATH);
        if (!file.exists()) {
            System.out.println("Excel file not found: " + EXCEL_FILE_PATH);
            return;
        }
        System.out.println("Excel file found: " + EXCEL_FILE_PATH);
        //opening and reading info from the excel sheet
        try (FileInputStream fileInputStream = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            System.out.println("Workbook opened successfully.");

            //opening students sheet and looping through each row, adding all student usernames and passwords to the student arraylist via user objects
            Sheet studentsSheet = workbook.getSheet("Students");
            if (studentsSheet != null) {
                for (Row row : studentsSheet) {
                    if(row.getRowNum()==0){continue;}
                    Cell usernameCell = row.getCell(0);  // Username is in column A (index 0)
                    Cell passwordCell = row.getCell(11);  // Password is in column L (index 11)

                    if (usernameCell != null && passwordCell != null) {
                        String username = usernameCell.getStringCellValue();
                        String password = passwordCell.getStringCellValue();
                        students.add(new User(username, password, "student"));
                    }
                }
                //checking if this ran successfully
                System.out.println("Loaded students from Excel sheet.");
            }

            //opening faculties sheet and looping through each row, adding all faculty usernames and passwords to the faculties arraylist via user objects
            Sheet facultiesSheet = workbook.getSheet("Faculties");
            if (facultiesSheet != null) {
                for (Row row : facultiesSheet) {
                    if(row.getRowNum()==0){continue;}
                    Cell usernameCell = row.getCell(0);  // Username is in column A (index 0)
                    Cell passwordCell = row.getCell(7);  // Password is in column H (index 7)

                    if (usernameCell != null && passwordCell != null) {
                        String username = usernameCell.getStringCellValue();
                        String password = passwordCell.getStringCellValue();
                        faculties.add(new User(username, password, "faculty"));
                    }
                }
                System.out.println("Loaded faculties from Excel sheet.");
            }

        } catch (IOException e) {
            System.err.println("Error opening workbook: " + e.getMessage());
        }
    }

    // check if any fields are blank, shows an error popup if yes
    private boolean areFieldsValid(String username, String password) {
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            showInvalidLoginAlert("Username or password cannot be empty.");
            return false;
        }
        return true;
    }

    // authenticate user based on loaded credentials
    private boolean authenticateUser(String username, String password) {
        System.out.println("Checking username: " + username);  // debugging

        String enteredUsername = username;
        //checking the contents of the arraylists (ERROR FOUND!!! THE LISTS ARE EMPTY)
        System.out.println(students);
        System.out.println(faculties);

        // is enteredusername found in the students array? does the password match? if yes to both assign role of student
        for (User user : students) {
            String loadedUsername = user.getUsername();
            System.out.println(loadedUsername);
            System.out.println("Checking student username: " + loadedUsername);  // Debugging log to show loaded username
            if (loadedUsername.equals(enteredUsername)) {
                if (user.getPassword().equals(password)) {
                    assignRole("student");
                    return true;
                } else {
                    showInvalidLoginAlert("Invalid password. Please try again.");
                    return false;
                }
            }
        }

        // check in the faculties list
        for (User user : faculties) {
            String loadedUsername = user.getUsername();
            System.out.println("Checking faculty username: " + loadedUsername);
            if (loadedUsername.equals(enteredUsername)) {
                if (user.getPassword().equals(password)) {
                    assignRole("faculty");
                    return true;
                } else {
                    showInvalidLoginAlert("Invalid password. Please try again.");
                    return false;
                }
            }else{
                System.out.println("no match");
            }
        }

        showInvalidLoginAlert("Username not found. Please try again.");
        return false;
    }

    private void assignRole(String role) {
        this.currentRole = role;
    }

    public void login(ActionEvent event) throws IOException {
        String username = Username.getText();
        String password = Password.getText();

        if (!areFieldsValid(username, password)) {
            return;
        }

        if (authenticateUser(username, password)) {
            switch (this.currentRole) {
                case "student":
                    navigateToDashboard(event, "Dashboard.fxml");
                    break;
                case "faculty":
                    navigateToDashboard(event, "FacultyDashboard.fxml");
                    break;
            }
        }
    }

    private void navigateToDashboard(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void showInvalidLoginAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void bypass(ActionEvent event) throws IOException {
        navigateToDashboard(event, "Dashboard.fxml");
    }
}
