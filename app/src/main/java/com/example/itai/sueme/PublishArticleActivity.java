package com.example.itai.sueme;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PublishArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_article);
    }

    public void publishOnClick(View v) {
        String title = ((TextView)findViewById(R.id.ArticleTitlePublishArticleText)).getText().toString();
        String content = ((TextView)findViewById(R.id.ArticleContentPublishArticleText)).getText().toString();
        Article article = new Article(-1, title, content, LoginActivity.ActiveUser.getId(), LoginActivity.ActiveUser.getName());
        // Submit article and content to server.
        DAL.publishArticle(article, new DALCallback() {
            @Override
            public void callback() {
                startHomeActivityFromMainThread();
            }
        });

    }

    public void startHomeActivityFromMainThread() {

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PublishArticleActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
