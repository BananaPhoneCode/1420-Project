package com.example._1420project;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class EditCourseList{
    public ArrayList<Course> courseList = new ArrayList<>();
    public DataFormatter formatter = new DataFormatter();
    public String path = "src/UMS_Data.xlsx";
    public FileInputStream fileInputStream = new FileInputStream(path);
    public XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
    public Sheet sheet = xssfWorkbook.getSheet("Courses");
    CreationHelper createHelper = xssfWorkbook.getCreationHelper();
    public EditCourseList() throws IOException {
    }

    //The Excel sheet data for Course
    public ArrayList<Course> Generate() throws IOException {
        String[] CourseOUT;
        for(Row row : sheet){
            if(row.getRowNum()==0){
                continue;
            }

            //gets data from desired row
            String tempName = formatter.formatCellValue(row.getCell(0));
            String tempCode = formatter.formatCellValue(row.getCell(1));
            String tempSection = formatter.formatCellValue(row.getCell(3));
            String tempLecture = formatter.formatCellValue(row.getCell(5));
            String tempCapacity = formatter.formatCellValue(row.getCell(6));
            String tempTeacher =formatter.formatCellValue(row.getCell(8));
            String tempLocation =formatter.formatCellValue(row.getCell(7));

            Course selectedCourse = new Course(tempName,tempCode, tempSection, tempLecture, tempCapacity, tempLocation, tempTeacher);
            if(!Objects.equals(tempName, ""))
            {courseList.add(selectedCourse);}
        }
        return courseList;
    }
        //Method: It finds the first empty row by checking if column 0 (Course Code) is blank.
        public void Add(String CourseNameIN, String CourseCodeIN, String CourseSectionIN, String CourseLectureIN, String CourseCapacityIN, String CourseLocationIN, String CourseTeacherIN) throws FileNotFoundException {
            // Find the next empty row (no data in column 0 of the row)
            int nextEmpty = 0;
            for (Row row : sheet) {
                if (Objects.equals(formatter.formatCellValue(row.getCell(0)), "")) {
                    break;
                }
                nextEmpty++;
            }

            // Create a new row at the next empty position
            Row row = sheet.createRow(nextEmpty);

            // Now find the last column with data in this row
            int lastCellNum = row.getLastCellNum(); // This gives the index of the first empty column

            // Add data to the next available columns
            if (lastCellNum == -1) lastCellNum = 0; // If the row is completely empty, start at column 0

            // Add each value in the correct column
            row.createCell(lastCellNum).setCellValue(CourseNameIN);
            row.createCell(lastCellNum + 1).setCellValue(CourseCodeIN);
            row.createCell(lastCellNum + 2).setCellValue(CourseSectionIN);
            row.createCell(lastCellNum + 3).setCellValue(CourseLectureIN);
            row.createCell(lastCellNum + 4).setCellValue(CourseCapacityIN);
            row.createCell(lastCellNum + 5).setCellValue(CourseLocationIN);
            row.createCell(lastCellNum + 6).setCellValue(CourseTeacherIN);

            // Write the updated workbook back to file
            try (OutputStream fileOut = new FileOutputStream(path)) {
                xssfWorkbook.write(fileOut);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    //Remove
    //Course In instance of the class
    public void Remove(Course CourseIN) throws FileNotFoundException {
        for (Row row : sheet) {
            String tempCode = formatter.formatCellValue(row.getCell(0));
            String tempName = formatter.formatCellValue(row.getCell(1));
            // Check if the provided CourseIN matches either the course code or name in the row

            if (Objects.equals(CourseIN.getCourseCode(), tempCode) || Objects.equals(CourseIN.getCourseName(), tempName)) {
                row.removeCell(row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                row.removeCell(row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
            }
        }
    }

    public void Edit(Course CourseIN, String CourseCodeIN, String CourseNameIN, String CourseSectionIN,
                     String CourseLectureIN, String CourseCapacityIN, String CourseLocationIN, String CourseTeacherIN)
            throws IOException {

        for (Row row : sheet) {
            // Null check for cells
            String tempCode = row.getCell(1) != null ? formatter.formatCellValue(row.getCell(1)) : "";
            String tempName = row.getCell(0) != null ? formatter.formatCellValue(row.getCell(0)) : "";
            String tempSection = row.getCell(3) != null ? formatter.formatCellValue(row.getCell(3)) : "";
            String tempLecture = row.getCell(5) != null ? formatter.formatCellValue(row.getCell(5)) : "";
            String tempCapacity = row.getCell(4) != null ? formatter.formatCellValue(row.getCell(4)) : "";
            String tempLocation = row.getCell(7) != null ? formatter.formatCellValue(row.getCell(7)) : "";
            String tempTeacher = row.getCell(8) != null ? formatter.formatCellValue(row.getCell(8)) : "";

            // Ensure we edit only the correct course (Matching both Code and Name)
            if (Objects.equals(CourseIN.getCourseCode(), tempCode) && Objects.equals(CourseIN.getCourseName(), tempName)) {
                if (!CourseCodeIN.trim().isEmpty())
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseCodeIN); //sets the value of that input to the second cell
                if (!CourseNameIN.trim().isEmpty())
                    row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseNameIN);
                if (!CourseSectionIN.trim().isEmpty())
                    row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseSectionIN);
                if (!CourseLectureIN.trim().isEmpty())
                    row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseLectureIN);
                if (!CourseCapacityIN.trim().isEmpty())
                    row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseCapacityIN);
                if (!CourseLocationIN.trim().isEmpty())
                    row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseLocationIN);
                if (!CourseTeacherIN.trim().isEmpty())
                    row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseTeacherIN);

                break; // Exit the loop after the course has been updated
            }
        }
    }


}

