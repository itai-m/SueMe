package com.example.itai.sueme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

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
        PopulateComments();

    }

    private void PopulateArticle(){
        ((TextView)findViewById(R.id.ArticleNameText)).setText(article.getArticleTitle());
        ((TextView)findViewById(R.id.AuthorNameText)).setText(article.getCreatorDisplayName());
        ((TextView)findViewById(R.id.ArticleContentText)).setText(article.getArticleContent());
    }

    private void PopulateComments() {
        comments = null;
        DAL.getComments(article.getArticleID(), new DALCallback() {
            @Override
            public void callback() {
                PopulateCommentsInner();
            }
        });

    }

    private void PopulateCommentsInner() {
        ListView lv = (ListView) findViewById(R.id.CommentsArticleListView);
        arrayAdapter = new CommentListViewAdapter(
                this,
                comments );

        // Sort list by comment date
        Collections.sort(comments, new Comparator<Comment>() {
            public int compare(Comment o1, Comment o2) {
                if (o1.getPublishDate() == null || o2.getPublishDate() == null)
                    return 0;
                return o1.getPublishDate().compareTo(o2.getPublishDate());
            }
        });

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

    public void publishCommentOnClick(View v) {
        String commentContent = ((TextView) findViewById(R.id.WriteCommentTextField)).getText().toString();
        Comment comment = new Comment(-1, LoginActivity.ActiveUser.getId(), LoginActivity.ActiveUser.getName() , commentContent, article.getArticleID(), new Date());
        DAL.publishComment(comment, new DALCallback() {
            @Override
            public void callback() {
                PopulateComments();
            }
        });
    }

}
