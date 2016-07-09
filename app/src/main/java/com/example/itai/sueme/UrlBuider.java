package com.example.itai.sueme;


/**
 * Created by Itai on 09/07/2016.
 */
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
                + andEqual(Constant.DataBase.LOCATION, user.getLocation())
                + andEqual(Constant.DataBase.LAWYER, user.isLawyer() ? TRUE : FALSE);
        return toReturn;
    }

    public static String insertArticle(Article article){
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.INSERT_ARTICLE)
                + andEqual(Constant.DataBase.ARTICLE_TITLE, article.getArticleTitle())
                + andEqual(Constant.DataBase.ARTICLE_CONTENT, article.getArticleContent())
                + andEqual(Constant.DataBase.CREATOR_ID, String.valueOf(article.getCreatorID()));
        return toReturn;
    }

    public static String insertTag(String name) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.INSERT_TAG)
                + andEqual(Constant.DataBase.NAME, name);
        return toReturn;
    }

    public static String insertTagsToArticle(int tagID, int articleID) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.INSERT_TAGS_TO_ARTICLE)
                + andEqual(Constant.DataBase.TAG_ID, String.valueOf(tagID))
                + andEqual(Constant.DataBase.ARTICLE_ID, String.valueOf(articleID));
        return toReturn;
    }

    public static String insertComment(Comment comment) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.INSERT_CCOMMENT)
                + andEqual(Constant.DataBase.COMMENTOTOR_OR_DISPLAYNAME, comment.getCommentatorDisplayName())
                + andEqual(Constant.DataBase.COMMENT_CONTENT, comment.getCommentContent())
                + andEqual(Constant.DataBase.CREATOR_ID, String.valueOf(comment.getCommentatorID()));
        return toReturn;
    }


    public static String getUser(int id) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_USER_BY_ID)
                + andEqual(Constant.DataBase.ID, String.valueOf(id));
        return toReturn;
    }

    public static String getUserByMail(String email) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_USER_BY_EMAIL)
                + andEqual(Constant.DataBase.EMAIL, email);
        return toReturn;
    }

    public static String getCommentByCreatorID(int creatorID) {
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.GET_COMMENT_BY_CREATOR_ID)
                + andEqual(Constant.DataBase.CREATOR_ID, String.valueOf(creatorID));
        return toReturn;
    }

    private static String andEqual(String var, String res){
        return AND + var + EQUAL + res;
    }

    private static String Action(String action){
        return  Constant.DataBase.ACTION + EQUAL + action;
    }


}
