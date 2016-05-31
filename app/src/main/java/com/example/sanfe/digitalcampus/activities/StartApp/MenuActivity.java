package com.example.sanfe.digitalcampus.activities.StartApp;

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
import android.widget.GridView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.activities.Creators.CreateExamActivity;
import com.example.sanfe.digitalcampus.activities.Creators.CreateStudentActivity;
import com.example.sanfe.digitalcampus.activities.Creators.CreateSubject1Activity;
import com.example.sanfe.digitalcampus.activities.Managers.ExamManagerActivity;
import com.example.sanfe.digitalcampus.activities.Managers.StudentManagerActivity;
import com.example.sanfe.digitalcampus.activities.Managers.SubjectManagerActivity;
import com.example.sanfe.digitalcampus.adapters.GridViewImageAdapter;
import com.example.sanfe.digitalcampus.logic.dataManager.SharedPreferencesManager;

public class MenuActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Integer [] images = {R.drawable.ic_book_black_48dp, R.drawable.ic_account_box_black_48dp,
                R.drawable.ic_description_black_48dp, R.drawable.ic_cancel_black_48dp};
        String [] descriptions = {getResources().getString(R.string.manageSubjects),
                getResources().getString(R.string.manageStudents), getResources().getString(R.string.exams),
                getResources().getString(R.string.logout)};

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new GridViewImageAdapter(this, images, descriptions));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: Intent intent1 = new Intent(getApplicationContext(), SubjectManagerActivity.class);
                            startActivity(intent1);
                            break;
                    case 1: Intent intent2 = new Intent(getApplicationContext(), StudentManagerActivity.class);
                            startActivity(intent2);
                            break;
                    case 2: Intent intent3 = new Intent(getApplicationContext(), ExamManagerActivity.class);
                            startActivity(intent3);
                            break;
                    case 3:

                        SharedPreferencesManager.setRememberMe(false);

                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;

                    default:
                        break;
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);
        Bitmap new_icon = resizeBitmapImageFn(icon, 72);
        Drawable d = new BitmapDrawable(getResources(), new_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(d);

        getSupportActionBar().setTitle("  " + getResources().getString(R.string.app_name));
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.create_student:
                Intent intent = new Intent(getApplicationContext(), CreateStudentActivity.class);
                startActivity(intent);
                return true;
            case R.id.create_subject:
                Intent intent2 = new Intent(getApplicationContext(), CreateSubject1Activity.class);
                startActivity(intent2);
                return true;
            case R.id.new_exam:
                Intent intent3 = new Intent(getApplicationContext(), CreateExamActivity.class);
                startActivity(intent3);
                return true;
            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public static Bitmap resizeBitmapImageFn(Bitmap bmpSource, int maxResolution){
        int iWidth = bmpSource.getWidth();
        int iHeight = bmpSource.getHeight();
        int newWidth = iWidth ;
        int newHeight = iHeight ;
        float rate;

        if (iWidth > iHeight ) {
            if (maxResolution < iWidth ) {
                rate = maxResolution / (float) iWidth ;
                newHeight = (int) (iHeight * rate);
                newWidth = maxResolution;
            }
        }else {
            if (maxResolution < iHeight ) {
                rate = maxResolution / (float) iHeight ;
                newWidth = (int) (iWidth * rate);
                newHeight = maxResolution;
            }
        }
        return Bitmap.createScaledBitmap(bmpSource, newWidth, newHeight, true);
    }

}
