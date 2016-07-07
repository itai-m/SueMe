package com.example.itai.sueme;

/**
 * Created by nadav on 7/7/2016.
 */
public class Article {
    /* Class variables */
    private int ArticleID;              // ID in the database.
    private String ArticleTitle;        // Title of the article.
    private String ArticleContent;      // Content of the article.
    private String[] Tags;              // Tags related to the article.
    private int CreatorID;              // Article creator user ID.
    private String CreatorDisplayName;  // Article creator display name.
    private Comment[] Comments;         // List of comments to this article.
    private int Upvotes, Downvotes;     // TODO: Upvotes tracker. Not yet implemented.

    /* Constructors */
    public Article(int mArticleID, String mArticleTitle, String mArticleContent, String[] mTags,
                    int mCreatorID, String mCreatorDisplayName, Comment[] mComments) {

        this.ArticleID = mArticleID;
        this.ArticleTitle = mArticleTitle;
        this.ArticleContent = mArticleContent;
        this.Tags = mTags;
        this.CreatorID = mCreatorID;
        this.CreatorDisplayName = mCreatorDisplayName;
        this.Comments = mComments;
    }

    /* Getters */
    public int getArticleID() {
        return ArticleID;
    }

    public String getArticleTitle() {
        return ArticleTitle;
    }

    public String getArticleContent() {
        return ArticleContent;
    }

    public String[] getTags() {
        return Tags;
    }

    public int getCreatorID() {
        return CreatorID;
    }

    public String getCreatorDisplayName() {
        return CreatorDisplayName;
    }

    public Comment[] getComments() {
        return Comments;
    }

    /* Setters */
    public void setArticleID(int articleID) {
        ArticleID = articleID;
    }

    public void setArticleTitle(String articleTitle) {
        ArticleTitle = articleTitle;
    }

    public void setArticleContent(String articleContent) {
        ArticleContent = articleContent;
    }

    public void setTags(String[] tags) {
        Tags = tags;
    }

    public void setCreatorID(int creatorID) {
        CreatorID = creatorID;
    }

    public void setCreatorDisplayName(String creatorDisplayName) {
        CreatorDisplayName = creatorDisplayName;
    }

    public void setComments(Comment[] comments) {
        Comments = comments;
    }


}
