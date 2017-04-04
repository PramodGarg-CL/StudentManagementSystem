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
import android.widget.Button;
import android.widget.Toast;

import com.example.darknight.studentmanagementsystem.helpers.Student;
import com.example.darknight.studentmanagementsystem.helpers.StudentAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private static final int GET_STUDENT_DATA = 1;
    private RecyclerView mRecyclerView;
    private Button mbButton;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Student> data;
    private StudentAdapter mStudentAdapter;
    private boolean isGridView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Dummy Data Ser
        data = new ArrayList<>();
        data.add(new Student("DummyName1", 12312, "DummySchool1", 1, "dummyemail@domain.com"));
        data.add(new Student("DummyName2", 16312, "DummySchool2", 1, "dummyemail@domain.com"));
        data.add(new Student("DummyName3", 17312, "DummySchool3", 1, "dummyemail@domain.com"));
        data.add(new Student("DummyName4", 165676, "DummySchool4", 1, "dummyemail@domain.com"));
        data.add(new Student("DummyName5", 142312, "DummySchool5", 1, "dummyemail@domain.com"));
        data.add(new Student("DummyName6", 167231, "DummySchool6", 1, "dummyemail@domain.com"));

        mStudentAdapter = new StudentAdapter(data);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        isGridView = false;
        //  mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(mStudentAdapter);


        mbButton = (Button) findViewById(R.id.button);
        mbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, AddStudent.class));
                Intent intent = new Intent(MainActivity.this, AddStudent.class);
                startActivityForResult(intent, GET_STUDENT_DATA);
            }
        });

    }

    // gets the data as results from the @AddStudent Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_STUDENT_DATA) {
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
            case R.id.sort_by_name:
                sortDataByName();
                Toast.makeText(getApplicationContext(), "Sort By Name", Toast.LENGTH_LONG).show();
                return true;
            case R.id.sort_by_roll:
                sortDataByRoll();
                Toast.makeText(getApplicationContext(), "Sort By Roll", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Sorts the data in the recycler view by Roll
    private void sortDataByRoll() {

    }

    // Sorts the data in the recycler view by name
    private void sortDataByName() {
        //Collections.sort(data);
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