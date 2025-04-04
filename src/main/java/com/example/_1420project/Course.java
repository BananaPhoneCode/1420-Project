package com.example._1420project;

import java.util.ArrayList;
import java.util.List;

    //constructor
public class Course{
    private final String CourseName;
    private final String CourseCode;
    private final String CourseSection;
    private final String CourseLecture;
    private final String CourseCapacity;
    private final String CourseLocation;
    private final String CourseTeacher;

    //constructor
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
    public String getCourseCapacity(){return this.CourseCapacity;}
    public String getCourseLocation(){return this.CourseLocation;}
    public String getCourseTeacher(){return this.CourseTeacher;}
}

