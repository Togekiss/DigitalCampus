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
import com.example.sanfe.digitalcampus.adapters.ListViewAdapter;
import com.example.sanfe.digitalcampus.adapters.ThemesListAdapter;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.data.Student;
import com.example.sanfe.digitalcampus.logic.data.Subject;
import com.example.sanfe.digitalcampus.logic.json.GsonManager;

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

        list = new ArrayList<>();

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
        Log.i("subject", "let's start");
        subject.setSubjectImage(R.mipmap.app_icon);
        if (subject.getSubjectStudents().isEmpty()) {
            Log.i("subject", "empty list");
        }
        for (Student student: subject.getSubjectStudents()) {
            Log.i("student", student.getStudentName());
            int sIndex = Singleton.getInstance().getStudentList().indexOf(student);
            Log.i("student", sIndex + "");
            //Log.i("student", "student found: " + Singleton.getInstance().getStudentList().get(sIndex).getStudentName());
           // Singleton.getInstance().getStudentList().get(sIndex).addStudentSubject(subject.getSubjectTitle());
        }

        Singleton.getInstance().addSubject(subject);
    }
}
