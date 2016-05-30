package com.example.sanfe.digitalcampus.activities;


import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.logic.dataLoader.SharedPreferencesManager;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.data.Student;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

//Controlar data i fer el select foto
//Vigilar la date
public class CreateStudentActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private String imgDecodableString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createstudent);

        final EditText name = (EditText) findViewById(R.id.createstudent_name);
        final TextView date = (TextView) findViewById(R.id.createstudent_birth);
        final Spinner spinner = (Spinner) findViewById(R.id.createstudent_spinner);
        final RadioButton male = (RadioButton) findViewById(R.id.createstudent_male);
        final RadioButton female = (RadioButton) findViewById(R.id.createstudent_female);
        final ImageView image = (ImageView) findViewById(R.id.createstudent_image);
        final Button image_button = (Button) findViewById(R.id.createstudent_image_button);
        final Button create_button = (Button) findViewById(R.id.createstudent_create_button);
        imgDecodableString = "";

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.careers_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateStudentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });


        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMG);

                }
                else if (Build.VERSION.SDK_INT >= 23) {
                    requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE_ASK_PERMISSIONS);
                    return;
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(CreateStudentActivity.this).create();
                    alertDialog.setMessage(getResources().getString(R.string.permissionDenied));
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }

            }
        });



        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Student student = new Student();
                if (male.isChecked()) {
                    try {
                        student = new Student(df.parse(date.getText().toString()), spinner.getSelectedItem().toString(),
                        "Hombre", imgDecodableString, name.getText().toString(), new ArrayList<String>());
                        Singleton.getInstance().addStudent(student);
                        SharedPreferencesManager.updateStudentsJSON();
                        Intent intent = new Intent (getApplicationContext(), ShowStudentActivity.class);
                        intent.putExtra("STUDENT", student);
                        startActivity(intent);
                        finish();
                    } catch (ParseException e) {}
                }
                else if (female.isChecked()){
                    try {
                        student = new Student(df.parse(date.getText().toString()), spinner.getSelectedItem().toString(),
                                "Mujer", imgDecodableString, name.getText().toString(), new ArrayList<String>());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Singleton.getInstance().addStudent(student);
                    SharedPreferencesManager.updateStudentsJSON();
                    Intent intent = new Intent (getApplicationContext(), ShowStudentActivity.class);
                    intent.putExtra("STUDENT", student);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
                } else {
                    // Permission Denied
                    Toast.makeText(CreateStudentActivity.this, "Permissions Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                Log.d("image", imgDecodableString);
                cursor.close();
                ImageView im = (ImageView) findViewById(R.id.createstudent_image);

                // Set the Image in ImageView after decoding the String
                im.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));


            } else {
                AlertDialog alertDialog = new AlertDialog.Builder(CreateStudentActivity.this).create();
                alertDialog.setMessage(getResources().getString(R.string.noImageSelected));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        } catch (Exception e) {
            Log.d("error", e.getMessage());
            AlertDialog alertDialog = new AlertDialog.Builder(CreateStudentActivity.this).create();
            alertDialog.setMessage(getResources().getString(R.string.error));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

    }

    public void onBackPressed() {
        this.startActivity(new Intent(CreateStudentActivity.this, StudentManagerActivity.class));
        finish();
    }
}
