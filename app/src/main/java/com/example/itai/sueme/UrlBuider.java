package com.example.itai.sueme;


import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;


public class UrlBuider {

    private final static String EQUAL = "=";
    private final static String AND = "&";
    private final static String TRUE = "1";
    private final static String FALSE = "0";

    //build a url for insertUser
    public static String insertUser(User user){
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.INSERT_USER)
                + andEqual(Constant.DataBase.NAME, user.getName())
                + andEqual(Constant.DataBase.EMAIL, user.getEmail())
                + andEqual(Constant.DataBase.PHONE, user.getPhonenumber())
                + andEqual(Constant.DataBase.LOCATION_LAT, user.getLocationLatitude())
                + andEqual(Constant.DataBase.LOCATION_LONG, user.getLocationLongtitude())
                + andEqual(Constant.DataBase.LAWYER, user.isLawyer() ? TRUE : FALSE);
        return urlFix(toReturn);
    }

    //build a url for insertArticle
    public static String insertArticle(Article article){
        String toReturn = Constant.DataBase.URL;
        try {
            toReturn += Action(Constant.DataBase.INSERT_ARTICLE)
                    + andEqual(Constant.DataBase.ARTICLE_TITLE, URLEncoder.encode(article.getArticleTitle(),"UTF-8"))
                    + andEqual(Constant.DataBase.ARTICLE_CONTENT, URLEncoder.encode(article.getArticleContent(),"UTF-8"))
                    + andEqual(Constant.DataBase.CREATOR_ID, String.valueOf(article.getCreatorID())
                    + andEqual(Constant.DataBase.CREATOR_DISPLAY_NAME, article.getCreatorDisplayName()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlFix(toReturn);
    }

    //build a url for insertTag
    public static String insertTag(String name) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.INSERT_TAG)
                + andEqual(Constant.DataBase.NAME, name);
        return urlFix(toReturn);
    }

    //build a url for insertTagsToArticle
    public static String insertTagsToArticle(int tagID, int articleID, String CreatorDisplayName) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.INSERT_TAGS_TO_ARTICLE)
                + andEqual(Constant.DataBase.TAG_ID, String.valueOf(tagID))
                + andEqual(Constant.DataBase.ARTICLE_ID, String.valueOf(articleID)
                + andEqual(Constant.DataBase.CREATOR_DISPLAY_NAME, CreatorDisplayName));
        return urlFix(toReturn);
    }

    //build a url for insertComment
    public static String insertComment(Comment comment) {
        String toReturn = Constant.DataBase.URL;
        try {
            toReturn += Action(Constant.DataBase.INSERT_COMMENT)
                    + andEqual(Constant.DataBase.COMMENTOTOR_OR_DISPLAYNAME, comment.getCommentatorDisplayName())
                    + andEqual(Constant.DataBase.COMMENT_CONTENT, URLEncoder.encode(comment.getCommentContent(),"UTF-8"))
                    + andEqual(Constant.DataBase.CREATOR_ID, String.valueOf(comment.getCommentatorID())
                    + andEqual(Constant.DataBase.ARTICLE_ID, String.valueOf(comment.getArticleID())));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlFix(toReturn);
    }

    //build a url for getUser
    public static String getUser(int id) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_USER_BY_ID)
                + andEqual(Constant.DataBase.ID, String.valueOf(id));
        return urlFix(toReturn);
    }

    //build a url for getUserByMail
    public static String getUserByMail(String email) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_USER_BY_EMAIL)
                + andEqual(Constant.DataBase.EMAIL, email);
        return urlFix(toReturn);
    }

    //build a url for getCommentByCreatorID
    public static String getCommentByCreatorID(int creatorID) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_COMMENT_BY_CREATOR_ID)
                + andEqual(Constant.DataBase.CREATOR_ID, String.valueOf(creatorID));
        return urlFix(toReturn);
    }

    //build a url for getTagByID
    public static String getTagByID(int id) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_TAG_BY_ID)
                + andEqual(Constant.DataBase.ID, String.valueOf(id));
        return urlFix(toReturn);
    }

    //build a url for getTagIdByArticleID
    public static String getTagIdByArticleID(int articleID) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_TAG_ID_BY_ARTICLE_ID)
                + andEqual(Constant.DataBase.ARTICLE_ID, String.valueOf(articleID));
        return urlFix(toReturn);
    }

    //build a url for getLastArticle
    public static String getLastArticle(int numberOfArticle) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_LAST_ARTICLE)
                + andEqual(Constant.DataBase.NUMBER, String.valueOf(numberOfArticle));
        return urlFix(toReturn);
    }

    //build a url for getLastArticleByUserID
    public static String getLastArticleByUserID(int numberOfArticles, int userId) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_LAST_ARTICLE_BY_USER_ID)
                + andEqual(Constant.DataBase.NUMBER, String.valueOf(numberOfArticles)
                + andEqual(Constant.DataBase.CREATOR_ID, String.valueOf(userId)));
        return urlFix(toReturn);
    }

    //build a url for getCommentByArticleID
    public static String getCommentByArticleID(int articleID) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_COMMENT_BY_ARTICLE_ID)
                + andEqual(Constant.DataBase.ARTICLE_ID, String.valueOf(articleID));
        return urlFix(toReturn);
    }

    //build a url for searchArticleInTitle
    public static String searchArticleInTitle(String name) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.SEARCH_ARTICLE_IN_TITLE)
                + andEqual(Constant.DataBase.NAME, name);
        return urlFix(toReturn);
    }

    //build a url for andEqual
    private static String andEqual(String var, String res){
        return AND + var + EQUAL + res;
    }

    //build a url for Action
    private static String Action(String action){
        return  Constant.DataBase.ACTION + EQUAL + action;
    }

    //build a url for urlFix
    private static String urlFix(String url){
        URI uri = null;
        try {
            uri = new URI(url.replace(" ", "%20"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return (uri.toString());
    }

}
