package com.example.itai.sueme;

import android.provider.BaseColumns;

public class Constant {
    private Constant(){
        throw new AssertionError("Can't create constant");
    }

    public static abstract class UserPreference implements BaseColumns{
        public static final String TABLE_NAME = "UserPreference";

    }

    public static abstract class DataBase {
        public static final String URL = "http://sueme.berry-games.com/api.php?";

        public static final String NAME = "Name";
        public static final String EMAIL = "Email";
        public static final String PHONE = "Phonenumber";
        public static final String LOCATION = "Location";
        public static final String LAWYER = "Lawyer";

        public static final String ACTION = "Action";

        public static final String INSERT_USER = "InsertUser";
        public static final String GET_USER_BY_ID = "getUserByID";
        public static final String INSERT_ARTICLE = "insertArticle";
        public static final String INSERT_TAG = "insertTag";
        public static final String INSERT_TAGS_TO_ARTICLE = "insertTagsToArticle";
        public static final String INSERT_CCOMMENT = "insertComment";
        public static final String GET_USER_BY_EMAIL = "getUserByEmail";

        public static final String ARTICLE_TITLE = "ArticleTitle";
        public static final String ARTICLE_CONTENT = "ArticleContent";
        public static final String CREATOR_ID = "CreatorID";

        public static final String TAG_ID = "TagID";
        public static final String ARTICLE_ID = "ArticleID";

        public static final String COMMENTOTOR_OR_DISPLAYNAME = "CommentatorDisplayName";
        public static final String COMMENT_CONTENT = "CommentContent";

    }
}
