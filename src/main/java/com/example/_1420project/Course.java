package com.example._1420project;

public class Course{
    private final String CourseName;
    private final String CourseCode;
    private final String CourseSection;
    private final String CourseLecture;
    //private final String courseSubjectName;
    private final String CourseCapacity;
    private final String CourseLocation;
    private final String CourseTeacher;

    public Course(String CN, String CC, String CS, String LT, String CCA,String CL,String CT){
        this.CourseName = CN;
        this.CourseCode = CC;
        this.CourseSection = CS;
        this.CourseLecture= LT;
        this.CourseCapacity = CCA;
        this.CourseLocation= CL;
        this.CourseTeacher = CT;

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
    public String getCourseCapacity(){return this.CourseLecture;}
    public String getCourseLocation(){return this.CourseLecture;}
    public String getCourseTeacher(){return this.CourseLecture;}


}
