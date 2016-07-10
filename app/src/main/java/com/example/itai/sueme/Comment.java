package com.example.itai.sueme;

import java.util.Date;

/**
 * Created by nadav on 7/7/2016.
 */
public class Comment {

    private Date PublishDate;
    private int CommentID;                      // ID of Comment from DB.
    private int CommentatorID;                  // ID of commentator.
    private String CommentatorDisplayName;      // Display name of commentator.
    private String CommentContent;              // Content of the comment.
    private int ArticleID;                      // Article associated with this comment.

    /* Constructors */

    public Comment(int commentID, int commentatorID, String commentatorDisplayName, String commentContent, int articleID, Date publishDate) {
        CommentID = commentID;
        CommentatorID = commentatorID;
        CommentatorDisplayName = commentatorDisplayName;
        CommentContent = commentContent;
        ArticleID = articleID;
        PublishDate = publishDate;
    }

    public Date getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(Date publishDate) {
        PublishDate = publishDate;
    }

    /* Getters */
    public int getCommentatorID() {
        return CommentatorID;
    }

    public String getCommentatorDisplayName() {
        return CommentatorDisplayName;
    }

    public int getCommentID() {
        return CommentID;
    }

    public String getCommentContent() {
        return CommentContent;
    }

    public int getArticleID() {
        return ArticleID;
    }

    /* Setters */
    public void setCommentatorID(int commentatorID) {
        CommentatorID = commentatorID;
    }

    public void setCommentatorDisplayName(String commentatorDisplayName) {
        CommentatorDisplayName = commentatorDisplayName;
    }

    public void setCommentID(int commentID) {
        CommentID = commentID;
    }

    public void setCommentContent(String commentContent) {
        CommentContent = commentContent;
    }

    public void setArticleID(int articleID) {
        ArticleID = articleID;
    }
}
