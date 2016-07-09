package com.example.itai.sueme;

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
        // Submit article and content to server.

    }
}
