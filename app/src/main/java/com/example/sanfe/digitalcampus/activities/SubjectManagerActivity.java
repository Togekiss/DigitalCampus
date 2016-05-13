package com.example.sanfe.digitalcampus.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.adapters.ListViewAdapter;
import com.example.sanfe.digitalcampus.logic.data.Subject;

import java.util.ArrayList;

public class SubjectManagerActivity extends AppCompatActivity {
    //Implementar el click d'assignatura
    //Mirar si les proporcions actionbar / resta activitat son adequades
    //Afegir appicon amb fletxeta i icon de +

    public static ArrayList<Subject> list;
    public static ListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectmanager);

        list = new ArrayList<>();
        Subject subject1 = new Subject(R.mipmap.app_icon, "Matemáticas", "Impartida por Javier Sevillano, resulta una " +
                "de las asignaturas más complejas en cualquier ingeniería.");
        Subject subject2 = new Subject(R.mipmap.app_icon, "Física", "adeu");

        list.add(subject1);
        list.add(subject2);

        ListView listview = (ListView) findViewById(R.id.subjectmanager_list);
        adapter = new ListViewAdapter(this, list, getResources().getString(R.string.title_elimination), getResources().getString(R.string.text_elimination));
        listview.setAdapter(adapter);

    }

    public static void eliminaAsignatura(int position) {
        list.remove(position);
        adapter.notifyDataSetChanged();
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
        getSupportActionBar().setSubtitle("   " + getResources().getString(R.string.subjectmanager_actionbar));
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.create_subject:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
