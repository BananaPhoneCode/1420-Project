package com.example._1420project;

public class Course{
    private final String CourseName;
    private final String CourseCode;
    private final String CourseSection;
    private final String CourseLecture;
    //private final String courseSubjectName;

    public Course(String CN, String CC, String CS, String LT){
        this.CourseName = CN;
        this.CourseCode = CC;
        this.CourseSection = CS;
        this.CourseLecture= LT;
    }
    //getters
    public String getCourseName(){
        return this.CourseName;
    }
    public String getCourseCode(){
        return this.CourseCode;
    }
    public String getCourseSection(){
        return this.CourseSection;
    }
    public String getCourseLecture(){return this.CourseLecture;}


}
