package com.example.itai.sueme;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class RegisterActivity extends AppCompatActivity {
    boolean location_disabled = false;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        spinner = (ProgressBar)findViewById(R.id.registerProgressBar);
        spinner.setVisibility(View.GONE);
    }

    public void onRegisterSubmitClick(View v) {
        // Aggregate data from all the fields.
        // If nothing has been entered, string is "".
        String displayName = ((EditText) (findViewById(R.id.DisplayNameText))).getText().toString();
        String email = ((EditText) (findViewById(R.id.EmailText))).getText().toString();
        String password = ((EditText) (findViewById(R.id.PasswordText))).getText().toString();
        String rePassword = ((EditText) (findViewById(R.id.RePasswordText))).getText().toString();
        ToggleButton tg = (ToggleButton) findViewById(R.id.toggleButton);
        // If toggle button on = lawyer, else it's a regular user.
        String userType = tg.getText().toString();
        String currLocation = ((TextView) (findViewById(R.id.CurrentLocationTextview))).getText().toString();
        String phoneNumber = ((EditText) (findViewById(R.id.PhoneNumberText))).getText().toString();
        boolean isLawyer = (userType.compareTo("Lawyer") == 0) ? true : false;
        User userToRegister = new User(0, displayName, email, phoneNumber, currLocation, isLawyer);
        LoginActivity.ActiveUser = userToRegister;
        DAL.addUser(userToRegister, new DALCallback() {
            @Override
            public void callback() {
                startActivityFromMainThread();
            }
        });
        spinner.setVisibility(View.VISIBLE);
    }

    public void startActivityFromMainThread() {

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }


    public void onGetLocationClick(View v) {
        if (location_disabled == true)
        {
            ((TextView) (findViewById(R.id.CurrentLocationTextview))).setText("Location Disabled.");
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        GetLocation();
    }

    private void GetLocation() {
        if (location_disabled == true)
        {
            ((TextView) (findViewById(R.id.CurrentLocationTextview))).setText("Location Disabled.");
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        Location l = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), false));
        String lklStr;
        if (l == null)
        {
            lklStr = "Not found";
        }
        else
        {
            lklStr = l.toString();
        }
        ((TextView) (findViewById(R.id.CurrentLocationTextview))).setText(lklStr);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: { // Location permission.
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    location_disabled = true;
                }
                GetLocation();
                return;
            }
            case 2: { // Phone call permission.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                return;
            }
            // permissions this app might request
        }
    }
}
