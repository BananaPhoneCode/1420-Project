package com.example._1420project;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.soap.Text;

import java.util.ArrayList;


public class changePass extends LoginController{
    @FXML
    private TextField oldpass;
    @FXML
    private TextField newpass;
    @FXML
    private TextField newpassconf;
    @FXML
    private Text error;

    private static final String EXCEL_FILE_PATH = "src/UMS_Data.xlsx";
    public DataFormatter formatter = new DataFormatter();
    public String path = "src/UMS_Data.xlsx";
    public FileInputStream fileInputStream = new FileInputStream(path);
    public XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
    public Sheet sheet = xssfWorkbook.getSheet("Faculties ");
    CreationHelper createHelper = xssfWorkbook.getCreationHelper();

    String Oldpass;
    String Newpass;
    String Newpassconf;



    public changePass(ActionEvent event) throws IOException {
        Oldpass = oldpass.getText();
        Newpass = newpass.getText();
        Newpassconf = newpassconf.getText();

        for (Row row : sheet) {
            Cell password = row.getCell(7);
            if (Oldpass == password.getStringCellValue() && Newpass == Newpassconf) {
                password.setCellValue(Newpass);
                try(OutputStream fileOut = new FileOutputStream(path)) {
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



