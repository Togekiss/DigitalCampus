package com.example.sanfe.digitalcampus.activities;

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
import android.widget.TextView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.adapters.ExamListAdapter;
import com.example.sanfe.digitalcampus.logic.data.Exam;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import java.util.ArrayList;


public class ExamManagerActivity extends AppCompatActivity {
    public static ArrayList<Exam> list;
    public static ExamListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exammanager);

        list = new ArrayList<>();

        list = Singleton.getInstance().getExamList();
        sort();
        Singleton.getInstance().setExamList(list);

        ListView listview = (ListView) findViewById(R.id.examlist);

        adapter = new ExamListAdapter(this, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle extras = new Bundle();
                Intent intent = new Intent (getApplicationContext(), EditExamActivity.class);
                extras.putSerializable("EXAM", list.get(position));
                extras.putInt("POSITION", position);
                intent.putExtras(extras);
                startActivity(intent);
                finish();
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
        getSupportActionBar().setSubtitle("   " + getResources().getString(R.string.exammanager_actionbar));
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.create_subject:
                Intent intent = new Intent(getApplicationContext(), CreateExamActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sort() {
        int size = list.size();
        Exam aux;

        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j > i; j--) {
                if (list.get(j).getExamDate().getTime() < list.get(j-1).getExamDate().getTime()) {
                    aux = list.get(j);
                    list.set(j, list.get(j-1));
                    list.set(j-1, aux);
                }
            }
        }
    }

    public void onBackPressed() {
        finish();
    }
}
