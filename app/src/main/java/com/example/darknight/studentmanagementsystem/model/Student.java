package com.example.darknight.studentmanagementsystem.model;

/**
 * Created by darknight on 4/4/17.
 */

import java.io.Serializable;

/**
 * Model Class for the Students
 */
public class Student implements Serializable {

    private String mStudentName;
    private long mRollNumber;
    private String mSchoolName;
    private int mGender;
    private String mEmail;

    /**
     * Constructor for intialising the Student object with ites details
     */

    public Student(String studentName, long mRollNumber, String schoolName, int gender, String email) {
        this.mStudentName = studentName;
        this.mRollNumber = mRollNumber;
        this.mSchoolName = schoolName;
        this.mGender = gender;
        this.mEmail = email;
    }

    /**
     * Setter methods
     **/
    public void setmStudentName(String mStudentName) {
        this.mStudentName = mStudentName;
    }

    public void setmRollNumber(long mRollNumber) {
        this.mRollNumber = mRollNumber;
    }

    public void setmSchoolName(String mSchoolName) {
        this.mSchoolName = mSchoolName;
    }

    public void setmGender(int mGender) {
        this.mGender = mGender;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    /**
     * Getter methods
     */
    public String getmStudentName() {
        return mStudentName;
    }

    public long getmRollNumber() {
        return mRollNumber;
    }

    public String getmSchoolName() {
        return mSchoolName;
    }

    public int getmGender() {
        return mGender;
    }

    public String getmEmail() {
        return mEmail;
    }


}
