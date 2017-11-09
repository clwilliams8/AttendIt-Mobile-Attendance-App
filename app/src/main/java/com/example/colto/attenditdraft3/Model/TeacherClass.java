package com.example.colto.attenditdraft3.Model;

/**
 * Created by colto on 11/1/2017.
 */

public class TeacherClass {
    private String teacherUsername;
    private String className;
    private String date;
    private String startTime;
    private String endTime;
    private String lateTime;
    private String absentTime;
    private String daysOfTheWeek;
    private String location;
    private String studentsEnrolled;

    public TeacherClass(){

    }

    public TeacherClass(String teacherUsername, String className,String date, String startTime , String endTime, String lateTime, String absentTime,
                        String daysOfTheWeek, String location, String studentsEnrolled) {
        this.teacherUsername = teacherUsername;
        this.className = className;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lateTime = lateTime;
        this.absentTime = absentTime;
        this.daysOfTheWeek = daysOfTheWeek;
        this.location = location;
        this.studentsEnrolled = studentsEnrolled;
    }

    public String getTeacherUsername() {
        return teacherUsername;
    }

    public void setTeacherUsername(String teacherUsername) {this.teacherUsername = teacherUsername;}

    public String getClassName(){return className;}

    public void setClassName(String className){this.className = className;}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLateTime() {
        return lateTime;
    }

    public void setLateTime(String lateTime) {
        this.lateTime = lateTime;
    }

    public String getAbsentTime() {
        return absentTime;
    }

    public void setAbsentTime(String absentTime) {
        this.absentTime = absentTime;
    }

    public String getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

    public void setDaysOfTheWeek(String daysOfTheWeek) {
        this.daysOfTheWeek = daysOfTheWeek;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(String studentsEnrolled) {this.studentsEnrolled = studentsEnrolled;}
}
