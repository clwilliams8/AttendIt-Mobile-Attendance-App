package com.example.colto.attenditdraft3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by colto on 11/19/2017.
 */

public class MyClassesModel {

    String className, classTimes, teacherName, day1, day2, day3, day4, day5, day6, day7;

    public MyClassesModel(){

    }


    public MyClassesModel(String className, String classTimes, String teacherName, String day1, String day2, String day3, String day4, String day5, String day6, String day7) {
        this.className = className;
        this.classTimes = classTimes;
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

    public String getClassTimes() {
        return classTimes;
    }

    public void setClassTimes(String classTimes) {
        this.classTimes = classTimes;
    }



    public String getTeacherName() {return teacherName;}

    public void setTeacherName(String teacherName) {this.teacherName = teacherName;}



    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("className", className);
        result.put("classTimes", classTimes);
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
