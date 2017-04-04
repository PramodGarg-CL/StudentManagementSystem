package com.example.darknight.studentmanagementsystem.helpers;

import java.util.Comparator;

/**
 * Created by darknight on 4/4/17.
 */

public class RollComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getRollNumber() < o2.getRollNumber())
            return -1;
        else if (o1.getRollNumber() == o2.getRollNumber())
            return 0;
        else
            return 1;
    }
}
