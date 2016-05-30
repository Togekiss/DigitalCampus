package com.example.sanfe.digitalcampus.activities;


import android.app.DatePickerDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.logic.dataLoader.SharedPreferencesManager;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.data.Student;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

//Controlar data i fer el select foto
//Vigilar la date
public class CreateStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createstudent);

        final EditText name = (EditText) findViewById(R.id.createstudent_name);
        final TextView date = (TextView) findViewById(R.id.createstudent_birth);
        final Spinner spinner = (Spinner) findViewById(R.id.createstudent_spinner);
        final RadioButton male = (RadioButton) findViewById(R.id.createstudent_male);
        final RadioButton female = (RadioButton) findViewById(R.id.createstudent_female);
        final ImageView image = (ImageView) findViewById(R.id.createstudent_image);
        final Button button = (Button) findViewById(R.id.createstudent_button);
        final Button create_button = (Button) findViewById(R.id.createstudent_create_button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.careers_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateStudentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });



        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Student student = new Student();
                if (male.isChecked()) {
                    try {
                        student = new Student(df.parse(date.getText().toString()), spinner.getSelectedItem().toString(),
                        "Hombre", R.mipmap.app_icon, name.getText().toString(), new ArrayList<String>());
                        Singleton.getInstance().addStudent(student);
                        SharedPreferencesManager.updateStudentsJSON();
                        Intent intent = new Intent (getApplicationContext(), ShowStudentActivity.class);
                        intent.putExtra("STUDENT", student);
                        startActivity(intent);
                        finish();
                    } catch (ParseException e) {}
                }
                else if (female.isChecked()){
                    try {
                        student = new Student(df.parse(date.getText().toString()), spinner.getSelectedItem().toString(),
                                "Mujer", R.mipmap.app_icon, name.getText().toString(), new ArrayList<String>());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Singleton.getInstance().addStudent(student);
                    SharedPreferencesManager.updateStudentsJSON();
                    Intent intent = new Intent (getApplicationContext(), ShowStudentActivity.class);
                    intent.putExtra("STUDENT", student);
                    startActivity(intent);
                    finish();
                }
            }
        });
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
        getSupportActionBar().setSubtitle("   " + getResources().getString(R.string.CreateStudent));
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
        this.startActivity(new Intent(CreateStudentActivity.this, StudentManagerActivity.class));
        finish();
    }
}
