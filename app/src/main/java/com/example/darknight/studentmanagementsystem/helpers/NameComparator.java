package com.example.darknight.studentmanagementsystem.helpers;

import java.util.Comparator;

/**
 * Created by darknight on 4/4/17.
 */

public class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getStudentName().compareTo(o2.getStudentName());
    }
}
