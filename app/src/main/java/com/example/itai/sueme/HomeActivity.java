package com.example.itai.sueme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nadav on 7/9/2016.
 */
public class HomeActivity extends AppCompatActivity {
    private static final int NUMBER_OF_ARTICLES = 5;
    static ArrayList<Article> articles = new ArrayList<Article>();
    ArticleListViewAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DAL.getLastArticle(NUMBER_OF_ARTICLES, new DALCallback() {
            @Override
            public void callback() {
                PopulateListView();
            }
        });
        // Change welcome text
        String name = LoginActivity.ActiveUser.getName();
        ((TextView) findViewById(R.id.WelcomeHomeText)).setText("Welcome, " + name + "!");
    }


    private void PopulateListView() {
        ListView lv = (ListView) findViewById(R.id.ArticleHomeListView);
        arrayAdapter = new ArticleListViewAdapter(
                this,
                articles );
        lv.setAdapter(arrayAdapter);
        // Set the onclick listener.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article article = (Article) parent.getAdapter().getItem(position);
                ArticleActivity.article = article;
                startArticleActivityFromMainThread(article.getArticleID());
                }
            });
    }

    public void startArticleActivityFromMainThread(final int articleId) {

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(HomeActivity.this, ArticleActivity.class);
                intent.putExtra("articleId", articleId);
                startActivity(intent);
            }
        });
    }
}
