package com.example.sanfe.digitalcampus.activities.Managers;

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
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.activities.Creators.CreateSubject1Activity;
import com.example.sanfe.digitalcampus.activities.StartApp.MenuActivity;
import com.example.sanfe.digitalcampus.activities.Show.ShowSubjectActivity;
import com.example.sanfe.digitalcampus.adapters.ListViewAdapter;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.data.Student;
import com.example.sanfe.digitalcampus.logic.data.Subject;
import com.example.sanfe.digitalcampus.logic.dataLoader.SharedPreferencesManager;

import java.util.ArrayList;

public class SubjectManagerActivity extends AppCompatActivity {
    //Mirar si les proporcions actionbar / resta activitat son adequades
    //Afegir appicon amb fletxeta i icon de +
   //Canviar el JSON per a que apareguin imatges de les assignatures
    //Canviar el show layout per que es faci mes ampli
    //Fer que el text quedi justificat
    //Borrar Student de la assignatura al borrar un student
    public static ArrayList<Subject> list;
    public static ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectmanager);
        list = new ArrayList<>();

        list = Singleton.getInstance().getSubjectList();

        ListView listview = (ListView) findViewById(R.id.subjectmanager_list);

        adapter = new ListViewAdapter(this, list, getResources().getString(R.string.title_elimination), getResources().getString(R.string.text_elimination));
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle extras = new Bundle();
                Intent intent = new Intent(getApplicationContext(), ShowSubjectActivity.class);
                extras.putSerializable("SUBJECT", list.get(position));
                extras.putInt("POSITION", position);
                intent.putExtras(extras);
                startActivity(intent);
                finish();
            }
        });
    }

    public static void eliminaAsignatura(int position) {

        for (Student student: list.get(position).getSubjectStudents()) {
            for (Student studentSingleton : Singleton.getInstance().getStudentList()) {
                if (student.getStudentName().equals(studentSingleton.getStudentName())) {
                    int sIndex = Singleton.getInstance().getStudentList().indexOf(studentSingleton);
                    Singleton.getInstance().getStudentList().get(sIndex).removeStudentSubject(list.get(position).getSubjectTitle());
                }
            }
        }

        int sIndex = 0;
        boolean found = false;

        while (sIndex < Singleton.getInstance().getExamList().size() && !found) {
            if (list.get(position).getSubjectTitle().equals(Singleton.getInstance().getExamList().get(sIndex).getExamSubject())) {
                Singleton.getInstance().getExamList().remove(sIndex);
                found = true;
            }
            sIndex++;
        }

        Singleton.getInstance().removeSubject(list.get(position));
        SharedPreferencesManager.updateSubjectsJSON();
        SharedPreferencesManager.updateStudentsJSON();
        SharedPreferencesManager.updateExamsJSON();

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
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.create_subject:
                Intent intent = new Intent(getApplicationContext(), CreateSubject1Activity.class);
                startActivity(intent);
                finish();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        finish();
    }
}
