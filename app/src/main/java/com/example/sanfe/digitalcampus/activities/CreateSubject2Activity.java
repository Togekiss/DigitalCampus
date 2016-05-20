package com.example.sanfe.digitalcampus.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.adapters.ListViewAdapter;
import com.example.sanfe.digitalcampus.logic.data.Student;

import java.util.ArrayList;

public class CreateSubject2Activity extends AppCompatActivity {
//Canviar text botó de back a < Anterior
    public static ArrayList<Student> list;
    public static ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createsubject2);

        list = new ArrayList<>();
        Student student1 = new Student(R.mipmap.app_icon, "Marta");
        Student student2 = new Student(R.mipmap.app_icon, "Mònica");

        list.add(student1);
        list.add(student2);

        ListView listview = (ListView) findViewById(R.id.createsubject2_list);
        //adapter = new ListViewAdapter(this, list, getResources().getString(R.string.title_elimination), getResources().getString(R.string.text_elimination));
        //listview.setAdapter(adapter);
    }
}
