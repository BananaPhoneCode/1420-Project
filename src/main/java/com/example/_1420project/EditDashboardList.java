package com.example._1420project;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class EditDashboardList {
    public ArrayList<Dashboard> DashboardList = new ArrayList<>();
    public DataFormatter formatter = new DataFormatter();
    public String path = "src/UMS_Data.xlsx";
    public FileInputStream fileInputStream = new FileInputStream(path);
    public XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
    Sheet sheet = xssfWorkbook.getSheet("Events ");
    CreationHelper createHelper = xssfWorkbook.getCreationHelper();
    public EditDashboardList() throws IOException {
    }

    public ArrayList<Dashboard> Generate() throws IOException {
        String[] DashboardOUT;
        for(Row row : sheet){
            if(row.getRowNum()==0){continue;}
            String tempName = formatter.formatCellValue(row.getCell(0));
            String tempStudent = formatter.formatCellValue(row.getCell(1));
            System.err.println(tempName+tempStudent);
            Dashboard selecDash = new Dashboard(tempName,tempStudent);
            DashboardList.add(selecDash);
        }
        return DashboardList;
    }
}
