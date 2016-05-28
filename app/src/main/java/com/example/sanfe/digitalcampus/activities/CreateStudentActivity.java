package com.example.sanfe.digitalcampus.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.sanfe.digitalcampus.R;

public class CreateStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createstudent);

        EditText name = (EditText) findViewById(R.id.createstudent_name);
        EditText date = (EditText) findViewById(R.id.createstudent_birth);
        Spinner spinner = (Spinner) findViewById(R.id.createstudent_spinner);
        RadioButton male = (RadioButton) findViewById(R.id.createstudent_male);
        RadioButton female = (RadioButton) findViewById(R.id.createstudent_female);
        ImageView image = (ImageView) findViewById(R.id.createstudent_image);
        Button button = (Button) findViewById(R.id.createstudent_button);
        Button create_button = (Button) findViewById(R.id.createstudent_create_button);

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent (getApplicationContext(), CreateSubject3Activity.class);
                startActivity(intent);
                finish(); */
            }
        });
    }
}
