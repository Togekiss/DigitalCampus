package com.example.sanfe.digitalcampus.activities;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.logic.data.Student;

import java.util.Calendar;

public class CreateStudentActivity extends AppCompatActivity {
    private int year;
    private int month;
    private int day;
    private EditText date;
    static final int DATE_PICKER_ID = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createstudent);

        final Calendar calendar = Calendar.getInstance();

        EditText name = (EditText) findViewById(R.id.createstudent_name);
        date = (EditText) findViewById(R.id.createstudent_birth);
        Spinner spinner = (Spinner) findViewById(R.id.createstudent_spinner);
        RadioButton male = (RadioButton) findViewById(R.id.createstudent_male);
        RadioButton female = (RadioButton) findViewById(R.id.createstudent_female);
        ImageView image = (ImageView) findViewById(R.id.createstudent_image);
        Button button = (Button) findViewById(R.id.createstudent_button);
        Button create_button = (Button) findViewById(R.id.createstudent_create_button);


        year  = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day   = calendar.get(Calendar.DAY_OF_MONTH);

        date.setText(new StringBuilder()
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_PICKER_ID);
            }
        });
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Student student = new Student(date.getText().toString(), );
                /*Intent intent = new Intent (getApplicationContext(), CreateSubject3Activity.class);
                startActivity(intent);
                finish(); */
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                return new DatePickerDialog(this, pickerListener, year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;

            date.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };
}
