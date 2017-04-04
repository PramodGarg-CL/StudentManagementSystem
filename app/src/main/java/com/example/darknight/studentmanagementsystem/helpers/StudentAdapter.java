package com.example.darknight.studentmanagementsystem.helpers;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.darknight.studentmanagementsystem.R;
import com.example.darknight.studentmanagementsystem.VIewStudentData;

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
        return new StudentVH(view);
    }

    @Override
    public void onBindViewHolder(StudentVH holder, int position) {
        Student current = data.get(position);

        holder.name.setText(current.getStudentName());
        holder.rollNo.setText(String.valueOf(current.getRollNumber()));
        holder.schooName.setText(current.getSchoolName());
        int gender = current.getGender();
        if (gender == 1)
            holder.gender.setText(R.string.string_male);
        else if (gender == 0)
            holder.gender.setText(R.string.string_female);
        else
            holder.gender.setText(R.string.string_other);
        holder.email.setText(current.getEmail());


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class StudentVH extends RecyclerView.ViewHolder {
        TextView name, rollNo, schooName, gender, email;

        public StudentVH(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.studentName);
            schooName = (TextView) itemView.findViewById(R.id.schoolName);
            rollNo = (TextView) itemView.findViewById(R.id.rollNumber);
            gender = (TextView) itemView.findViewById(R.id.gender);
            email = (TextView) itemView.findViewById(R.id.email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence charSequence[] = {"View", "Edit", "Delete"};
                    AlertDialog.Builder alertbuilder = new AlertDialog.Builder(itemView.getContext());
                    alertbuilder.setTitle("Choose Option");
                    alertbuilder.setItems(charSequence, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                // View the data
                                case 0:
                                    Student selectedStudent = data.get(getAdapterPosition());
                                    Intent intent = new Intent(itemView.getContext(), VIewStudentData.class);
                                    intent.putExtra("name", selectedStudent.getStudentName());
                                    intent.putExtra("roll", "" + selectedStudent.getRollNumber());
                                    intent.putExtra("gender", selectedStudent.getGender());
                                    intent.putExtra("email", selectedStudent.getEmail());
                                    intent.putExtra("schoolName", selectedStudent.getSchoolName());
                                    itemView.getContext().startActivity(intent);
                                    break;
                                // Edit the data from the list
                                case 1:

                                    break;
                                // Delete the data from list
                                case 2:
                                    deleteDatafromList(getAdapterPosition());
                                    break;
                            }
                        }
                    });
                    alertbuilder.show();
                }
            });
        }

    }

    //delete item from the data
    private void deleteDatafromList(int adapterPosition) {
        Log.d("StudentAdapter", "deleteDatafromList: " + adapterPosition);
        data.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
        notifyItemRangeChanged(adapterPosition, data.size());

    }
}
