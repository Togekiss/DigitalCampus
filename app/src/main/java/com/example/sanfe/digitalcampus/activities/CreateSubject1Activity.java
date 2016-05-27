package com.example.sanfe.digitalcampus.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.data.Subject;

public class CreateSubject1Activity extends AppCompatActivity {
//Controlar que 2 assignatures no es diguin igual
//Falta ActionBar i Hints
    private Subject subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createsubject1);

        final EditText title = (EditText) findViewById(R.id.createsubject1_titlefield);
        final EditText description = (EditText) findViewById(R.id.createsubject1_descriptionfield);
        Button continue_button = (Button) findViewById(R.id.createsubject1_nextbutton);

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fer que passi a la seguent activitat
                //En la seguent pantalla ha de poder tornar a aquesta pero amb un boto de back especial
                subject = new Subject (title.getText().toString(), description.getText().toString());
                Intent intent = new Intent (getApplicationContext(), CreateSubject2Activity.class);
                startActivity(intent);
                // finish();
            }
        });
    }
}
