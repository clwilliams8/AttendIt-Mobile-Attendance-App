package com.example.colto.attenditdraft3;

/**
 * Created by colto on 11/20/2017.
 */

public class StudentsEnrolledModel {

    String studentRealName, studentUsername;

    public StudentsEnrolledModel() {

    }

    public StudentsEnrolledModel(String studentRealName, String studentUsername) {
        this.studentRealName = studentRealName;
        this.studentUsername = studentUsername;
    }

    public String getStudentRealName() {return studentRealName;}

    public void setStudentRealName(String studentRealName) {this.studentRealName = studentRealName;}

    public String getStudentUsername() {return studentUsername;}

    public void setStudentUsername(String studentUsername) {this.studentUsername = studentUsername;}




}
