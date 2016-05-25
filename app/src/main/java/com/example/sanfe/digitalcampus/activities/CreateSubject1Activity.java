package com.example.sanfe.digitalcampus.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sanfe.digitalcampus.R;

public class CreateSubject1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createsubject1);

        EditText title = (EditText) findViewById(R.id.createsubject1_titlefield);
        EditText description = (EditText) findViewById(R.id.createsubject1_descriptionfield);
        Button continue_button = (Button) findViewById(R.id.createsubject1_nextbutton);
        //Falta ActionBar i Hints
       // Singleton title.getText().toString();
       // Singleton description.getText().toString();

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fer que passi a la seguent activitat
                //En la seguent pantalla ha de poder tornar a aquesta pero amb un boto de back especial
                Intent intent = new Intent (getApplicationContext(), CreateSubject2Activity.class);
                startActivity(intent);
                // finish();
            }
        });
    }
}
