package com.example.colto.attenditdraft3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by colto on 11/19/2017.
 */

public class MyClassesModel {

    String className, classStartTime, classLateTime, classAbsentTime, teacherName, day1, day2, day3, day4, day5, day6, day7;

    public MyClassesModel(){

    }


    public MyClassesModel(String className, String classStartTime, String classLateTime, String classAbsentTime, String teacherName, String day1, String day2, String day3, String day4, String day5, String day6, String day7) {
        this.className = className;
        this.classStartTime = classStartTime;
        this.classLateTime = classLateTime;
        this.classAbsentTime = classAbsentTime;
        this.teacherName = teacherName;
        this.day1 = day1;
        this.day2 = day2;
        this.day3 = day3;
        this.day4 = day4;
        this.day5 = day5;
        this.day6 = day6;
        this.day7 = day7;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassStartTime() {return classStartTime;}

    public void setClassStartTime(String classStartTime) {this.classStartTime = classStartTime;}

    public String getClassLateTime() {return classLateTime;}

    public void setClassLateTime(String classLateTime) {this.classLateTime = classLateTime;}

    public String getClassAbsentTime() {return classAbsentTime;}

    public void setClassAbsentTime(String classAbsentTime) {this.classAbsentTime = classAbsentTime;}

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

    public String getTeacherName() {return teacherName;}

    public void setTeacherName(String teacherName) {this.teacherName = teacherName;}



    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("className", className);
        result.put("classStartTime", classStartTime);
        result.put("classLateTime", classLateTime);
        result.put("classAbsentTime", classAbsentTime);
        result.put("day1", day1);
        result.put("day2", day2);
        result.put("day3", day3);
        result.put("day4", day4);
        result.put("day5", day5);
        result.put("day6", day6);
        result.put("day7", day7);

        return result;
    }

}
