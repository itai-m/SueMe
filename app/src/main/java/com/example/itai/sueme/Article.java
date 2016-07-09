package com.example.itai.sueme;

/**
 * Created by nadav on 7/7/2016.
 */
public class Article {
    /* Class variables */
    private int ArticleID;              // ID in the database.
    private String ArticleTitle;        // Title of the article.
    private String ArticleContent;      // Content of the article.
    private int CreatorID;              // Article creator user ID.
    private String CreatorDisplayName;  // Article creator display name.
    private int Upvotes, Downvotes;     // TODO: Upvotes tracker. Not yet implemented.

    /* Constructors */
    public Article(int mArticleID, String mArticleTitle, String mArticleContent,
                    int mCreatorID, String mCreatorDisplayName) {

        this.ArticleID = mArticleID;
        this.ArticleTitle = mArticleTitle;
        this.ArticleContent = mArticleContent;
        this.CreatorID = mCreatorID;
        this.CreatorDisplayName = mCreatorDisplayName;
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


    public int getCreatorID() {
        return CreatorID;
    }

    public String getCreatorDisplayName() {
        return CreatorDisplayName;
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

    public void setCreatorID(int creatorID) {
        CreatorID = creatorID;
    }

    public void setCreatorDisplayName(String creatorDisplayName) {
        CreatorDisplayName = creatorDisplayName;
    }

}
