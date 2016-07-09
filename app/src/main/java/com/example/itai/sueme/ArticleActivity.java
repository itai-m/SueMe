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
public class ArticleActivity extends AppCompatActivity {

    ArrayList<Comment> comments = null;
    CommentListViewAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        comments = new ArrayList<Comment>();
        comments.add(new Comment(1, 1, "nadav", "my pretty comment", 12));
        comments.add(new Comment(1, 1, "nadav", "my ugly comment", 12));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        // Get the article id from the intent.
        Intent mIntent = getIntent();
        int articleId = mIntent.getIntExtra("articleId", 0);
        PopulateComments();
    }

    private void PopulateComments() {
        ListView lv = (ListView) findViewById(R.id.CommentsArticleListView);
        arrayAdapter = new CommentListViewAdapter(
                this,
                comments );
        lv.setAdapter(arrayAdapter);
        // Set the onclick listener.
    }
}
