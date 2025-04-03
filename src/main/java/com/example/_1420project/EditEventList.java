package com.example._1420project;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class EditEventList {
    public ArrayList<Event> EventList = new ArrayList<>();
    public DataFormatter formatter = new DataFormatter();
    public String path = "src/UMS_Data.xlsx";
    public FileInputStream fileInputStream = new FileInputStream(path);
    public XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
    Sheet sheet = xssfWorkbook.getSheet("Events ");
    CreationHelper createHelper = xssfWorkbook.getCreationHelper();
    public EditEventList() throws IOException {
    }
    public ArrayList<Event> Generate() throws IOException {
        String[] EventOUT;
        for(Row row : sheet){
            if(row.getRowNum()==0){continue;}
            String tempCode = formatter.formatCellValue(row.getCell(0));
            String tempName = formatter.formatCellValue(row.getCell(1));
            String tempDescription = formatter.formatCellValue(row.getCell(2));
            String tempLocation = formatter.formatCellValue(row.getCell(3));
            String tempDateNTime = formatter.formatCellValue(row.getCell(4));
            String tempCapacity = formatter.formatCellValue(row.getCell(5));
            String tempCost = formatter.formatCellValue(row.getCell(6));
            System.err.println(tempCode+tempName+tempDescription+tempLocation+tempDateNTime+tempCapacity+tempCost);
            Event selecEvent = new Event(tempCode,tempName,tempDescription,tempLocation,tempDateNTime,tempCapacity,tempCost);
            EventList.add(selecEvent);
        }
        return EventList;
    }
    public void Add(String EveCodeIN, String EveNameIN, String EveDescIN, String EveLocaIN, String EveDTIN, String EveCapIN, String EveCostIN) throws FileNotFoundException {
        int nextEmpty=0;
        for(Row row : sheet){
            if(Objects.equals(formatter.formatCellValue(row.getCell(0)), "")){
                break;
            }
            nextEmpty++;
        }
        Row row = sheet.createRow(nextEmpty);
        Cell cellCode = row.createCell(0);
        cellCode.setCellValue(EveCodeIN);
        Cell cellName = row.createCell(1);
        cellName.setCellValue(EveNameIN);
        Cell cellDesc = row.createCell(2);
        cellDesc.setCellValue(EveDescIN);
        Cell cellLocation = row.createCell(3);
        cellLocation.setCellValue(EveLocaIN);
        Cell cellDateNTime = row.createCell(4);
        cellDateNTime.setCellValue(EveDTIN);
        Cell cellCapacity = row.createCell(5);
        cellCapacity.setCellValue(EveCapIN);
        Cell cellCost = row.createCell(6);
        cellCost.setCellValue(EveCostIN);
        try(OutputStream fileOut = new FileOutputStream(path)) {
            xssfWorkbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void Remove(Event SubIN) throws FileNotFoundException{
        for(Row row : sheet){
            String tempCode = formatter.formatCellValue(row.getCell(0));
            String tempName = formatter.formatCellValue(row.getCell(1));
            String tempDescription = formatter.formatCellValue(row.getCell(2));
            String tempLocation = formatter.formatCellValue(row.getCell(3));
            String tempDateNTime = formatter.formatCellValue(row.getCell(4));
            String tempCapacity = formatter.formatCellValue(row.getCell(5));
            String tempCost = formatter.formatCellValue(row.getCell(6));
            if(Objects.equals(SubIN.getEventCode(), tempCode) || Objects.equals(SubIN.getEventName(), tempName) || Objects.equals(SubIN.getEventDescription(), tempDescription) || Objects.equals(SubIN.getEventLocation(), tempLocation) || Objects.equals(SubIN.getEventDateNTime(), tempDateNTime) || Objects.equals(SubIN.getEventCapacity(), tempCapacity) || Objects.equals(SubIN.getEventCost(), tempCost)){
                row.removeCell(row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                row.removeCell(row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                row.removeCell(row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                row.removeCell(row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                row.removeCell(row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                row.removeCell(row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                row.removeCell(row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
            }
        }
        try(OutputStream fileOut = new FileOutputStream(path)) {
            xssfWorkbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void Edit(Event SubIN, String SubCodeIN, String SubNameIN, String SubDescIN, String SubLocationIN, String SubDTIN, String SubCapIN, String SubCostIN) throws FileNotFoundException{
        for(Row row : sheet){
            String tempCode = formatter.formatCellValue(row.getCell(0));
            String tempName = formatter.formatCellValue(row.getCell(1));
            String tempDescription = formatter.formatCellValue(row.getCell(2));
            String tempLocation = formatter.formatCellValue(row.getCell(3));
            String tempDateNTime = formatter.formatCellValue(row.getCell(4));
            String tempCapacity = formatter.formatCellValue(row.getCell(5));
            String tempCost = formatter.formatCellValue(row.getCell(6));
            if(Objects.equals(SubIN.getEventCode(), tempCode) || Objects.equals(SubIN.getEventName(), tempName) || Objects.equals(SubIN.getEventDescription(), tempDescription) || Objects.equals(SubIN.getEventLocation(), tempLocation) || Objects.equals(SubIN.getEventDateNTime(), tempDateNTime) || Objects.equals(SubIN.getEventCapacity(), tempCapacity) || Objects.equals(SubIN.getEventCost(), tempCost)){
                if(!SubCodeIN.isEmpty())row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(SubCodeIN);
                if(!SubNameIN.isEmpty())row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(SubNameIN);
                if(!SubCodeIN.isEmpty())row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(SubDescIN);
                if(!SubNameIN.isEmpty())row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(SubLocationIN);
                if(!SubCodeIN.isEmpty())row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(SubDTIN);
                if(!SubNameIN.isEmpty())row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(SubCapIN);
                if(!SubCodeIN.isEmpty())row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(SubCostIN);
            }
        }
        try(OutputStream fileOut = new FileOutputStream(path)) {
            xssfWorkbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
