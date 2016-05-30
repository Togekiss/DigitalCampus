package com.example.sanfe.digitalcampus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.logic.data.Exam;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.data.Subject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ExamListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Exam> list;

    public ExamListAdapter (Context context, ArrayList<Exam> list) {
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
        int participants = 0;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.examlist_row, parent, false);
        }

        Exam exam = (Exam) getItem(position);
        row.setTag(exam);

        DateFormat dfTime = new SimpleDateFormat("HH:mm");
        DateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");

        TextView date = (TextView) row.findViewById(R.id.examlist_date);
        TextView time = (TextView) row.findViewById(R.id.examlist_time);
        TextView location = (TextView) row.findViewById(R.id.examlist_location);
        TextView students = (TextView) row.findViewById(R.id.examlist_participants);
        TextView degree = (TextView) row.findViewById(R.id.examlist_class);

        date.setText(dfDate.format(exam.getExamDate()));
        time.setText("Hora: " + dfTime.format(exam.getExamDate()));
        location.setText(exam.getExamClassroom());

        try {
            for (Subject subjectSingleton : Singleton.getInstance().getSubjectList()) {
                if (exam.getExamSubject().equals(subjectSingleton.getSubjectTitle())) {
                    int sIndex = Singleton.getInstance().getSubjectList().indexOf(subjectSingleton);
                    participants = Singleton.getInstance().getSubjectList().get(sIndex).getSubjectStudents().size();
                }
            }
        }catch (Exception e) {}

        students.setText(participants + " alumnos");
        degree.setText("Asignatura: " + exam.getExamSubject());

        return row;
    }
}
