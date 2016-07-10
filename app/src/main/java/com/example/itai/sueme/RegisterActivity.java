package com.example.itai.sueme;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
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

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.RunnableFuture;

public class RegisterActivity extends AppCompatActivity {
    boolean location_disabled = false;
    private ProgressBar spinner;
    private Location location = null;
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
        String phoneNumber = ((EditText) (findViewById(R.id.PhoneNumberText))).getText().toString();
        boolean isLawyer = (userType.compareTo("Lawyer") == 0) ? true : false;
        String currLocationLat;
        String currLocationLong;
        if (location == null) {
            currLocationLat = "";
            currLocationLong = "";
        }
        else {
             currLocationLat = String.valueOf(location.getLatitude());
             currLocationLong = String.valueOf(location.getLongitude());
        }


        User userToRegister = new User(0, displayName, email, phoneNumber, currLocationLat, currLocationLong , isLawyer);
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

        // Get location on different thread, not to stuck the program.
        Thread t = new Thread(new Runnable() {
            public void run() {
                GetLocation();
            }
        });

        t.start();
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
        final String lklStr;
        if (l == null)
        {
            lklStr = "None";
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(RegisterActivity.this, "Can't get last known location.", Toast.LENGTH_LONG ).show();
                }
            });
        }
        else
        {
            location = l;
            Geocoder geocoder;
            List<Address> addresses = null;
            geocoder = new Geocoder(this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(l.getLatitude(), l.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addresses != null) {
                lklStr = addresses.get(0).getAddressLine(0);
            }
            else {
                lklStr = "None";
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this, "Cant resolve address", Toast.LENGTH_LONG).show();
                    }
                });

            }
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView) (findViewById(R.id.CurrentLocationTextview))).setText(lklStr);
            }
        });
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
