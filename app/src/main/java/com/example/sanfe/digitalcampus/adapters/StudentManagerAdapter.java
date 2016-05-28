package com.example.sanfe.digitalcampus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.logic.data.Student;
import com.example.sanfe.digitalcampus.windows.AlertDialogWindow;

import java.util.ArrayList;

public class StudentManagerAdapter extends BaseAdapter {

    public String title_elimination;
    public String text_elimination;
    private Context context;
    private ArrayList<Student> list;

    public StudentManagerAdapter (Context context, ArrayList<Student> list, String title_elimination, String text_elimination) {
        this.context = context;
        this.list = list;
        this.title_elimination = title_elimination;
        this.text_elimination = text_elimination;
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
            row = inflater.inflate(R.layout.studentmanagerlist_row, parent, false);
        }

        Student student = (Student) getItem(position);
        row.setTag(student);

        ImageView image = (ImageView) row.findViewById(R.id.studentmanager_image);
        TextView name = (TextView) row.findViewById(R.id.studentmanager_name);
        TextView age = (TextView) row.findViewById(R.id.studentmanager_age);
        TextView degree = (TextView) row.findViewById(R.id.studentmanager_class);
        Button button = (Button) row.findViewById(R.id.studentmanager_button);

        image.setImageResource(R.mipmap.app_icon);
        name.setText(student.getStudentName());
        age.setText("Edad: " + String.valueOf(student.getStudentAge()));
        degree.setText("Especialidad: " + student.getStudentCareer());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogWindow.confirmationMessage2(context, title_elimination, text_elimination, position);
            }
        });

        return row;
    }
}
