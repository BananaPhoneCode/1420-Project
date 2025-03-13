package com.example._1420project;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class EditSubjectList{
    public ArrayList<Subject> subjectList = new ArrayList<>();
    public DataFormatter formatter = new DataFormatter();
    public String path = "src/UMS_Data.xlsx";
    public FileInputStream fileInputStream = new FileInputStream(path);
    public XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
    public Sheet sheet = xssfWorkbook.getSheet("Subjects");
    CreationHelper createHelper = xssfWorkbook.getCreationHelper();
    public EditSubjectList() throws IOException {
    }
    public ArrayList<Subject> Generate() throws IOException {
        String[] SubjectOUT;
        for(Row row : sheet){
            if(row.getRowNum()==0){continue;}
            String tempName = formatter.formatCellValue(row.getCell(0));
            String tempCode = formatter.formatCellValue(row.getCell(1));
            Subject selecSubject = new Subject(tempName,tempCode);
            subjectList.add(selecSubject);
        }
        return subjectList;
    }
    public void Add(String SubNameIN, String SubCodeIN) throws FileNotFoundException {
        int nextEmpty=0;
        for(Row row : sheet){
            if(formatter.formatCellValue(row.getCell(0))==""){
                break;
            }
            nextEmpty++;
        }
        Row row = sheet.createRow(nextEmpty);
        Cell cellName = row.createCell(0);
        cellName.setCellValue(SubNameIN);
        Cell cellCode = row.createCell(1);
        cellCode.setCellValue(SubCodeIN);
        try(OutputStream fileOut = new FileOutputStream(path)) {
            xssfWorkbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void Remove(Subject SubIN) throws FileNotFoundException{
        for(Row row : sheet){
            if(Objects.equals(SubIN.getSubjectCode(), row.getCell(0).getStringCellValue())|| Objects.equals(SubIN.getSubjectName(), row.getCell(1).getStringCellValue())){
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