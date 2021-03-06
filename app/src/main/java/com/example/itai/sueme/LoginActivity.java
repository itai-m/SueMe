package com.example.itai.sueme;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static User ActiveUser = null;
    public static boolean findUser = false;
    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
    }

    public void onClickRegisterButton(View v) {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }

    public void onClickLoginButton(View v) {
        String email = ((EditText) (findViewById(R.id.emailField))).getText().toString();
        DAL.getUserByEmail(email, new DALCallback() {
            @Override
            public void callback() {
                String pass = ((EditText) (findViewById(R.id.passwordField))).getText().toString();
                if (true) {
                    if (ActiveUser == null) {
                        // Error occured during fetching of the user.
                        Toast.makeText(LoginActivity.this, "Error occured when trying to fetch user.", Toast.LENGTH_LONG).show();
                        spinner.setVisibility(View.GONE);
                    }
                    else {
                        startHomeActivityFromMainThread();
                    }

                }
            }
        });
        spinner.setVisibility(View.VISIBLE);

    }

    public void DEBUG_ONCLICK_CALLBACK(View v)
    {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        startActivity(profileIntent);
    }

    public void startHomeActivityFromMainThread() {

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
