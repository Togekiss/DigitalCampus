package com.example.sanfe.digitalcampus.activities.Show;


import android.app.Activity;
import android.content.Context;
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
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.activities.Managers.SubjectManagerActivity;
import com.example.sanfe.digitalcampus.activities.StartApp.MenuActivity;
import com.example.sanfe.digitalcampus.adapters.ShowSubjectStudentListAdapter;
import com.example.sanfe.digitalcampus.logic.data.Student;
import com.example.sanfe.digitalcampus.logic.data.Subject;
import com.example.sanfe.digitalcampus.windows.AlertDialogWindow;

import java.util.ArrayList;

public class ShowSubjectActivity extends AppCompatActivity {
    private ShowSubjectStudentListAdapter adapter;
    private ArrayList<Student> list;
    private int position;
    private Context context;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showsubject);

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        Subject subject = new Subject();
        String themes_text = new String();
        list = new ArrayList<>();
        int index = 1;
        context = this;
        activity = this;


        if (bundle != null) {
            subject = (Subject) bundle.getSerializable("SUBJECT");
            position = bundle.getInt("POSITION");
        }

        TextView name = (TextView) findViewById(R.id.showsubject_name);
        TextView description = (TextView) findViewById(R.id.showsubject_description);
        TextView themes = (TextView) findViewById(R.id.showsubject_themes);
        ListView listview = (ListView) findViewById(R.id.showsubject_list);

        if (subject != null) {
            name.setText(subject.getSubjectTitle());
            description.setText(subject.getSubjectDescription());

            for (String a : subject.getSubjectThemes()) {
                themes_text += index + ". " + a + "\n";
                index++;
            }

            themes.setText(themes_text);
            list = subject.getSubjectStudents();
            adapter = new ShowSubjectStudentListAdapter(this, list);
            listview.setAdapter(adapter);
            setListViewHeightBasedOnItems(listview);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.showsubject_actionbar, menu);

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);
        Bitmap new_icon = MenuActivity.resizeBitmapImageFn(icon, 72);
        Drawable d = new BitmapDrawable(getResources(), new_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(d);
        getSupportActionBar().setTitle("  " + getResources().getString(R.string.app_name));
        getSupportActionBar().setSubtitle("   " + getResources().getString(R.string.CreateSubject4));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.delete_subject:
                AlertDialogWindow.confirmationMessage3(activity, context, getResources().getString(R.string.title_elimination),
                        getResources().getString(R.string.text_elimination), position);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        this.startActivity(new Intent(ShowSubjectActivity.this, SubjectManagerActivity.class));
        finish();
    }

    private static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
}
