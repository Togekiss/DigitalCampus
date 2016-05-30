package com.example.sanfe.digitalcampus.activities.Creators;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.activities.Managers.ExamManagerActivity;
import com.example.sanfe.digitalcampus.activities.StartApp.MenuActivity;
import com.example.sanfe.digitalcampus.logic.data.Exam;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.data.Subject;
import com.example.sanfe.digitalcampus.logic.dataManager.SharedPreferencesManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EditExamActivity extends AppCompatActivity {
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createexam);

        list = new ArrayList<>();
        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        DateFormat dfTime = new SimpleDateFormat("HH:mm");
        DateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");
        final Exam exam;

        final TextView date = (TextView) findViewById(R.id.field_date);
        final TextView time = (TextView) findViewById(R.id.field_time);
        final Spinner degree = (Spinner) findViewById(R.id.field_degree);
        final Spinner subject = (Spinner) findViewById(R.id.field_subject);
        final Spinner classroom = (Spinner) findViewById(R.id.field_location);
        Button button = (Button) findViewById(R.id.save_button);

        for (Subject subject1 : Singleton.getInstance().getSubjectList()) {
            list.add(subject1.getSubjectTitle());
        }

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditExamActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(EditExamActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                if (minute < 10) time.setText(hourOfDay + ":0" + minute);
                                else time.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        ArrayAdapter<CharSequence> adapterDegree = ArrayAdapter.createFromResource(this, R.array.careers_array, android.R.layout.simple_spinner_item);
        degree.setAdapter(adapterDegree);

        ArrayAdapter<String> adapterSubject = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        subject.setAdapter(adapterSubject);

        ArrayAdapter<CharSequence> adapterClassroom = ArrayAdapter.createFromResource(this, R.array.classrooms_array, android.R.layout.simple_spinner_item);
        classroom.setAdapter(adapterClassroom);

        if (bundle != null) {
            exam = (Exam) bundle.getSerializable("EXAM");
            date.setText(dfDate.format(exam.getExamDate()));
            time.setText(dfTime.format(exam.getExamDate()));

            String[] myResArray = getResources().getStringArray(R.array.careers_array);
            for (int i = 0; i < myResArray.length; i++) {
                if (myResArray[i].equals(exam.getExamCareer())) degree.setSelection(i);
            }

            myResArray = getResources().getStringArray(R.array.classrooms_array);
            for (int i = 0; i < myResArray.length; i++) {
                if (myResArray[i].equals(exam.getExamClassroom())) classroom.setSelection(i);
            }

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(exam.getExamSubject())) subject.setSelection(i);
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String[] aux = time.getText().toString().split(":");
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(aux[0]));
                    cal.set(Calendar.MINUTE, Integer.parseInt(aux[1]));

                    aux = date.getText().toString().split("/");

                    cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(aux[0]));
                    cal.set(Calendar.MONTH, Integer.parseInt(aux[1]) - 1);
                    cal.set(Calendar.YEAR, Integer.parseInt(aux[2]));

                    Exam exam1 = new Exam(degree.getSelectedItem().toString(), classroom.getSelectedItem().toString(), cal.getTime(), subject.getSelectedItem().toString());

                    if (bundle != null) {
                        int position = (int) bundle.getInt("POSITION");

                        Singleton.getInstance().getExamList().set(position, exam1);
                        SharedPreferencesManager.updateExamsJSON();
                    }

                }catch (Exception e) {}

                Intent intent = new Intent (getApplicationContext(), ExamManagerActivity.class);
                startActivity(intent);
                finish();
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
        getSupportActionBar().setSubtitle("   " + getResources().getString(R.string.EditExam));
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
        this.startActivity(new Intent(EditExamActivity.this, ExamManagerActivity.class));
        finish();
    }
}
