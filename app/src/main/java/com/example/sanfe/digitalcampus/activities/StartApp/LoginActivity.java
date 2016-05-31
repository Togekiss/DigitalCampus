package com.example.sanfe.digitalcampus.activities.StartApp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.logic.dataManager.SharedPreferencesManager;
import com.example.sanfe.digitalcampus.windows.AlertDialogWindow;


public class LoginActivity  extends AppCompatActivity {

    public static final String EMAIL = "administrador@salleurl.edu";
    public static final String PASSWORD = "123qwe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String TITLE = getResources().getString(R.string.error);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email = (EditText) findViewById(R.id.field_email);
        final EditText password = (EditText) findViewById(R.id.field_password);
        final CheckBox remember_me = (CheckBox) findViewById(R.id.checkbox_remember_me);
        Button enter_button = (Button) findViewById(R.id.enter_button);
        final Context context = this;

        enter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isEmailValid(email.getText().toString())) {
                    AlertDialogWindow.errorMessage(context, TITLE, getResources().getString(R.string.badEmailFormat));
                }
                else {
                    if (password.getText().toString().length() < 6) {
                        AlertDialogWindow.errorMessage(context, TITLE, getResources().getString(R.string.shortPassword));
                    }
                    else {
                        if (!email.getText().toString().equals(EMAIL)) {
                            AlertDialogWindow.errorMessage(context, TITLE, getResources().getString(R.string.unregisteredEmail));
                        }
                        else {
                            if (!password.getText().toString().equals(PASSWORD)) {
                                AlertDialogWindow.errorMessage(context, TITLE, getResources().getString(R.string.incorrectPassword));
                            }
                            else {
                                if (remember_me.isChecked()) {
                                    SharedPreferencesManager.setRememberMe(true);
                                }

                                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                }
            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
