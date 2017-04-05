package com.example.darknight.studentmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.example.darknight.studentmanagementsystem.R;
import com.example.darknight.studentmanagementsystem.adapter.StudentAdapter;
import com.example.darknight.studentmanagementsystem.helpers.NameComparator;
import com.example.darknight.studentmanagementsystem.helpers.RollComparator;
import com.example.darknight.studentmanagementsystem.model.Student;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This activity displays the list of the added Students in RecyclerView
 * and button to add launch new activity(@{@link StudentDetailsActivity to add new @{@link Student}
 */

public class MainActivity extends AppCompatActivity {
    public static final int GET_NEW_STUDENT_DATA = 11;
    public static final int GET_OLD_STUDENT_DATA = 12;
    private static final String TAG = MainActivity.class.getName();
    private RecyclerView mRecyclerView;
    private ArrayList<Student> data;
    private StudentAdapter mStudentAdapter;
    private boolean isGridView = false;
    private boolean isSortByRoll;
    private ToggleButton mToggleButton;
    private static int roll = 123;  // dummy roll no for dummy data
    public final static int ACTIVITY_MODE_VIEW_DATA = 1;
    public final static int ACTIVITY_MODE_ADD_DATA = 2;
    public final static int ACTIVITY_MODE_EDIT_DATA = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_students);
        Spinner spinnerSort = (Spinner) findViewById(R.id.sp_sort);
        mToggleButton = (ToggleButton) findViewById(R.id.tb_change_layout);
        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeRecyclerLayout();
            }
        });


        // adding options to spinner
        ArrayList<String> arrayListSort = new ArrayList<>();
        arrayListSort.add("Sort By Name");
        arrayListSort.add("Sort By Roll");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListSort);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerSort.setAdapter(dataAdapter);
        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    sortDataByName();
                else
                    sortDataByRoll();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sortDataByName();

            }
        });
        data = new ArrayList<>();
        data.add(new Student("DummyName", roll, "DummySchoolName", 0, "dummyemail@domain.com"));
        mStudentAdapter = new StudentAdapter(data);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mToggleButton.setText("GridView");
        isGridView = false;
        //  mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(mStudentAdapter);

        // Button to add new Student Data
        Button button = (Button) findViewById(R.id.bt_add_student);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, StudentDetailsActivity.class));
                Intent intent = new Intent(MainActivity.this, StudentDetailsActivity.class);
                intent.putExtra("mode", ACTIVITY_MODE_ADD_DATA);
                startActivityForResult(intent, GET_NEW_STUDENT_DATA);
            }
        });


    }

    /**
     * gets the data as results from the @{@link StudentDetailsActivity }Activity
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_NEW_STUDENT_DATA) {
            if (resultCode == RESULT_OK) {
                Student student = (Student) data.getSerializableExtra("student");
                mStudentAdapter.addData(student);

            }
        } else if (requestCode == GET_OLD_STUDENT_DATA) {
            if (resultCode == RESULT_OK) {
                Student student = (Student) data.getSerializableExtra("student");
                int position = data.getIntExtra("position", mStudentAdapter.getItemCount());
                mStudentAdapter.editData(position, student);

            }
        }
    }


    /**
     * Sorts the data in the recycler view by RollNo. using @{@link RollComparator}
     */

    private void sortDataByRoll() {
        Collections.sort(data, new RollComparator());
        mStudentAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mStudentAdapter);
        isSortByRoll = true;
    }

    /**
     * Sorts the data in the recycler view by RollNo. using @{@link NameComparator}
     */
    private void sortDataByName() {
        Collections.sort(data, new NameComparator());
        mStudentAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mStudentAdapter);
        isSortByRoll = false;

    }

    /**
     * Switch between the RecyclerView layout manager -Grid and Linear Layout
     */
    private void changeRecyclerLayout() {
        if (mToggleButton.isChecked()) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            isGridView = false;
            mStudentAdapter.isGridView = false;
            mToggleButton.setText("GridView");
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            isGridView = true;
            mStudentAdapter.isGridView = true;
            mToggleButton.setText("LinearLayout");
        }
        mStudentAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mStudentAdapter);

    }


}
