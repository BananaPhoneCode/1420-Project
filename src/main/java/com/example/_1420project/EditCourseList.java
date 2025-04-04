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
            String tempName = formatter.formatCellValue(row.getCell(0));
            String tempCode = formatter.formatCellValue(row.getCell(1));
            String tempSection = formatter.formatCellValue(row.getCell(2));
            String tempLecture = formatter.formatCellValue(row.getCell(3));
            String tempCapacity = formatter.formatCellValue(row.getCell(4));
            String tempLocation = formatter.formatCellValue(row.getCell(5));
            String tempTeacher = formatter.formatCellValue(row.getCell(6));

            if (Objects.equals(CourseIN.getCourseCode(), tempCode) ||
                    Objects.equals(CourseIN.getCourseName(), tempName) ||
                    Objects.equals(CourseIN.getCourseSection(), tempSection) ||
                    Objects.equals(CourseIN.getCourseLecture(), tempLecture) ||
                    Objects.equals(CourseIN.getCourseCapacity(), tempCapacity) ||
                    Objects.equals(CourseIN.getCourseLocation(), tempLocation) ||
                    Objects.equals(CourseIN.getCourseTeacher(), tempTeacher)) {

                row.removeCell(row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                row.removeCell(row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                row.removeCell(row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                row.removeCell(row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                row.removeCell(row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                row.removeCell(row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                row.removeCell(row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
            }
        }
    }


    //editing functionality
        public void Edit(Course CourseIN, String CourseCodeIN, String CourseNameIN, String CourseSectionIN, String CourseLectureIN, String CourseCapacityIN, String CourseLocationIN, String CourseTeacherIN) throws FileNotFoundException{
            for(Row row : sheet) {
                String tempCode = formatter.formatCellValue(row.getCell(0));
                String tempName = formatter.formatCellValue(row.getCell(1));
                String tempSection = formatter.formatCellValue(row.getCell(3));
                String tempLecture = formatter.formatCellValue(row.getCell(5));
                String tempCapacity = formatter.formatCellValue(row.getCell(6));
                String tempLocation =formatter.formatCellValue(row.getCell(7));
                String tempTeacher =formatter.formatCellValue(row.getCell(8));
                if (Objects.equals(CourseIN.getCourseCode(), tempCode) ||
                        Objects.equals(CourseIN.getCourseName(), tempName) ||
                        Objects.equals(CourseIN.getCourseSection(), formatter.formatCellValue(row.getCell(3))) ||
                        Objects.equals(CourseIN.getCourseLecture(), formatter.formatCellValue(row.getCell(5))) ||
                        Objects.equals(CourseIN.getCourseCapacity(), formatter.formatCellValue(row.getCell(4))) ||
                        Objects.equals(CourseIN.getCourseLocation(), formatter.formatCellValue(row.getCell(7))) ||
                        Objects.equals(CourseIN.getCourseTeacher(), formatter.formatCellValue(row.getCell(8)))) {
                    if (!CourseCodeIN.isEmpty())
                        row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseCodeIN);
                    if (!CourseNameIN.isEmpty())
                        row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseNameIN);
                    if (!CourseSectionIN.isEmpty())
                        row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseSectionIN);
                    if (!CourseLectureIN.isEmpty())
                        row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseLectureIN);
                    if (!CourseCapacityIN.isEmpty())
                        row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseCapacityIN);
                    if (!CourseLocationIN.isEmpty())
                        row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseLocationIN);
                    if (!CourseTeacherIN.isEmpty())
                        row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(CourseTeacherIN);
                }
            }

        try(OutputStream fileOut = new FileOutputStream(path);) {
            xssfWorkbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

