package com.example.itai.sueme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

public class LoginActivity extends AppCompatActivity {
    public static User ActiveUser = null;
    public static boolean findUser = false;
    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spinner = (ProgressBar)findViewById(R.id.progressBar);
    }

    public void onClickRegisterButton(View v) {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }

    public void onClickLoginButton(View v) {
        String email = ((EditText) (findViewById(R.id.emailField))).getText().toString();
        DAL.getUserByEmail(email);
        waitForUserInfo();
        checkUserPass(ActiveUser);
    }

    private void waitForUserInfo(){
        spinner.setVisibility(View.VISIBLE);
        try {
            while (!findUser) {
                wait(500);
            }
        }catch (Exception e){
            Log.d("Erorr", e.getMessage());
        }
        findUser = false;
        spinner.setVisibility(View.GONE);
    }

    private void checkUserPass(User user){
        String pass = ((EditText) (findViewById(R.id.passwordField))).getText().toString();
        if (true) {///TODO: need to add password Check
            Intent homeIntent = new Intent(this, HomeActivity.class);
            startActivity(homeIntent);
        }
    }

    public void DEBUG_ONCLICK_CALLBACK(View v)
    {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        startActivity(profileIntent);
    }

}
