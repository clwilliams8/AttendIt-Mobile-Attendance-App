package com.example.colto.attenditdraft3.Model;

/**
 * Created by colto on 11/1/2017.
 */

public class TeacherClass {
    private String teacherUsername;
    private String className;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String lateTime;
    private String absentTime;
    private String day1;
    private String day2;
    private String day3;
    private String day4;
    private String day5;
    private String day6;
    private String day7;
    private String location;

    public TeacherClass(){

    }

    public TeacherClass(String className,String teacherUsername, String startDate, String endDate, String startTime , String endTime, String lateTime, String absentTime,
                        String day1, String day2, String day3, String day4, String day5, String day6, String day7, String location) {
        this.className = className;
        this.teacherUsername = teacherUsername;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lateTime = lateTime;
        this.absentTime = absentTime;
        this.day1 = day1;
        this.day2 = day2;
        this.day3 = day3;
        this.day4 = day4;
        this.day5 = day5;
        this.day6 = day6;
        this.day7 = day7;
        this.location = location;
    }

    public String getTeacherUsername() {
        return teacherUsername;
    }

    public void setTeacherUsername(String teacherUsername) {this.teacherUsername = teacherUsername;}

    public String getClassName(){return className;}

    public void setClassName(String className){this.className = className;}

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String date) {
        this.startDate = date;
    }

    public String getEndDate(String endDate) {return  endDate;}

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

    public String getDay1() {return day1;}

    public void setDay1(String day1) {this.day1 = day1;}

    public String getDay2() {return day2;}

    public void setDay2(String day2) {this.day2 = day2;}

    public String getDay3() {return day3;}

    public void setDay3(String day3) {this.day3 = day3;}

    public String getDay4() {return day4;}

    public void setDay4(String day4) {this.day4 = day4;}

    public String getDay5() {return day5;}

    public void setDay5(String day5) {this.day5 = day5;}

    public String getDay6() {return day6;}

    public void setDay6(String day6) {this.day6 = day6;}

    public String getDay7() {return day7;}

    public void setDay7(String day7) {this.day7 = day7;}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
