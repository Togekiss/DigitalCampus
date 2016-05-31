package com.example.sanfe.digitalcampus.activities.Creators;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.activities.StartApp.LoginActivity;
import com.example.sanfe.digitalcampus.activities.StartApp.MenuActivity;
import com.example.sanfe.digitalcampus.activities.Managers.SubjectManagerActivity;
import com.example.sanfe.digitalcampus.adapters.StudentListAdapter;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.data.Student;
import com.example.sanfe.digitalcampus.logic.data.Subject;
import com.example.sanfe.digitalcampus.windows.AlertDialogWindow;

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

        final Context context = this;
        subject = new Subject();

        if (bundle != null) {
            subject = (Subject) bundle.get("SUBJECT1");
        }

        back.setText("< Anterior");
        adapter = new StudentListAdapter(this, list);
        listview.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subject subject = new Subject();
                ArrayList<Student> students = new ArrayList<>();
                for (int i = 0; i < checkboxlist.length; i++) {
                    if (checkboxlist[i]) {
                        students.add(list.get(i));
                    }
                }

                if (bundle != null) {
                    subject = (Subject) bundle.get("SUBJECT1");
                    try {
                        subject.setSubjectStudents(students);
                    }catch (Exception e){}
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
                for (int i = 0; i < checkboxlist.length; i++) {
                    if (checkboxlist[i]) {
                        students.add(list.get(i));
                    }
                }

                if (bundle != null) {
                    subject = (Subject) bundle.get("SUBJECT1");
                   try {
                       subject.setSubjectStudents(students);
                   }catch (Exception e){}
                }
                if (students.isEmpty())  AlertDialogWindow.errorMessage(context, getResources().getString(R.string.error), "Selecciona al menos un alumno!");
                else {
                    Intent intent = new Intent(getApplicationContext(), CreateSubject3Activity.class);
                    intent.putExtra("SUBJECT2", subject);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sample_actionbar, menu);

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);
        Bitmap new_icon = MenuActivity.resizeBitmapImageFn(icon, 72);
        Drawable d = new BitmapDrawable(getResources(), new_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(d);
        getSupportActionBar().setTitle("  " + getResources().getString(R.string.app_name));
        getSupportActionBar().setSubtitle("   " + getResources().getString(R.string.CreateSubject2));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        this.startActivity(new Intent(CreateSubject2Activity.this, SubjectManagerActivity.class));
        finish();
    }
}
