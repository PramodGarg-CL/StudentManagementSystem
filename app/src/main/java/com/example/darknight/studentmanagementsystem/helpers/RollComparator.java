package com.example.darknight.studentmanagementsystem.helpers;

import com.example.darknight.studentmanagementsystem.model.Student;

import java.util.Comparator;

/**
 * NameComparactor compares the teo @{@link Student} objects by RollNo
 */

public class RollComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getmRollNumber() < o2.getmRollNumber())
            return -1;
        else if (o1.getmRollNumber() == o2.getmRollNumber())
            return 0;
        else
            return 1;
    }
}
