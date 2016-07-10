package com.example.itai.sueme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by nadav on 7/9/2016.
 */
public class ProfileActivity extends AppCompatActivity {
    static boolean PhoneCallPermission = true;
    static User CurrentActiveProfile = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // Get current profile id
        Intent mIntent = getIntent();
        int profileId = mIntent.getIntExtra("profileId", 0);
        PopulateProfilePage(profileId);

    }

    private void PopulateProfilePage(int profileId) {
        DAL.getUserByID(profileId, new DALCallbackUserObject() {
            @Override
            public void callback(final User u) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CurrentActiveProfile = u;
                        ((TextView)findViewById(R.id.ProfileDisplayName)).setText("Profile for:  " + u.getName() + "");
                        ((TextView)findViewById(R.id.PhoneNumberProfileText)).setText(u.getPhonenumber());
                    }
                });
            }
        });
    }

    public void showLocationOnClick(View v) {
        User u = CurrentActiveProfile;
        try {
            double latitude = Double.parseDouble(u.getLocationLatitude());
            double longitude = Double.parseDouble(u.getLocationLatitude());
            String label = u.getName() + "'s Location";
            String uriBegin = "geo:" + latitude + "," + longitude;
            String query = latitude + "," + longitude + "(" + label + ")";
            String encodedQuery = Uri.encode(query);
            String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
            Uri uri = Uri.parse(uriString);
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch (Exception e) {
            Log.d("ShowLocation", e.toString());
            return;
        }

    }
    public void onCallClick(View v) {
        String phoneNumber = ((TextView) findViewById(R.id.PhoneNumberProfileText)).getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        if (PhoneCallPermission == false || ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE}, 2);
            return;
        }
        startActivity(intent);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 2: { // Phonecall Permission
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhoneCallPermission = true;
                    onCallClick(null);
                } else {
                    PhoneCallPermission = false;
                }
            }
            // permissions this app might request
        }
    }

}
