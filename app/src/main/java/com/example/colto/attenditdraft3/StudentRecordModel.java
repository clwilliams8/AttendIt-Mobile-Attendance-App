package com.example.colto.attenditdraft3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by colto on 11/30/2017.
 */

public class StudentRecordModel {

    String className, studentName, date, signInTime;

    Boolean present, late, absent;

    public StudentRecordModel() {
    }


    public StudentRecordModel(String className, String studentName, String date, String signInTime, Boolean present, Boolean late, Boolean absent) {
        this.className = className;
        this.studentName = studentName;
        this.date = date;
        this.signInTime = signInTime;
        this.present = present;
        this.late = late;
        this.absent = absent;
    }

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public String getSignInTime() {return signInTime;}

    public void setSignInTime(String signInTime) {this.signInTime = signInTime;}

    public Boolean getPresent() {return present;}

    public void setPresent(Boolean present) {this.present = present;}

    public Boolean getLate() {return late;}

    public void setLate(Boolean late) {this.late = late;}

    public Boolean getAbsent() {return absent;}

    public void setAbsent(Boolean absent) {this.absent = absent;}

    public String getClassName() {return className;}

    public void setClassName(String className) {this.className = className;}

    public String getStudentName() {return studentName;}

    public void setStudentName(String studentName) {this.studentName = studentName;}

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("className", className);
        result.put("studentName", studentName);
        result.put("date", date);
        result.put("signInTime", signInTime);
        result.put("present", present);
        result.put("late", late);
        result.put("absent", absent);

        return result;
    }

}


