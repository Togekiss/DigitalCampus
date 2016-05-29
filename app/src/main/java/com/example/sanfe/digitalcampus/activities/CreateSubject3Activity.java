package com.example.sanfe.digitalcampus.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.adapters.ThemesListAdapter;
import com.example.sanfe.digitalcampus.logic.dataLoader.SharedPreferencesManager;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.data.Student;
import com.example.sanfe.digitalcampus.logic.data.Subject;

import java.util.ArrayList;

public class CreateSubject3Activity extends AppCompatActivity {
    private ArrayList<String> list;
    private ThemesListAdapter adapter;
//Per temes d'usabilitat considerar sempre mostrar l'ultim afegit
    //Control·lar noms de temes massa llargs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createsubject3);

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        Subject subject = new Subject();

        if (bundle != null) {
            subject = (Subject) bundle.get("SUBJECT2");
        }

        list = new ArrayList<>();

        if (subject != null) {
            if (subject.getSubjectThemes() != null) {
                list = subject.getSubjectThemes();
            }
        }

        ListView listview = (ListView) findViewById(R.id.createsubject3_list);
        final EditText textfield = (EditText) findViewById(R.id.createsubject3_subject);
        Button add = (Button) findViewById(R.id.createsubject3_add);
        Button next = (Button) findViewById(R.id.createsubject3_next);
        Button back = (Button) findViewById(R.id.createsubject3_back);

        back.setText("< Anterior");
        adapter = new ThemesListAdapter(this, list);
        listview.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(textfield.getText().toString());
                textfield.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subject subject = new Subject();
                if (bundle != null) {
                    subject = (Subject) bundle.get("SUBJECT2");
                }
                if (subject != null) {
                    subject.setSubjectThemes(list);
                }
                Intent intent = new Intent (getApplicationContext(), CreateSubject2Activity.class);
                intent.putExtra("SUBJECT1", subject);
                startActivity(intent);
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subject subject = new Subject();

                if (bundle != null) {
                    subject = (Subject) bundle.get("SUBJECT2");
                    subject.setSubjectThemes(list);
                }

                addNewSubjectToSystem(subject);
                Intent intent = new Intent(getApplicationContext(), SubjectManagerActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void onBackPressed() {
        this.startActivity(new Intent(CreateSubject3Activity.this, SubjectManagerActivity.class));
        finish();
    }

    private void addNewSubjectToSystem(Subject subject) {
        Log.d("subject", "let's start");
        subject.setSubjectImage(R.mipmap.app_icon);

        for (Student student: subject.getSubjectStudents()) {
            Log.d("students", student.getStudentName());
            for (Student studentSingleton : Singleton.getInstance().getStudentList()) {
                if (student.getStudentName().equals(studentSingleton.getStudentName())) {
                    int sIndex = Singleton.getInstance().getStudentList().indexOf(studentSingleton);
                    Singleton.getInstance().getStudentList().get(sIndex).addStudentSubject(subject.getSubjectTitle());
                }
            }
        }
        Singleton.getInstance().addSubject(subject);
        SharedPreferencesManager.updateSubjectsJSON();
        SharedPreferencesManager.updateStudentsJSON();
    }
}
