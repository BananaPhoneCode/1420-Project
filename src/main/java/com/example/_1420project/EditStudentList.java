package com.example._1420project;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class EditStudentList {
    public ArrayList<Student> studentList = new ArrayList<>();
    public DataFormatter formatter = new DataFormatter();
    public String path = "src/UMS_Data.xlsx";
    public FileInputStream fileInputStream = new FileInputStream(path);
    public XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
    public Sheet sheet = xssfWorkbook.getSheet("Students");
    CreationHelper createHelper = xssfWorkbook.getCreationHelper();

    public EditStudentList() throws IOException {
    }

    public ArrayList<Student> Generate() throws IOException {
        String[] StudentOUT;
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            String tempName = formatter.formatCellValue(row.getCell(0));
            String tempId = formatter.formatCellValue(row.getCell(1));
            String tempAddress = formatter.formatCellValue(row.getCell(2));
            String tempTele = formatter.formatCellValue(row.getCell(3));
            String tempTuition = formatter.formatCellValue(row.getCell(4));
            String tempCourses = formatter.formatCellValue(row.getCell(5));
            String tempEmail = formatter.formatCellValue(row.getCell(6));
            String tempGrade = formatter.formatCellValue(row.getCell(7));
            String tempSem = formatter.formatCellValue(row.getCell(8));
            String tempSub = formatter.formatCellValue(row.getCell(9));
            String tempLev = formatter.formatCellValue(row.getCell(10));
            String tempTitle = formatter.formatCellValue(row.getCell(11));
            String tempProg = formatter.formatCellValue(row.getCell(12));
            Student selectStudent = new Student(tempName, tempId, tempAddress, tempTele, tempTuition, tempCourses, tempEmail, tempGrade, tempSem, tempSub, tempLev, tempTitle, tempProg);
            if (!Objects.equals(tempName, "")) {
                studentList.add(selectStudent);
            }
        }
        return studentList;
    }

    public void Add(String StuNameIN, String StuIdIN) throws FileNotFoundException {
        int nextEmpty = 0;
        for (Row row : sheet) {
            if (formatter.formatCellValue(row.getCell(0)) == "") {
                break;
            }
            nextEmpty++;
        }
        Row row = sheet.createRow(nextEmpty);
        Cell cellName = row.createCell(0);
        cellName.setCellValue(StuNameIN);
        Cell cellId = row.createCell(1);
        cellId.setCellValue(StuIdIN);
        try (OutputStream fileOut = new FileOutputStream(path)) {
            xssfWorkbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Remove(Student StuIN) throws FileNotFoundException {
        for (Row row : sheet) {
            if (Objects.equals(StuIN.getStudentId(), row.getCell(0).getStringCellValue()) || Objects.equals(StuIN.getStudentName(), row.getCell(1).getStringCellValue()) || Objects.equals(StuIN.getStudentAddress(), row.getCell(3).getStringCellValue()) || Objects.equals(StuIN.getStudentTelephone(), row.getCell(4).getStringCellValue()) || Objects.equals(StuIN.getStudentTuition(), row.getCell(5).getStringCellValue()) || Objects.equals(StuIN.getStudentCourses(), row.getCell(6).getStringCellValue()) || Objects.equals(StuIN.getStudentEmail(), row.getCell(7).getStringCellValue()) || Objects.equals(StuIN.getStudentGrade(), row.getCell(8).getStringCellValue()) || Objects.equals(StuIN.getStudentSem(), row.getCell(9).getStringCellValue()) || Objects.equals(StuIN.getStudentSub(), row.getCell(10).getStringCellValue()) || Objects.equals(StuIN.getStudentLev(), row.getCell(11).getStringCellValue()) || Objects.equals(StuIN.getStudentTitle(), row.getCell(12).getStringCellValue()) || Objects.equals(StuIN.getStudentProg(), row.getCell(13).getStringCellValue())) {
                row.getCell(0).setBlank();
                row.getCell(1).setBlank();
                row.getCell(2).setBlank();
                row.getCell(3).setBlank();
                row.getCell(4).setBlank();
                row.getCell(5).setBlank();
                row.getCell(6).setBlank();
                row.getCell(7).setBlank();
                row.getCell(8).setBlank();
                row.getCell(9).setBlank();
                row.getCell(10).setBlank();
                row.getCell(11).setBlank();
                row.getCell(12).setBlank();
                row.getCell(13).setBlank();

            }
        }
        try (OutputStream fileOut = new FileOutputStream(path)) {
            xssfWorkbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewStudentProfile(String studentId) throws IOException {
        // Generate or refresh the studentList
        ArrayList<Student> students = Generate();

        // Search for the student by ID
        Student targetStudent = null;
        for (Student student : students) {
            if (Objects.equals(student.getStudentId(), studentId)) {
                targetStudent = student;
                break;
            }
        }

        if (targetStudent != null) {
            // Print or display the student's profile details
            System.out.println("===== Student Profile =====");
            System.out.println("Name: " + targetStudent.getStudentName());
            System.out.println("ID: " + targetStudent.getStudentId());
            System.out.println("===========================");
        } else {
            System.out.println("Student with ID " + studentId + " does not exist.");
        }
    }

    public void editStudent(String studentId, String newAddress, String newTelephone) throws IOException {
        // Generate or refresh the student list
        ArrayList<Student> students = Generate();

        // Search for the student by ID
        boolean studentFound = false;
        for (Student student : students) {
            if (Objects.equals(student.getStudentId(), studentId)) {
                studentFound = true;

                // Update in-memory data
                if (newAddress != null && !newAddress.isEmpty()) {
                    student.getStudentAddress();
                }
                if (newTelephone != null && !newTelephone.isEmpty()) {
                    student.getStudentTelephone();
                }

                // Update in the Excel file
                for (Row row : sheet) {
                    Cell idCell = row.getCell(1); // Assuming Student ID is in column 2 (index 1)
                    if (idCell != null && formatter.formatCellValue(idCell).equals(studentId)) {
                        if (newAddress != null && !newAddress.isEmpty()) {
                            row.getCell(2).setCellValue(newAddress); // Assuming Address is in column 3 (index 2)
                        }
                        if (newTelephone != null && !newTelephone.isEmpty()) {
                            row.getCell(3).setCellValue(newTelephone); // Assuming Telephone is in column 4 (index 3)
                        }
                        break;
                    }
                }
                break;
            }
        }

        // Save changes to the Excel file
        if (studentFound) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
                xssfWorkbook.write(fileOutputStream);
            }
            System.out.println("Student details updated successfully.");
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }
    public void trackStudentProgress(String studentId) {
        for (Student student : studentList) {
            if (student.getStudentId().equals(studentId)) {
                System.out.println("===== Academic Progress =====");
                System.out.println("Name: " + student.getStudentName());
                System.out.println("ID: " + student.getStudentId());
                System.out.println("Current Grade: " + student.getStudentGrade());
                System.out.println("Current Semester: " + student.getStudentSem());
                System.out.println("Program: " + student.getStudentProg());
                System.out.println("============================");
                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }

    public void updateStudentGrade(String studentId, String newGrade) throws IOException {
        boolean studentFound = false;
        for (Row row : sheet) {
            Cell idCell = row.getCell(1);
            if (idCell != null && formatter.formatCellValue(idCell).equals(studentId)) {
                row.getCell(7).setCellValue(newGrade);
                studentFound = true;
                break;
            }
        }
        if (studentFound) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
                xssfWorkbook.write(fileOutputStream);
            }
            System.out.println("Grade updated successfully.");
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }
    private double calculateTuitionFee(String program) {
        if (program.equalsIgnoreCase("Undergraduate")) {
            return 5000;
        } else if (program.equalsIgnoreCase("Graduate")) {
            return 4000;
        }
        return 0;
    }
}


