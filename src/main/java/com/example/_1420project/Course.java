package com.example._1420project;

public class Course{
    private final String CourseName;
    private final String CourseCode;
    private final String CourseSection;
    private final String LectureTime;

    public Course(String CN, String CC, String CS, String LT){
        this.CourseName = CN;
        this.CourseCode = CC;
        this.CourseSection = CS;
        this.LectureTime= LT;
    }
    public String getCourseName(){
        return this.CourseName;
    }
    public String getCourseCode(){
        return this.CourseCode;
    }
    public String getCourseSection(){
        return this.CourseSection;
    }
    public String getLectureTime(){return this.LectureTime;}



}
