package com.example._1420project;

public class Student {
    private final String StudentName;
    private final String StudentId;
    private final String StudentAddress;
    private final String StudentTelephone;
    private final String StudentTuition;
    private final String StudentCourses;
    private final String StudentEmail;
    private final String StudentGrade;
    private final String StudentSem;
    private final String StudentSub;
    private final String StudentLev;
    private final String StudentTitle;
    private final String StudentProg; // this is the progress field

    public Student(String SN, String SI, String SA, String ST, String STT, String SC,
                   String SE, String SG, String SS, String SSS, String SL, String STTT, String SP) {
        this.StudentName = SN;
        this.StudentId = SI;
        this.StudentAddress = SA;
        this.StudentTelephone = ST;
        this.StudentTuition = STT;
        this.StudentCourses = SC;
        this.StudentEmail = SE;
        this.StudentGrade = SG;
        this.StudentSem = SS;
        this.StudentSub = SSS;
        this.StudentLev = SL;
        this.StudentTitle = STTT;
        this.StudentProg = SP;
    }

    public String getStudentName() { return this.StudentName; }
    public String getStudentId() { return this.StudentId; }
    public String getStudentAddress() { return this.StudentAddress; }
    public String getStudentTelephone() { return this.StudentTelephone; }
    public String getStudentTuition() { return this.StudentTuition; }
    public String getStudentCourses() { return this.StudentCourses; }
    public String getStudentEmail() { return this.StudentEmail; }
    public String getStudentGrade() { return this.StudentGrade; }
    public String getStudentSem() { return this.StudentSem; }
    public String getStudentSub() { return this.StudentSub; }
    public String getStudentLev() { return this.StudentLev; }
    public String getStudentTitle() { return this.StudentTitle; }
    public String getStudentProg() { return this.StudentProg; } // progress
}
