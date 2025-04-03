package com.example._1420project;

import java.time.ZonedDateTime;

public class CalendarActivity {
    private ZonedDateTime date;
    private String StudentName;
    private Integer serviceNo;

    public CalendarActivity(ZonedDateTime date, String StudentName, Integer serviceNo) {
        this.date = date;
        this.StudentName = StudentName;
        this.serviceNo = serviceNo;
    }

    public ZonedDateTime getDate() { return date; }

    public void setDate(ZonedDateTime date) { this.date = date; }

    public String getStudentName() { return StudentName; }

    public void setClientName(String clientName) { this.StudentName = StudentName; }

    public Integer getServiceNo() { return serviceNo; }

    public void setServiceNo(Integer serviceNo) { this.serviceNo = serviceNo; }

    @Override
    public String toString() {
        return "CalendarActivity{" +
                "date=" + date +
                ", StudentName='" + StudentName + '\'' +
                ", serviceNo=" + serviceNo +
                '}';
    }
}
