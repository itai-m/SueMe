package com.example.itai.sueme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
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

    static Article article = null;
    static ArrayList<Comment> comments = null;
    CommentListViewAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        comments = new ArrayList<Comment>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        PopulateArticle();
        DAL.getCommets(article.getArticleID(), new DALCallback() {
            @Override
            public void callback() {
                PopulateComments();
            }
        });

    }

    private void PopulateArticle(){
        ((TextView)findViewById(R.id.ArticleNameText)).setText(article.getArticleTitle());
        ((TextView)findViewById(R.id.AuthorNameText)).setText(article.getCreatorDisplayName());
        ((TextView)findViewById(R.id.ArticleContentText)).setText(article.getArticleContent());
    }

    private void PopulateComments() {
        ListView lv = (ListView) findViewById(R.id.CommentsArticleListView);
        arrayAdapter = new CommentListViewAdapter(
                this,
                comments );
        lv.setAdapter(arrayAdapter);
        // Set the onclick listener.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Comment comment = (Comment) parent.getAdapter().getItem(position);
                startProfileActivityFromMainThread(comment.getCommentatorID());
            }
        });
    }

    public void startProfileActivityFromMainThread(final int profileId) {

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ArticleActivity.this, ProfileActivity.class);
                intent.putExtra("profileId", profileId);
                startActivity(intent);
            }
        });
    }
}
