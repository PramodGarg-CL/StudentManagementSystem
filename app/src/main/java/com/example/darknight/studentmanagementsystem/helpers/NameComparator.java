package com.example.darknight.studentmanagementsystem.helpers;

import com.example.darknight.studentmanagementsystem.model.Student;

import java.util.Comparator;

/**
 * NameComparactor compares the teo @{@link Student} objects by Name
 */

public class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getmStudentName().compareTo(o2.getmStudentName());
    }
}
