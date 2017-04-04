package com.example.darknight.studentmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.darknight.studentmanagementsystem.helpers.NameComparator;
import com.example.darknight.studentmanagementsystem.helpers.RollComparator;
import com.example.darknight.studentmanagementsystem.helpers.Student;
import com.example.darknight.studentmanagementsystem.helpers.StudentAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public static final int GET_NEW_STUDENT_DATA = 1;
    private RecyclerView mRecyclerView;
    private ArrayList<Student> data;
    private StudentAdapter mStudentAdapter;
    private boolean isGridView = false;
    private boolean isSortByRoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Spinner spinner_sort = (Spinner) findViewById(R.id.spinner);

        // adding options to spinner
        ArrayList<String> sort_menu = new ArrayList<>();
        sort_menu.add("Sort By Name");
        sort_menu.add("Sort By Roll");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sort_menu);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_sort.setAdapter(dataAdapter);
        spinner_sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        // Dummy Data Set
        data = new ArrayList<>();
        data.add(new Student("Pramod", 464, "DummySchool1", 1, "dummyemail@domain.com"));
        data.add(new Student("Rajat", 468, "DummySchool2", 1, "dummyemail@domain.com"));
        data.add(new Student("Ranveer", 470, "DummySchool3", 1, "dummyemail@domain.com"));
        data.add(new Student("Anmol", 1090, "DummySchool4", 1, "dummyemail@domain.com"));
        data.add(new Student("Danish", 1091, "DummySchool5", 1, "dummyemail@domain.com"));
        data.add(new Student("Shray", 487, "DummySchool6", 1, "dummyemail@domain.com"));
        data.add(new Student("Namita", 268, "DummySchool6", 0, "dummyemail@domain.com"));
        data.add(new Student("Priya", 1056, "DummySchool6", 0, "dummyemail@domain.com"));

        mStudentAdapter = new StudentAdapter(data);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        isGridView = false;
        //  mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(mStudentAdapter);


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, AddStudent.class));
                Intent intent = new Intent(MainActivity.this, AddStudent.class);
                startActivityForResult(intent, GET_NEW_STUDENT_DATA);
            }
        });


    }

    // gets the data as results from the @AddStudent Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_NEW_STUDENT_DATA) {
            if (resultCode == RESULT_OK) {
                String studentname = data.getStringExtra("studentName");
                String schoolname = data.getStringExtra("schoolName");
                int gender = data.getIntExtra("gender", 0);
                String rollno = data.getStringExtra("rollNumber");
                String email = data.getStringExtra("email");
                Log.d("MainActivity", "onActivityResult: StudentName" + studentname +
                        "schoolname:  " + schoolname +
                        "gender:  " + gender +
                        "rollno:  " + rollno +
                        "email:  " + email);

                addDatatoList(studentname, schoolname, gender, rollno, email);

            }
        }
    }

    // add the data to the ArrayList
    private void addDatatoList(String studentname, String schoolname, int gender, String rollno, String email) {
        data.add(new Student(studentname, Long.parseLong(rollno), schoolname, gender, email));
        mStudentAdapter.notifyItemInserted(data.size() - 1);
        if (isSortByRoll)
            sortDataByRoll();
        else
            sortDataByName();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);//Menu Resource, Menu
        return true;
    }


    // THe muenu list
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_ayout:
                changeRecyclerLayout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Sorts the data in the recycler view by Roll
    private void sortDataByRoll() {
        Collections.sort(data, new RollComparator());
        mStudentAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mStudentAdapter);
        isSortByRoll = true;
    }

    // Sorts the data in the recycler view by name
    private void sortDataByName() {
        Collections.sort(data, new NameComparator());
        mStudentAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mStudentAdapter);
        isSortByRoll = false;

    }

    // shuffles between the grid layout and the linear layout
    private void changeRecyclerLayout() {
        if (isGridView) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            isGridView = false;
            mStudentAdapter.isGridView = false;
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            isGridView = true;
            mStudentAdapter.isGridView = true;
        }
        mStudentAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mStudentAdapter);

    }


}
