package com.example.itai.sueme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickRegisterButton(View v) {
        DAL dal = new DAL();
        dal.addUser(new User(0, "name", "email", "phonenumber", "location", false));
        /*Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);*/
    }
}
