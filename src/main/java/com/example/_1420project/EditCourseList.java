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
            String tempName = formatter.formatCellValue(row.getCell(0));
            String tempCode = formatter.formatCellValue(row.getCell(1));
            String tempSection = formatter.formatCellValue(row.getCell(2));
            String tempLecture = formatter.formatCellValue(row.getCell(3));
            Course selectedCourse = new Course(tempName,tempCode, tempSection, tempLecture);
            courseList.add(selectedCourse);
        }
        return courseList;
    }
    public void Add(String CourseNameIN, String CourseCodeIN, String CourseSectionIN, String CourseLectureIN) throws FileNotFoundException {
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
        Cell cellSection = row.createCell(2);
        cellCode.setCellValue(CourseSectionIN);
        Cell cellLecture = row.createCell(3);
        cellCode.setCellValue(CourseLectureIN);
        try(OutputStream fileOut = new FileOutputStream(path)) {
            xssfWorkbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void Remove(Course CourseIN) throws FileNotFoundException{
        for(Row row : sheet){
            if(Objects.equals(CourseIN.getCourseCode(), row.getCell(0).getStringCellValue())|| Objects.equals(CourseIN.getCourseName(), row.getCell(1).getStringCellValue())){
                row.getCell(0).setBlank();
                row.getCell(1).setBlank();
            }
        }
        try(OutputStream fileOut = new FileOutputStream(path)) {
            xssfWorkbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}