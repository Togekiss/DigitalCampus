package com.example.sanfe.digitalcampus.activities.Creators;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.activities.StartApp.LoginActivity;
import com.example.sanfe.digitalcampus.activities.StartApp.MenuActivity;
import com.example.sanfe.digitalcampus.activities.Managers.SubjectManagerActivity;
import com.example.sanfe.digitalcampus.adapters.ThemesListAdapter;
import com.example.sanfe.digitalcampus.logic.dataManager.SharedPreferencesManager;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.data.Student;
import com.example.sanfe.digitalcampus.logic.data.Subject;
import com.example.sanfe.digitalcampus.windows.AlertDialogWindow;

import java.util.ArrayList;

public class CreateSubject3Activity extends AppCompatActivity {
    private ArrayList<String> list;
    private ThemesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createsubject3);

        final Context context = this;
        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        Subject subject = new Subject();

        if (bundle != null) {
            subject = (Subject) bundle.get("SUBJECT2");
        }

        list = new ArrayList<>();

        if (subject.getSubjectThemes() != null) {
            list = subject.getSubjectThemes();
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
                if (textfield.getText().toString().trim().isEmpty()) AlertDialogWindow.errorMessage(context, getResources().getString(R.string.error), "El título esta vacío!");
                else list.add(textfield.getText().toString());
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
                if (list.isEmpty())
                    AlertDialogWindow.errorMessage(context, getResources().getString(R.string.error), "Ha de introducir al menos 1 tema!");
                else {
                    addNewSubjectToSystem(subject);
                    Intent intent = new Intent(getApplicationContext(), SubjectManagerActivity.class);
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
        getSupportActionBar().setSubtitle("   " + getResources().getString(R.string.CreateSubject3));
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
