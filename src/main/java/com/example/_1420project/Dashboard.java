package com.example._1420project;

public class Dashboard {
    public String eventName;
    public String RecentStudent;

    public Dashboard (String eventName, String RecentStudent) {
        this.eventName = eventName;
        this.RecentStudent = RecentStudent;
    }
    public final String getEventName(){return eventName;}
    public final String getRecentStudent(){return RecentStudent;}

    public void setEventName(String eventName){this.eventName = eventName;}
    public void setRecentStudent(String RecentStudent){this.RecentStudent = RecentStudent;}
}
