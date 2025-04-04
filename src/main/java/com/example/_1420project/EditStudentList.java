package com.example._1420project;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class EditStudentList {
    private final ArrayList<Student> studentList = new ArrayList<>();
    private final DataFormatter formatter = new DataFormatter();
    private final String path = "src/UMS_Data.xlsx";
    private final FileInputStream fileInputStream = new FileInputStream(path);
    private final XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
    private final Sheet sheet = xssfWorkbook.getSheet("Students ");

    public EditStudentList() throws IOException {}

    public ArrayList<Student> Generate() throws IOException {
        studentList.clear(); // Prevent duplicates on regeneration

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;

            String tempId = formatter.formatCellValue(row.getCell(0));        // A: ID
            String tempName = formatter.formatCellValue(row.getCell(1));      // B: Name
            String tempAddress = formatter.formatCellValue(row.getCell(2));   // C: Address
            String tempTele = formatter.formatCellValue(row.getCell(3));      // D: Telephone
            String tempEmail = formatter.formatCellValue(row.getCell(4));     // E: Email
            String tempLev = formatter.formatCellValue(row.getCell(5));       // F: Level
            String tempSem = formatter.formatCellValue(row.getCell(6));       // G: Semester
            String tempTitle = formatter.formatCellValue(row.getCell(7));     // H: Thesis Title
            String tempSub = formatter.formatCellValue(row.getCell(8));       // I: Subjects
            String tempProg = formatter.formatCellValue(row.getCell(9));      // J: Program
            String tempCourses = formatter.formatCellValue(row.getCell(10));  // K: Courses
            String tempGrade = formatter.formatCellValue(row.getCell(11));    // L: Grades

            // Tuition will be handled in controller
            Student student = new Student(
                    tempName,
                    tempId,
                    tempAddress,
                    tempTele,
                    "", // tuition placeholder
                    tempCourses,
                    tempEmail,
                    tempGrade,
                    tempSem,
                    tempSub,
                    tempLev,
                    tempTitle,
                    tempProg
            );

            if (!tempName.isEmpty()) {
                studentList.add(student);
            }
        }

        return studentList;
    }

    public Student viewStudentProfile(String studentId) throws IOException {
        ArrayList<Student> students = Generate();

        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }

        return null;
    }

    public void editStudent(String studentId, String newAddress, String newTelephone) throws IOException {
        for (Row row : sheet) {
            Cell idCell = row.getCell(0); // Student ID in column A
            if (idCell != null && formatter.formatCellValue(idCell).trim().equalsIgnoreCase(studentId.trim())) {
                if (newAddress != null && !newAddress.isEmpty()) {
                    row.getCell(2).setCellValue(newAddress); // Address in column C
                }
                if (newTelephone != null && !newTelephone.isEmpty()) {
                    row.getCell(3).setCellValue(newTelephone); // Telephone in column D
                }
                break;
            }
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            xssfWorkbook.write(fileOutputStream);
        }
    }
}
