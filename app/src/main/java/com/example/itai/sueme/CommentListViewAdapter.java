package com.example.itai.sueme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by nadav on 7/9/2016.
 */
public class CommentListViewAdapter extends ArrayAdapter<Comment> {
    public CommentListViewAdapter(Context context, ArrayList<Comment> articles) {
        super(context, 0, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Comment comment = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_article_comment, parent, false);
        }
        // Lookup view for data population
        TextView commentator = (TextView) convertView.findViewById(R.id.CommentatorNameCommentViewText);
        TextView content = (TextView) convertView.findViewById(R.id.CommentContentCommentViewText);
        TextView date = (TextView) convertView.findViewById(R.id.CommentDateCommentViewText);
        // Populate the data into the template view using the data object
        commentator.setText(comment.getCommentatorDisplayName());
        content.setText(comment.getCommentContent());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formatted = sdf.format(comment.getPublishDate());
        date.setText(formatted);
        // Return the completed view to render on screen
        return convertView;
    }


}
