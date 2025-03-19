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

    private final List<User> students = new ArrayList<>();
    private final List<User> faculties = new ArrayList<>();

    public LoginController() {
        loadUserCredentials();
    }

    // Load credentials from the Excel file
    private void loadUserCredentials() {
        File file = new File(EXCEL_FILE_PATH);
        if (!file.exists()) {
            System.out.println("Excel file not found: " + EXCEL_FILE_PATH);
            return;
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            DataFormatter formatter = new DataFormatter();

            // Load students
            Sheet studentSheet = workbook.getSheet("Students");
            if (studentSheet != null) {
                for (Row row : studentSheet) {
                    if (row.getRowNum() == 0) continue; // Skip header row

                    String username = formatter.formatCellValue(row.getCell(0)).trim();  // Column A (Student ID)
                    String password = formatter.formatCellValue(row.getCell(11)).trim(); // Column L (Password)

                    if (!username.isEmpty() && !password.isEmpty()) {
                        students.add(new User(username, "default123", "student")); // Default password
                    }
                }
            }

            // Load faculties
            Sheet facultySheet = workbook.getSheet("Faculties");
            if (facultySheet != null) {
                for (Row row : facultySheet) {
                    if (row.getRowNum() == 0) continue; // Skip header row

                    String username = formatter.formatCellValue(row.getCell(0)).trim(); // Column A (Faculty ID)
                    String password = formatter.formatCellValue(row.getCell(7)).trim(); // Column H (Password)

                    if (!username.isEmpty() && !password.isEmpty()) {
                        faculties.add(new User(username, "default123", "faculty")); // Default password
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error loading user credentials: " + e.getMessage());
        }
    }

    // Validate fields
    private boolean areFieldsValid(String username, String password) {
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            showInvalidLoginAlert("Username or password cannot be empty.");
            return false;
        }
        return true;
    }

    // Authenticate user based on loaded credentials
    private boolean authenticateUser(String username, String password) {
        System.out.println("Checking username: " + username);  // Debugging log to show entered username

        // Trim spaces from the entered username
        String enteredUsername = username.trim();

        for (User user : students) {
            // Trim spaces from the loaded username
            String loadedUsername = user.getUsername().trim();
            System.out.println("Checking student username: " + loadedUsername);  // Debugging log to show loaded username
            if (loadedUsername.equals(enteredUsername)) {  // Case-sensitive comparison
                if (user.getPassword().equals(password)) {
                    assignRole("student");
                    return true;
                } else {
                    showInvalidLoginAlert("Invalid password. Please try again.");
                    return false;
                }
            }
        }

        for (User user : faculties) {
            // Trim spaces from the loaded username
            String loadedUsername = user.getUsername().trim();
            System.out.println("Checking faculty username: " + loadedUsername);  // Debugging log to show loaded username
            if (loadedUsername.equals(enteredUsername)) {  // Case-sensitive comparison
                if (user.getPassword().equals(password)) {
                    assignRole("faculty");
                    return true;
                } else {
                    showInvalidLoginAlert("Invalid password. Please try again.");
                    return false;
                }
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
