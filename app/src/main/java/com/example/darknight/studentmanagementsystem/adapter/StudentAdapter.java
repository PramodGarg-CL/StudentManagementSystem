package com.example.darknight.studentmanagementsystem.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.darknight.studentmanagementsystem.R;
import com.example.darknight.studentmanagementsystem.activity.MainActivity;
import com.example.darknight.studentmanagementsystem.activity.StudentDetailsActivity;
import com.example.darknight.studentmanagementsystem.model.Student;

import java.util.ArrayList;

/**
 * StudentAdapter is a adapter class for the RecyclerView
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentVH> {
    private ArrayList<Student> mStudentData;
    public boolean isGridView = false;

    public StudentAdapter(ArrayList<Student> mStudentData) {
        this.mStudentData = mStudentData;
    }

    @Override
    public StudentVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(isGridView
                        ? R.layout.item_linear_student
                        : R.layout.item_grid_student,
                parent, false);
        return new StudentVH(view);
    }

    @Override
    public void onBindViewHolder(StudentVH holder, int position) {
        Student current = mStudentData.get(position);

        holder.textViewName.setText(current.getmStudentName());
        holder.textViewRollNo.setText(String.valueOf(current.getmRollNumber()));
        holder.textViewSchooName.setText(current.getmSchoolName());
        int gender = current.getmGender();
        if (gender == 1)
            holder.textViewGender.setText(R.string.string_male);
        else if (gender == 0)
            holder.textViewGender.setText(R.string.string_female);
        else
            holder.textViewGender.setText(R.string.string_other);
        holder.textViewEmail.setText(current.getmEmail());


    }


    @Override
    public int getItemCount() {
        return mStudentData.size();
    }

    /**
     * ViewHolder Class for the StudentAdapter
     */
    public class StudentVH extends RecyclerView.ViewHolder {
        TextView textViewName, textViewRollNo, textViewSchooName, textViewGender, textViewEmail;

        public StudentVH(final View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.tv_studentName);
            textViewSchooName = (TextView) itemView.findViewById(R.id.tv_school_name);
            textViewRollNo = (TextView) itemView.findViewById(R.id.tv_label_roll_no);
            textViewGender = (TextView) itemView.findViewById(R.id.tv_gender);
            textViewEmail = (TextView) itemView.findViewById(R.id.tv_email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence[] charSequence = {"View", "Edit", "Delete"};
                    AlertDialog.Builder alertbuilder = new AlertDialog.Builder(itemView.getContext());
                    alertbuilder.setTitle("Choose Option");
                    alertbuilder.setItems(charSequence, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {

                                // View the mStudentData
                                case 0:
                                    Intent intent = new Intent(itemView.getContext(), StudentDetailsActivity.class);
                                    intent.putExtra("mode", MainActivity.ACTIVITY_MODE_VIEW_DATA);
                                    intent.putExtra("student", mStudentData.get(getAdapterPosition()));
                                    itemView.getContext().startActivity(intent);
                                    break;

                                // Edit the mStudentData from the list
                                case 1:
                                    Intent intentEdit = new Intent(itemView.getContext(), StudentDetailsActivity.class);
                                    intentEdit.putExtra("mode", MainActivity.ACTIVITY_MODE_EDIT_DATA);
                                    intentEdit.putExtra("student", mStudentData.get(getAdapterPosition()));
                                    intentEdit.putExtra("position", getAdapterPosition());

                                    MainActivity mainActivity = (MainActivity) itemView.getContext();
                                    mainActivity.startActivityForResult(intentEdit, MainActivity.GET_OLD_STUDENT_DATA);
                                    break;
                                // Delete the mStudentData from list
                                case 2:
                                    deleteDatafromList(itemView.getContext(), getAdapterPosition());
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                    alertbuilder.show();
                }
            });
        }

    }

    /**
     * Takes the intent and student object and student parameteres to
     * the the intent object using intent exttras
     */

    private void addDatatoIntent(Intent intent, Student student) {

        intent.putExtra("name", student.getmStudentName());
        intent.putExtra("roll", "" + student.getmRollNumber());
        intent.putExtra("gender", student.getmGender());
        intent.putExtra("email", student.getmEmail());
        intent.putExtra("schoolName", student.getmSchoolName());

    }

    /**
     * Deletes the data from the arrayList data at given adapterPostion    *
     *
     * @param adapterPosition
     */
    private void deleteDatafromList(Context context, int adapterPosition) {
        // Log.d("StudentAdapter", "deleteDatafromList: " + adapterPosition);
        try {
            mStudentData.remove(adapterPosition);
        } catch (IndexOutOfBoundsException e) {
            Toast.makeText(context, "Please try again", Toast.LENGTH_SHORT).show();
        }
        notifyItemRemoved(adapterPosition);
        notifyItemRangeChanged(adapterPosition, mStudentData.size());

    }

    /**
     * Add new student to the data
     *
     * @param student
     */
    public void addData(Student student) {
        mStudentData.add(student);
        notifyItemInserted(mStudentData.size() - 1);

    }

    public void editData(int position, Student student) {
        Log.d("StudentAdapter", "editData: " + position);
        mStudentData.set(position, student);
        notifyItemChanged(position);
    }

    ;
}
