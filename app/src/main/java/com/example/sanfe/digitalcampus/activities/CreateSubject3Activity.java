package com.example.sanfe.digitalcampus.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.adapters.ListViewAdapter;
import com.example.sanfe.digitalcampus.logic.data.Singleton;

import java.util.ArrayList;

public class CreateSubject3Activity extends AppCompatActivity {
    public static ArrayList<String> list;
    public static ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createsubject3);

        list = new ArrayList<>();
        String tema1 = "Introducción";
        String tema2 = "Distribuciones discretas";

        list.add(tema1);
        list.add(tema2);


        ListView listview = (ListView) findViewById(R.id.createsubject3_list);
        final EditText textfield = (EditText) findViewById(R.id.createsubject3_subject);
        Button add = (Button) findViewById(R.id.createsubject3_add);
        Button next = (Button) findViewById(R.id.createsubject3_next);
        Button back = (Button) findViewById(R.id.createsubject3_back);

        back.setText("< Anterior");
        //adapter = new ListViewAdapter(this, list, getResources().getString(R.string.title_elimination), getResources().getString(R.string.text_elimination));
        //listview.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(textfield.getText().toString());
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent (getApplicationContext(), CreateSubject3Activity.class);
                // startActivity(intent);
                // finish();
                //Afegir list a la classe subject
               // Singleton.getInstance().addSubject(subject);
            }
        });

    }
}
