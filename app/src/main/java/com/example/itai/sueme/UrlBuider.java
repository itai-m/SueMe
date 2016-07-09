package com.example.itai.sueme;

/**
 * Created by Itai on 09/07/2016.
 */
public class UrlBuider {

    private final static String EQUAL = "=";
    private final static String AND = "&";

    public static String insertUser(User user){
        String toReturn = Constant.DataBase.URL;
        toReturn += Action(Constant.DataBase.INSERT_USER);
        toReturn += andEqual(Constant.DataBase.NAME, user.getName());
        toReturn += andEqual(Constant.DataBase.EMAIL, user.getEmail());

        return toReturn;
    }

    private static String andEqual(String var, String res){
        return AND + var + EQUAL + res;
    }

    private static String Action(String action){
        return  Constant.DataBase.ACTION + "=" + action;
    }

}
