package com.example.darknight.studentmanagementsystem.helpers;

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

import com.example.darknight.studentmanagementsystem.AddStudent;
import com.example.darknight.studentmanagementsystem.R;

public class EditDetails extends AppCompatActivity {
    private EditText mStudentName, mRollNumber, mEmail, mSchoolName;
    private String studentName, rollNumber, email, schoolName;
    private int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        mStudentName = (EditText) findViewById(R.id.studentName);
        mRollNumber = (EditText) findViewById(R.id.rollNumber);
        mEmail = (EditText) findViewById(R.id.email);
        mSchoolName = (EditText) findViewById(R.id.schoolName);
        Button mButton = (Button) findViewById(R.id.button);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mButton.setText(R.string.save_changes);

        final RadioButton radio_male = (RadioButton) radioGroup.findViewById(R.id.gender_male);
        final RadioButton radio_female = (RadioButton) radioGroup.findViewById(R.id.gender_female);
        final RadioButton radio_other = (RadioButton) radioGroup.findViewById(R.id.gender_other);

        //get Data from the intent extras
        Intent intent = getIntent();

        mStudentName.setText(intent.getStringExtra("name"));
        mRollNumber.setText(intent.getStringExtra("roll"));
        mEmail.setText(intent.getStringExtra("email"));
        mSchoolName.setText(intent.getStringExtra("schoolName"));
        gender = intent.getIntExtra("gender", 0);

        // Set radio button for the gender
        if (gender == 1)
            radio_male.setChecked(true);
        else if (gender == 0)
            radio_female.setChecked(true);
        else
            radio_other.setChecked(true);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                if (studentName.isEmpty() || rollNumber.isEmpty() || email.isEmpty() ||
                        schoolName.isEmpty())
                    Toast.makeText(EditDetails.this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show();
                else
                    addStudentData();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                if (radio_male.isChecked())
                    gender = 1;
                else if (radio_female.isChecked())
                    gender = 0;
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


    }
}
