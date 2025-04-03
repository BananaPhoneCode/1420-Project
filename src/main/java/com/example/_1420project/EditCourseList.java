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
            courseList.add(selectedCourse);
        }
        return courseList;
    }
        //Method: It finds the first empty row by checking if column 0 (Course Code) is blank.
    public void Add(String CourseNameIN, String CourseCodeIN, String CourseSectionIN, String CourseLectureIN, String CourseCapacityIN, String CourseLocationIN, String CourseTeacherIN) throws FileNotFoundException {
        int nextEmpty=0;
        for(Row row : sheet){
            if(formatter.formatCellValue(row.getCell(0))==""){
                break;
            }
            nextEmpty++;
        }
        Row row = sheet.createRow(nextEmpty);
        Cell cellName = row.createCell(0);
        cellName.setCellValue(CourseNameIN);
        Cell cellCode = row.createCell(1);
        cellCode.setCellValue(CourseCodeIN);
        Cell cellSection = row.createCell(3);
        cellSection.setCellValue(CourseSectionIN);
        Cell cellLecture = row.createCell(5);
        cellLecture.setCellValue(CourseLectureIN);

        //
        Cell cellCapacity = row.createCell(6);
        cellCapacity.setCellValue(CourseCapacityIN);
        Cell cellLocation= row.createCell(7);
        cellLocation.setCellValue(CourseLocationIN);
        Cell cellTeacher = row.createCell(8);
        cellTeacher.setCellValue(CourseTeacherIN);

        try(OutputStream fileOut = new FileOutputStream(path)) {
            xssfWorkbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Remove
    public void Remove(Course CourseIN) throws FileNotFoundException {
        for (Row row : sheet) {
            String tempCode = formatter.formatCellValue(row.getCell(0));
            String tempName = formatter.formatCellValue(row.getCell(1));
            if (Objects.equals(CourseIN.getCourseCode(), tempCode) || Objects.equals(CourseIN.getCourseName(), tempName)) {
                row.removeCell(row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                row.removeCell(row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
            }
        }
    }

        //editing functionality
        public void Edit(Course CourseIN, String CourseCodeIN, String CourseNameIN) throws FileNotFoundException{
            for(Row row : sheet) {
                String tempCode = formatter.formatCellValue(row.getCell(0));
                String tempName = formatter.formatCellValue(row.getCell(1));
                String tempSection = formatter.formatCellValue(row.getCell(3));
                String tempLecture = formatter.formatCellValue(row.getCell(5));
                if (Objects.equals(CourseIN.getCourseCode(), tempCode) || Objects.equals(CourseIN.getCourseName(), tempName)) {
                    if (!CourseCodeIN.isEmpty())
                        row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseCodeIN);
                    if (!CourseNameIN.isEmpty())
                        row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseNameIN);
                }
            }

        try(OutputStream fileOut = new FileOutputStream(path);) {
            xssfWorkbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

