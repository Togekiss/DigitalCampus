package com.example.sanfe.digitalcampus.activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.logic.data.Student;
import com.example.sanfe.digitalcampus.logic.data.Subject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowStudentActivity extends AppCompatActivity {
//Visualizar imagen, control de null
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showstudent);

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();

        Student student = new Student();

        if (bundle != null) {
            student = (Student) bundle.get("STUDENT");
        }

        ImageView image = (ImageView) findViewById(R.id.showstudent_image);
        TextView name = (TextView) findViewById(R.id.showstudent_name);
        TextView degree = (TextView) findViewById(R.id.showstudent_class);
        TextView birth = (TextView) findViewById(R.id.showstudent_date);
        TextView gender = (TextView) findViewById(R.id.showstudent_gender);
        TextView subjects = (TextView) findViewById(R.id.showstudent_list);
        String subject = new String();

        if (student.getStudentImage().equals("")) image.setImageResource(R.mipmap.app_icon);
        else {
            image.setImageBitmap(BitmapFactory.decodeFile(student.getStudentImage()));
        }
        name.setText(student.getStudentName());
        degree.setText(student.getStudentCareer());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        birth.setText(df.format(student.getStudentBirthdate()));
        gender.setText(student.getStudentGender());

        for (String a : student.getStudentSubjects()) {
            subject += a + "\n";
        }

        subjects.setText(subject);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.subjectmanager_actionbar, menu);

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);
        Bitmap new_icon = MenuActivity.resizeBitmapImageFn(icon, 72);
        Drawable d = new BitmapDrawable(getResources(), new_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(d);

        getSupportActionBar().setTitle("  " + getResources().getString(R.string.app_name));
        getSupportActionBar().setSubtitle("   " + getResources().getString(R.string.ShowStudent));
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
        this.startActivity(new Intent(ShowStudentActivity.this, StudentManagerActivity.class));
        finish();
    }
}
