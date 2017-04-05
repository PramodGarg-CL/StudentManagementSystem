package com.example.darknight.studentmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.darknight.studentmanagementsystem.R;
import com.example.darknight.studentmanagementsystem.model.Student;

/**
 * StudentDetailsActivity is a activity to add a new student to the data
 */
public class StudentDetailsActivity extends AppCompatActivity {

    private EditText mEdiTextStudentName, mEdiTextRollNumber, mEdiTextEmail, mEdiTextSchoolName;
    private String mStudentName, mRollNumber, mEmail, mSchoolName;
    private int mGender;
    private Button mButtonSaveChanges;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonMale, mRadioButtonFemale, mRadioButtonOther;
    private int mPositionInDataSet = -1;
    private boolean isEditMode = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        mEdiTextStudentName = (EditText) findViewById(R.id.et_studentName);
        mEdiTextRollNumber = (EditText) findViewById(R.id.et_roll_no);
        mEdiTextEmail = (EditText) findViewById(R.id.et_email);
        mEdiTextSchoolName = (EditText) findViewById(R.id.et_school_name);
        mButtonSaveChanges = (Button) findViewById(R.id.bt_save);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_gender);
        mRadioButtonMale = (RadioButton) mRadioGroup.findViewById(R.id.rb_gender_male);
        mRadioButtonFemale = (RadioButton) mRadioGroup.findViewById(R.id.rb_gender_female);
        mRadioButtonOther = (RadioButton) mRadioGroup.findViewById(R.id.rb_gender_other);

        Intent intent = getIntent();
        //Checks if the activity is alunched to add edit or view the data
        checkActivityModeType(intent);

        mButtonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                if (mStudentName.isEmpty() || mEmail.isEmpty() ||
                        mSchoolName.isEmpty())
                    Toast.makeText(StudentDetailsActivity.this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show();
                else
                    returnStudentDatatoActivity();
            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {


                if (mRadioButtonMale.isChecked())
                    mGender = 1;
                else if (mRadioButtonFemale.isChecked())
                    mGender = 0;
                else
                    mGender = 2;
            }
        });
    }

    /**
     * Checks whether the activity is opened in edit data, view data or add new data
     */
    private void checkActivityModeType(Intent intent) {
        int mode = intent.getIntExtra("mode", 1);
        if (mode == MainActivity.ACTIVITY_MODE_VIEW_DATA) disableEditableMode(intent);
        else if (mode == MainActivity.ACTIVITY_MODE_ADD_DATA) enableEditableMode();
        else if (mode == MainActivity.ACTIVITY_MODE_EDIT_DATA) setEditMode(intent);

    }

    /**
     * Enables the editing mode for the fields
     **/
    private void setEditMode(Intent intent) {
        enableEditableMode();
        isEditMode = true;
        mButtonSaveChanges.setText(getString(R.string.save_changes));
        mPositionInDataSet = intent.getIntExtra("position", -1);
        setDatatoViews((Student) intent.getSerializableExtra("student"));
    }

    private void setDatatoViews(Student student) {
        mEdiTextStudentName.setText(student.getmStudentName());
        mEdiTextEmail.setText(student.getmEmail());
        mEdiTextSchoolName.setText(student.getmSchoolName());
        mEdiTextRollNumber.setText(String.valueOf(student.getmRollNumber()));
        int gender = student.getmGender();
        if (gender == 0) mRadioButtonFemale.setChecked(true);
        else if (gender == 1) mRadioButtonMale.setChecked(true);
        else mRadioButtonOther.setChecked(true);

    }

    /**
     * Enables the Add new Data mode for the various Views
     **/
    private void enableEditableMode() {
        isEditMode = false;
        mButtonSaveChanges.setText(R.string.add_student);
        mEdiTextEmail.setEnabled(true);
        mEdiTextRollNumber.setEnabled(true);
        mEdiTextSchoolName.setEnabled(true);
        mEdiTextStudentName.setEnabled(true);
        mRadioGroup.setEnabled(true);
        mButtonSaveChanges.setVisibility(View.VISIBLE);

    }


    /**
     * Disables the editing mode for the various Views
     **/
    private void disableEditableMode(Intent intent) {
        setDatatoViews((Student) intent.getSerializableExtra("student"));
        isEditMode = false;
        mEdiTextEmail.setEnabled(false);
        mEdiTextRollNumber.setEnabled(false);
        mEdiTextSchoolName.setEnabled(false);
        mEdiTextStudentName.setEnabled(false);
        mRadioGroup.setEnabled(false);
        mRadioButtonMale.setEnabled(false);
        mRadioButtonFemale.setEnabled(false);
        mRadioButtonOther.setEnabled(false);
        mButtonSaveChanges.setVisibility(View.GONE);

    }


    /**
     * Exit this activity and returns the data to
     * previous activity in onActivityResult method
     */

    private void returnStudentDatatoActivity() {

        Student newStudent = new Student(mStudentName, Long.parseLong(mRollNumber), mSchoolName, mGender, mEmail);

        Intent intent = new Intent();
        if (isEditMode)
            intent.putExtra("position", mPositionInDataSet);
        intent.putExtra("student", newStudent);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Gets the data from the fields to the respective variables
     */
    public void getData() {
        mStudentName = mEdiTextStudentName.getText().toString().trim();
        mRollNumber = mEdiTextRollNumber.getText().toString().trim();
        mEmail = mEdiTextEmail.getText().toString().trim();
        mSchoolName = mEdiTextSchoolName.getText().toString().trim();

    }


}
