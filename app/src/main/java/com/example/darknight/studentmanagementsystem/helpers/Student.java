package com.example.darknight.studentmanagementsystem.helpers;

/**
 * Created by darknight on 4/4/17.
 */

public class Student {

    private String studentName;
    private long rollNumber;
    private String schoolName;
    private int gender;
    private String email;

    public Student(String studentName, long rollNumber, String schoolName, int gender, String email) {
        this.studentName = studentName;
        this.rollNumber = rollNumber;
        this.schoolName = schoolName;
        this.gender = gender;
        this.email = email;
    }

    public String getStudentName() {
        return studentName;
    }

    public long getRollNumber() {
        return rollNumber;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public int getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }



}
