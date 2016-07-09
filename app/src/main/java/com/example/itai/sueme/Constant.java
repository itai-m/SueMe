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
        public static final String URL = "http://sueme.berry-games.com/api.php";
    }
}
