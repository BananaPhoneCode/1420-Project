package com.example._1420project;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

public class EditStudentList {
    private final ArrayList<Student> studentList = new ArrayList<>();
    private final DataFormatter formatter = new DataFormatter();
    private final String path = "src/UMS_Data.xlsx";

    public EditStudentList() {}

    public ArrayList<Student> Generate() throws IOException {
        studentList.clear();

        try (FileInputStream fileInputStream = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheet("Students ");

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String tempId = formatter.formatCellValue(row.getCell(0));
                String tempName = formatter.formatCellValue(row.getCell(1));
                String tempAddress = formatter.formatCellValue(row.getCell(2));
                String tempTele = formatter.formatCellValue(row.getCell(3));
                String tempEmail = formatter.formatCellValue(row.getCell(4));
                String tempLev = formatter.formatCellValue(row.getCell(5));
                String tempSem = formatter.formatCellValue(row.getCell(6));
                String tempTitle = formatter.formatCellValue(row.getCell(9));
                String tempSub = formatter.formatCellValue(row.getCell(8));
                String tempProg = formatter.formatCellValue(row.getCell(10));
                String tempPassword = formatter.formatCellValue(row.getCell(11));

                Student student = new Student(
                        tempName, tempId, tempAddress, tempTele, "",
                        tempSub, tempEmail, tempPassword, tempSem,
                        tempSub, tempLev, tempTitle, tempProg
                );

                if (!tempName.isEmpty()) {
                    studentList.add(student);
                }
            }
        }

        return studentList;
    }

    public Student viewStudentProfile(String studentId) throws IOException {
        ArrayList<Student> students = Generate();

        for (Student student : students) {
            if (student.getStudentId().trim().equalsIgnoreCase(studentId.trim())) {
                return student;
            }
        }

        return null;
    }

    public void editStudent(String studentId, String newAddress, String newTelephone) throws IOException {
        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Students ");
            DataFormatter formatter = new DataFormatter();

            for (Row row : sheet) {
                Cell idCell = row.getCell(0);
                if (idCell != null && formatter.formatCellValue(idCell).trim().equalsIgnoreCase(studentId.trim())) {
                    if (newAddress != null && !newAddress.isEmpty()) {
                        Cell addressCell = row.getCell(2);
                        if (addressCell == null) addressCell = row.createCell(2);
                        addressCell.setCellValue(newAddress);
                    }

                    if (newTelephone != null && !newTelephone.isEmpty()) {
                        Cell phoneCell = row.getCell(3);
                        if (phoneCell == null) phoneCell = row.createCell(3);
                        phoneCell.setCellValue(newTelephone);
                    }

                    break;
                }
            }

            try (FileOutputStream fos = new FileOutputStream(path)) {
                workbook.write(fos);
            }
        }
    }
    public void updateStudentPassword(String studentId, String newPassword) throws IOException {
        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Students ");
            for (Row row : sheet) {
                Cell idCell = row.getCell(0);
                if (idCell != null && formatter.formatCellValue(idCell).trim().equalsIgnoreCase(studentId.trim())) {
                    Cell passCell = row.getCell(11);
                    if (passCell == null) passCell = row.createCell(11);
                    passCell.setCellValue(newPassword);
                    break;
                }
            }

            try (FileOutputStream fos = new FileOutputStream(path)) {
                workbook.write(fos);
            }
        }
    }
    public void addStudent(Student student) throws IOException {
        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Students ");
            int lastRow = sheet.getLastRowNum() + 1;
            Row row = sheet.createRow(lastRow);

            row.createCell(0).setCellValue(student.getStudentId());
            row.createCell(1).setCellValue(student.getStudentName());
            row.createCell(2).setCellValue(student.getStudentAddress());
            row.createCell(3).setCellValue(student.getStudentTelephone());
            row.createCell(4).setCellValue(student.getStudentEmail());
            row.createCell(5).setCellValue(student.getStudentLev());
            row.createCell(6).setCellValue(student.getStudentSem());
            row.createCell(7).setCellValue(""); // Placeholder column
            row.createCell(8).setCellValue(student.getStudentSub());
            row.createCell(9).setCellValue(student.getStudentTitle());
            row.createCell(10).setCellValue(student.getStudentProg());
            row.createCell(11).setCellValue(student.getStudentPassword());

            try (FileOutputStream fos = new FileOutputStream(path)) {
                workbook.write(fos);
            }
        }
    }
}
