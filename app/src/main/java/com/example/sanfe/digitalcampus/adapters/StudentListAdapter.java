package com.example.sanfe.digitalcampus.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.activities.CreateSubject2Activity;
import com.example.sanfe.digitalcampus.logic.data.Student;

import java.util.ArrayList;

public class StudentListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Student> list;

    public StudentListAdapter (Context context, ArrayList<Student> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.studentslist_row, parent, false);
        }
        Student student = (Student) getItem(position);
        row.setTag(student);

        ImageView image = (ImageView) row.findViewById(R.id.studentslist_image);
        TextView name = (TextView) row.findViewById(R.id.studentslist_name);
        final CheckBox checkbox = (CheckBox) row.findViewById(R.id.studentslist_checkbox);

        image.setImageResource(R.mipmap.app_icon);
        name.setText(student.getStudentName());

        try {
            //per quan tirem enrere
            if (!CreateSubject2Activity.subject.getSubjectStudents().isEmpty()) {
                for (Student a : CreateSubject2Activity.subject.getSubjectStudents()) {
                    if (a.getStudentName().equals(student.getStudentName()) && a.getStudentImage() == student.getStudentImage()
                            && a.getStudentBirthdate().equals(student.getStudentBirthdate())) {
                        checkbox.setChecked(true);
                        CreateSubject2Activity.checkboxlist[position] = true;
                    }
                }
            }
        }catch (NullPointerException e) {}


       row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkbox.isChecked()) {
                 checkbox.setChecked(true);
                }
                else {
                 checkbox.setChecked(false);
                }
                CreateSubject2Activity.checkboxlist[position] = !CreateSubject2Activity.checkboxlist[position];
            }
        });

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateSubject2Activity.checkboxlist[position] = !CreateSubject2Activity.checkboxlist[position];
            }
        });

        return row;
    }
}
