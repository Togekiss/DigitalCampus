package com.example.sanfe.digitalcampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.adapters.ListViewAdapter;
import com.example.sanfe.digitalcampus.logic.data.Student;

import java.util.ArrayList;

public class CreateSubject2Activity extends AppCompatActivity {
    private ListViewAdapter adapter;
    private ArrayList<Student> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createsubject2);

        ListView listview = (ListView) findViewById(R.id.createsubject2_list);
        Button next = (Button) findViewById(R.id.createsubject2_next);
        Button back = (Button) findViewById(R.id.createsubject2_back);
        list = new ArrayList<>();

        back.setText("< Anterior");
        //adapter = new ListViewAdapter(this,  Singleton.getInstance().getStudentList(), getResources().getString(R.string.title_elimination), getResources().getString(R.string.text_elimination));
        //listview.setAdapter(adapter);

        listview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Afegir o desafegir a la list els alumnes
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
            }
        });
    }
}
