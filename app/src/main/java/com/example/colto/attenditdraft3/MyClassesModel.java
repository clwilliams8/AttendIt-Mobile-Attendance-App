package com.example.colto.attenditdraft3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by colto on 11/19/2017.
 */

public class MyClassesModel {

    String className, classTimes, classDays;

    public MyClassesModel(){

    }


    public MyClassesModel(String className, String classTimes, String classDays) {
        this.className = className;
        this.classTimes = classTimes;
        this.classDays = classDays;
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

    public String getClassDays() {
        return classDays;
    }

    public void setClassDays(String classDays) {
        this.classDays = classDays;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("className", className);
        result.put("classTimes", classTimes);
        result.put("classDays", classDays);

        return result;
    }

}
