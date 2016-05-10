package com.example.sanfe.digitalcampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.adapters.GridViewImageAdapter;

public class MenuActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Integer [] images = {R.drawable.ic_book_black_48dp, R.drawable.ic_account_box_black_48dp,
                R.drawable.ic_description_black_48dp, R.drawable.ic_cancel_black_48dp};
        String [] descriptions = {"  Gestionar\nAssignatura", " Gestionar Alumnado", " Examenes", "     Salir"};

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new GridViewImageAdapter(this, images, descriptions));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: break;
                    case 1: break;
                    case 2: break;
                    case 3: Intent intent = new Intent (getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                            break;

                    default: break;
                }
            }
        });
    }
}
