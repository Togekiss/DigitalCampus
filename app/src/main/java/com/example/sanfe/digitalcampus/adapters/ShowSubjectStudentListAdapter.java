package com.example.sanfe.digitalcampus.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.activities.CreateSubject2Activity;
import com.example.sanfe.digitalcampus.logic.data.Student;

import java.util.ArrayList;

public class ShowSubjectStudentListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Student> list;

    public ShowSubjectStudentListAdapter (Context context, ArrayList<Student> list) {
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
            row = inflater.inflate(R.layout.studentshow_row, parent, false);
            row.setClickable(true);
        }

        Student student = (Student) getItem(position);
        row.setTag(student);

        ImageView image = (ImageView) row.findViewById(R.id.studentsshow_image);
        TextView name = (TextView) row.findViewById(R.id.studentsshow_name);
        TextView degree = (TextView) row.findViewById(R.id.studentsshow_class);

        image.setImageResource(student.getStudentImage());
        name.setText(student.getStudentName());
        degree.setText(student.getStudentCareer());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Visualitzar alumne
            }
        });

        return row;
    }
}
