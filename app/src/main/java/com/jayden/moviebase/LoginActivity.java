package com.jayden.moviebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jayden on 10-Jun-16.
 */
public class LoginActivity extends AppCompatActivity implements LoginSetHolder {

    private static String url = "https://moviebaseapi.herokuapp.com/api/authenticate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        //get email, password fields, soft keyboard and login button
        Button loginButton = (Button) findViewById(R.id.login_button);
        final EditText emailText = (EditText) findViewById(R.id.email_text);
        final EditText passwordText = (EditText) findViewById(R.id.password_text);
        final InputMethodManager softkeyboard = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);

        if (loginButton != null) {
            loginButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    //when login is pressed, close keyboard
                    emailText.clearFocus();
                    passwordText.clearFocus();
                    softkeyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    //login
                    login(emailText.getText().toString(), passwordText.getText().toString());
                }
            });
        }

        //if you press go on the keyboard when you have the password field selected
        if (passwordText != null) {
            passwordText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    //if it pressed do what the login button does
                    if (actionId == EditorInfo.IME_ACTION_GO) {

                        //when login is pressed, close keyboard
                        softkeyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        //login
                        login(emailText.getText().toString(), passwordText.getText().toString());
                    }
                    return true;
                }
            });
        }
    }

    private void login(String email, String password) {
        UserTasker tasker = new UserTasker(this, "LOGIN", email, password);
        tasker.execute(url);
    }

    @Override
    public void getLoginFinished(User user) {
        //if user exists and was authenticated login to main app
        if (user != null) {
            if (user.getEmail() != "failed") {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                //set some details
                intent.putExtra(MainActivity.USER_USERNAME_EXTRA, user.getUsername());
                intent.putExtra(MainActivity.USER_EMAIL_EXTRA, user.getEmail());
                intent.putExtra(MainActivity.USER_ID_EXTRA, user.getUserId());
                intent.putExtra(MainActivity.USER_TOKEN_EXTRA, user.getToken());
                //go to activity
                startActivity(intent);
            } else {
                Toast.makeText(this, user.getUsername(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Failed to login!", Toast.LENGTH_SHORT).show();
        }
    }
}
