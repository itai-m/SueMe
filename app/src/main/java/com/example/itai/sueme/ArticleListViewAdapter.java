package com.example.itai.sueme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ArticleListViewAdapter extends ArrayAdapter<Article> {
    public ArticleListViewAdapter(Context context, ArrayList<Article> articles) {
        super(context, 0, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Article article = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_home_article, parent, false);
        }
        // Lookup view for data population
        TextView author = (TextView) convertView.findViewById(R.id.AuthorArticleViewText);
        TextView content = (TextView) convertView.findViewById(R.id.ContentArticleViewText);
        TextView title = (TextView) convertView.findViewById(R.id.ArticleNameArticleViewText);
        // Populate the data into the template view using the data object
        title.setText(article.getArticleTitle());
        author.setText(article.getCreatorDisplayName());
        content.setText(article.getArticleContent());
        // Return the completed view to render on screen
        return convertView;
    }


}
