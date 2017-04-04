package com.example.darknight.studentmanagementsystem;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {
    private EditText mStudentName, mRollNumber, mEmail, mSchoolName;
    private String studentName, rollNumber, email, schoolName;
    private int gender = 0;
    private Button mButton;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        mStudentName = (EditText) findViewById(R.id.studentName);
        mRollNumber = (EditText) findViewById(R.id.rollNumber);
        mEmail = (EditText) findViewById(R.id.email);
        mSchoolName = (EditText) findViewById(R.id.schoolName);
        mButton = (Button) findViewById(R.id.button);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                if (studentName.isEmpty() || rollNumber.isEmpty() || email.isEmpty() ||
                        schoolName.isEmpty())
                    Toast.makeText(AddStudent.this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show();
                else
                    addStudentData();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radio_male = (RadioButton) group.findViewById(R.id.gender_male);
                RadioButton radio_female = (RadioButton) group.findViewById(R.id.gender_female);
                RadioButton radio_other = (RadioButton) group.findViewById(R.id.gender_other);

                if (radio_male.isChecked())
                    gender = 0;
                else if (radio_female.isChecked())
                    gender = 1;
                else
                    gender = 2;
            }
        });
    }

    private void addStudentData() {

        Intent intent = new Intent();
        intent.putExtra("studentName", studentName);
        intent.putExtra("rollNumber", rollNumber);
        intent.putExtra("email", email);
        intent.putExtra("gender", gender);
        intent.putExtra("schoolName", schoolName);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void getData() {
        studentName = mStudentName.getText().toString().trim();
        rollNumber = mRollNumber.getText().toString().trim();
        email = mEmail.getText().toString().trim();
        schoolName = mSchoolName.getText().toString().trim();
        Toast.makeText(this, "data: " + gender, Toast.LENGTH_SHORT).show();


    }


}
