package com.example.darknight.studentmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class VIewStudentData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_data);

        TextView t_rollNo = (TextView) findViewById(R.id.rollNo);
        TextView t_name = (TextView) findViewById(R.id.name);
        TextView t_gender = (TextView) findViewById(R.id.gender);
        TextView t_email = (TextView) findViewById(R.id.email);
        TextView t_schoolName = (TextView) findViewById(R.id.schoolName);

        //get Data from the intent extras
        Intent intent = getIntent();
        String roll = intent.getStringExtra("roll");
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String schoolName = intent.getStringExtra("schoolName");

// set Gender field
        int gender = intent.getIntExtra("gender", 0);

        if (gender == 0)
            t_gender.setText(getString(R.string.string_female));
        else if (gender == 1)
            t_gender.setText(getString(R.string.string_male));
        else
            t_gender.setText(getString(R.string.string_other));

        // set other fields
        t_rollNo.setText(roll);
        t_name.setText(name);
        t_email.setText(email);
        t_schoolName.setText(schoolName);

    }
}
