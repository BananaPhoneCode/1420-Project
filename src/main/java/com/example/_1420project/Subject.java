package com.example._1420project;

public class Subject{
    private final String SubjectName;
    private final String SubjectCode;
    public Subject(String SN, String SC){
        this.SubjectName = SN;
        this.SubjectCode = SC;
    }
    public String getSubjectName(){
        return this.SubjectName;
    }
    public String getSubjectCode(){
        return this.SubjectCode;
    }


}

