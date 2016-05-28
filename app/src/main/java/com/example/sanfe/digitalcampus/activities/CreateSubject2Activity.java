package com.example.sanfe.digitalcampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.adapters.ListViewAdapter;
import com.example.sanfe.digitalcampus.adapters.StudentListAdapter;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.data.Student;
import com.example.sanfe.digitalcampus.logic.data.Subject;

import java.util.ArrayList;

public class CreateSubject2Activity extends AppCompatActivity {
    private StudentListAdapter adapter;
    private ArrayList<Student> list;
    public static boolean[] checkboxlist;
    public static Subject subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createsubject2);

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();

        ListView listview = (ListView) findViewById(R.id.createsubject2_list);
        Button next = (Button) findViewById(R.id.createsubject2_next);
        Button back = (Button) findViewById(R.id.createsubject2_back);

        list = Singleton.getInstance().getStudentList();
        checkboxlist = new boolean[list.size()];

        subject = new Subject();

        if (bundle != null) {
            subject = (Subject) bundle.get("SUBJECT1");
        }

        back.setText("< Anterior");
        adapter = new StudentListAdapter(getApplicationContext(), list);
        listview.setAdapter(adapter);
        Log.d("students", "adapter has been set");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subject subject = new Subject();
                if (bundle != null) {
                    subject = (Subject) bundle.get("SUBJECT1");
                }
                Intent intent = new Intent (getApplicationContext(), CreateSubject1Activity.class);
                intent.putExtra("SUBJECT2", subject);
                startActivity(intent);
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subject subject = new Subject();
                ArrayList<Student> students = new ArrayList<>();
                Log.d("students", "starting to check checkboxes");
                Log.d("students", checkboxlist.length + "");
                for (int i = 0; i < checkboxlist.length; i++) {
                    Log.d("students", "student: " + list.get(i).getStudentName());
                    if (checkboxlist[i]) {
                        students.add(list.get(i));
                        Log.d("students", "is selected");
                    }
                }

                if (bundle != null) {
                    subject = (Subject) bundle.get("SUBJECT1");
                   try {
                       subject.setSubjectStudents(students);
                   }catch (Exception e){}
                }
                Intent intent = new Intent (getApplicationContext(), CreateSubject3Activity.class);
                intent.putExtra("SUBJECT2", subject);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed() {
        this.startActivity(new Intent(CreateSubject2Activity.this, SubjectManagerActivity.class));
        finish();
    }
}
