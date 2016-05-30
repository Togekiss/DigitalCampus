package com.example.sanfe.digitalcampus.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.adapters.ShowSubjectStudentListAdapter;
import com.example.sanfe.digitalcampus.logic.data.Student;
import com.example.sanfe.digitalcampus.logic.data.Subject;

import java.util.ArrayList;

public class ShowSubjectActivity extends AppCompatActivity {
    private ShowSubjectStudentListAdapter adapter;
    private ArrayList<Student> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showsubject);

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        Subject subject = new Subject();
        String themes_text = new String();
        list = new ArrayList<>();
        int index = 1;

        if (bundle != null) {
            subject = (Subject) bundle.get("SUBJECT");
        }

        TextView name = (TextView) findViewById(R.id.showsubject_name);
        TextView description = (TextView) findViewById(R.id.showsubject_description);
        TextView themes = (TextView) findViewById(R.id.showsubject_themes);
        ListView listview = (ListView) findViewById(R.id.showsubject_list);

        if (subject != null) {
            name.setText(subject.getSubjectTitle());
            description.setText(subject.getSubjectDescription());

            for (String a : subject.getSubjectThemes()) {
                themes_text += index + ". " + a + "\n";
                index++;
            }

            themes.setText(themes_text);
            list = subject.getSubjectStudents();
            adapter = new ShowSubjectStudentListAdapter(this, list);
            listview.setAdapter(adapter);
        }
    }

    public void onBackPressed() {
        this.startActivity(new Intent(ShowSubjectActivity.this, SubjectManagerActivity.class));
        finish();
    }
}
