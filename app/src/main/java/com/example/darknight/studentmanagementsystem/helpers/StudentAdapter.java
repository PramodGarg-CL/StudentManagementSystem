package com.example.darknight.studentmanagementsystem.helpers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.darknight.studentmanagementsystem.R;

import java.util.ArrayList;

/**
 * Created by darknight on 4/4/17.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentVH> {
    private ArrayList<Student> data;
    public boolean isGridView = false;

    public StudentAdapter(ArrayList<Student> data) {
        this.data = data;
    }

    @Override
    public StudentVH onCreateViewHolder(ViewGroup parent, int viewType) {
        // View view = LayoutInflater.from(parent.getContext()).inflate(isViewWithCatalog ? R.layout.product_row_layout_list : R.layout.product_row_layout_grid, null)
        View view = LayoutInflater.from(parent.getContext()).inflate(isGridView
                        ? R.layout.layout_grid_recycler_item
                        : R.layout.layout_linear_recycler_item,
                parent, false);
        StudentVH studentVH = new StudentVH(view);
        return studentVH;
    }

    @Override
    public void onBindViewHolder(StudentVH holder, int position) {
        Student current = data.get(position);

        holder.name.setText(current.getStudentName());
        holder.rollNo.setText(String.valueOf(current.getRollNumber()));
        holder.schooName.setText(current.getSchoolName());
        int gender = current.getGender();
        if (gender == 1)
            holder.gender.setText("Male");
        else if (gender == 2)
            holder.gender.setText("Female");
        else
            holder.gender.setText("Other");
        holder.email.setText(current.getEmail());


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class StudentVH extends RecyclerView.ViewHolder {
        TextView name, rollNo, schooName, gender, email;

        public StudentVH(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.studentName);
            schooName = (TextView) itemView.findViewById(R.id.schoolName);
            rollNo = (TextView) itemView.findViewById(R.id.rollNumber);
            gender = (TextView) itemView.findViewById(R.id.gender);
            email = (TextView) itemView.findViewById(R.id.email);
        }
    }
}
