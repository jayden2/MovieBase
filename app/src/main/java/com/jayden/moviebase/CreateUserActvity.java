package com.jayden.moviebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Jayden on 11-Jun-16.
 */
public class CreateUserActvity extends AppCompatActivity implements LoginSetHolder {

    EditText username;
    EditText email;
    EditText password;
    String urlCreate = "https://moviebaseapi.herokuapp.com/api/users/";
    String urlVerify = "https://moviebaseapi.herokuapp.com/api/users/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Create User");
        setContentView(R.layout.activity_user_add);

        //set up buttons, soft keyboard and edit text fields
        Button createUserButton = (Button) findViewById(R.id.create_user_bttn);
        Button cancelUserButton = (Button) findViewById(R.id.cancel_creation_bttn);
        final InputMethodManager softkeyboard = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        username = (EditText) findViewById(R.id.username_text);
        email = (EditText) findViewById(R.id.email_text);
        password = (EditText) findViewById(R.id.password_text);

        //create user button
        if (createUserButton != null) {
            createUserButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    //close keyboard
                    closeKeyboard(softkeyboard, view);
                    //when create user button is pressed
                    checkValidUser();
                }
            });
        }

        //cancel user button
        if (cancelUserButton != null) {
            cancelUserButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    closeKeyboard(softkeyboard, view);
                    finish();
                }
            });
        }
    }
    private void closeKeyboard(InputMethodManager softkeyboard, View view) {
        //close the keyboard
        softkeyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void checkValidUser() {
        if (username.getText().toString() == "") {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.getText().toString() == "") {
            Toast.makeText(this, "Please enter an email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.getText().toString() == "") {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        //check if email exists
        UserTasker verifyTasker = new UserTasker(this, "VERIFY", "", email.getText().toString(), "");
        verifyTasker.execute(urlVerify + email.getText().toString() + "/check/");

    }

    private void createUser() {
        //create user
        UserTasker createTasker = new UserTasker(this, "SIGNUP", username.getText().toString(), email.getText().toString(), password.getText().toString());
        createTasker.execute(urlCreate);
    }

    @Override
    public void getLoginFinished(User user) {
        if (user != null) {
            //failed failed to create user because of existing email
            if (user.getUsername() == "email_exists") {
                Toast.makeText(this, "Email already exists, please change your email and try again", Toast.LENGTH_SHORT).show();
                return;
            } else if (user.getUsername() == "verified") {
                //if email is ok create user!
                Log.d("VERIFIED", "CREATING USER");
                createUser();
            }
            //failed to create user
            if (user.getUsername() == "failed_create") {
                Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();
                return;
            }
            //if user created tell user and then go back to login
            Toast.makeText(this, "User created!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            //if uesr recieced is null
            Toast.makeText(this, "Something went wrong, user not created. Please check your connection!", Toast.LENGTH_SHORT).show();
        }
    }
}
