package com.example.itai.sueme;


import java.net.URI;
import java.net.URISyntaxException;


public class UrlBuider {

    private final static String EQUAL = "=";
    private final static String AND = "&";
    private final static String TRUE = "1";
    private final static String FALSE = "0";

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
    public static String insertArticle(Article article){
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.INSERT_ARTICLE)
                + andEqual(Constant.DataBase.ARTICLE_TITLE, article.getArticleTitle())
                + andEqual(Constant.DataBase.ARTICLE_CONTENT, article.getArticleContent())
                + andEqual(Constant.DataBase.CREATOR_ID, String.valueOf(article.getCreatorID())
                + andEqual(Constant.DataBase.CREATOR_DISPLAY_NAME, article.getCreatorDisplayName()));
        return urlFix(toReturn);
    }

    public static String insertTag(String name) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.INSERT_TAG)
                + andEqual(Constant.DataBase.NAME, name);
        return urlFix(toReturn);
    }

    public static String insertTagsToArticle(int tagID, int articleID, String CreatorDisplayName) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.INSERT_TAGS_TO_ARTICLE)
                + andEqual(Constant.DataBase.TAG_ID, String.valueOf(tagID))
                + andEqual(Constant.DataBase.ARTICLE_ID, String.valueOf(articleID)
                + andEqual(Constant.DataBase.CREATOR_DISPLAY_NAME, CreatorDisplayName));
        return urlFix(toReturn);
    }

    public static String insertComment(Comment comment) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.INSERT_COMMENT)
                + andEqual(Constant.DataBase.COMMENTOTOR_OR_DISPLAYNAME, comment.getCommentatorDisplayName())
                + andEqual(Constant.DataBase.COMMENT_CONTENT, comment.getCommentContent())
                + andEqual(Constant.DataBase.CREATOR_ID, String.valueOf(comment.getCommentatorID())
                + andEqual(Constant.DataBase.ARTICLE_ID, String.valueOf(comment.getArticleID())));
        return urlFix(toReturn);
    }


    public static String getUser(int id) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_USER_BY_ID)
                + andEqual(Constant.DataBase.ID, String.valueOf(id));
        return urlFix(toReturn);
    }

    public static String getUserByMail(String email) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_USER_BY_EMAIL)
                + andEqual(Constant.DataBase.EMAIL, email);
        return urlFix(toReturn);
    }

    public static String getCommentByCreatorID(int creatorID) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_COMMENT_BY_CREATOR_ID)
                + andEqual(Constant.DataBase.CREATOR_ID, String.valueOf(creatorID));
        return urlFix(toReturn);
    }

    public static String getTagByID(int id) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_TAG_BY_ID)
                + andEqual(Constant.DataBase.ID, String.valueOf(id));
        return urlFix(toReturn);
    }

    public static String getTagIdByArticleID(int articleID) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_TAG_ID_BY_ARTICLE_ID)
                + andEqual(Constant.DataBase.ARTICLE_ID, String.valueOf(articleID));
        return urlFix(toReturn);
    }

    public static String getLastArticle(int numberOfArticle) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_LAST_ARTICLE)
                + andEqual(Constant.DataBase.NUMBER, String.valueOf(numberOfArticle));
        return urlFix(toReturn);
    }

    public static String getLastArticleByUserID(int numberOfArticles, int userId) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_LAST_ARTICLE_BY_USER_ID)
                + andEqual(Constant.DataBase.NUMBER, String.valueOf(numberOfArticles)
                + andEqual(Constant.DataBase.CREATOR_ID, String.valueOf(userId)));
        return urlFix(toReturn);
    }
    public static String getCommentByArticleID(int articleID) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_COMMENT_BY_ARTICLE_ID)
                + andEqual(Constant.DataBase.ARTICLE_ID, String.valueOf(articleID));
        return urlFix(toReturn);
    }

    public static String searchArticleInTitle(String name) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.SEARCH_ARTICLE_IN_TITLE)
                + andEqual(Constant.DataBase.NAME, name);
        return urlFix(toReturn);
    }

    private static String andEqual(String var, String res){
        return AND + var + EQUAL + res;
    }

    private static String Action(String action){
        return  Constant.DataBase.ACTION + EQUAL + action;
    }

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
