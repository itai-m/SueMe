package com.example.itai.sueme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nadav on 7/9/2016.
 */
public class HomeActivity extends AppCompatActivity {
    ArrayList<Article> articles = new ArrayList<Article>();
    ArticleListViewAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        articles.add(new Article(0, "Title", "long description goes here", null, 0, "nadav", null));
        articles.add(new Article(1, "secpmdToit", "long description goes here", null, 0, "nadav", null));
        articles.add(new Article(2, "asfopjmasp", "long description goes here", null, 0, "nadav", null));
        articles.add(new Article(3, "asofdk", "wqofjpqowejgf3pqotjg3p21qojt32pogjpwojpojgwepogjwepo", null, 0, "nadav", null));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Change welcome text
        String name = LoginActivity.ActiveUser.getName();
        ((TextView) findViewById(R.id.WelcomeHomeText)).setText("Welcome, " + name + "!");
        PopulateListView();
    }

    private void PopulateListView() {
        ListView lv = (ListView) findViewById(R.id.ArticleHomeListView);
        arrayAdapter = new ArticleListViewAdapter(
                this,
                articles );
        lv.setAdapter(arrayAdapter);
    }

}
