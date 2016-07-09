package com.example.itai.sueme;


import android.util.Log;

import com.android.volley.Request;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

interface DALCallback {
    void callback();
}

public class DAL {

    public static void getLastArticle(int numer, final DALCallback callback){
        String apiCall = UrlBuider.getLastArticle(numer);
        final DALCallback callbk = callback;
        // Actions to do when succeeding
        Response.Listener<JSONObject> response = new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Ss",response.toString());
                try{
                    JSONObject jArray = response.getJSONObject("");
                    HomeActivity.articles = new ArrayList<Article>();
                    for (int i=0; i < jArray.length(); i++){
                        HomeActivity.articles.add(jsonToArticle(jArray.getJSONObject("")));
                    }
                    if (callbk != null) {
                        callbk.callback();
                    }
                } catch (Exception e){
                    Log.d("Error", e.getMessage());
                }
            }
        };

        // Actions to do when faililng:
        Response.ErrorListener error = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("", "Error");
            }
        };
        sendJson(apiCall, response, error);
    }

    public static int addUser(User user, final DALCallback callback){
        String apiCall = UrlBuider.insertUser(user);
        LoginActivity.ActiveUser = user;
        final DALCallback callbk = callback;
        // Actions to do when succeeding
        Response.Listener<JSONObject> response = new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try{
                    int id = response.getInt("id");
                    LoginActivity.ActiveUser.setId(id);
                    if (callbk != null) {
                        callbk.callback();
                    }
                } catch (Exception e){

                }
            }
        };

        // Actions to do when faililng:
        Response.ErrorListener error = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("", "Error");
            }
        };
        sendJson(apiCall, response, error);
        return 0;
    }

    public static User getUserByID (int id){
        String apiCall = UrlBuider.getUser(id);

        // Actions to do when succeeding
        Response.Listener<JSONObject> response = new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                // Do stuff here with the response.
                Log.d("", "Response: " + response.toString());
            }
        };

        // Actions to do when faililng:
        Response.ErrorListener error = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("", "Error");
            }
        };
        sendJson(apiCall, response, error);
        return null;
    }

    public static User getUserByEmail (String email, final DALCallback callback){
        String apiCall = UrlBuider.getUserByMail(email);
        final DALCallback callbk = callback;
        // Actions to do when succeeding
        Response.Listener<JSONObject> response = new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                // Do stuff here with the response.
                Log.d("", "Response: " + response.toString());
                LoginActivity.ActiveUser = jsonToUser(response);
                LoginActivity.findUser = true;
                if (callbk != null) {
                    callbk.callback();
                }
            }
        };

        // Actions to do when faililng:
        Response.ErrorListener error = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("", "Error");
            }
        };
        sendJson(apiCall, response, error);
        return null;
    }


    private static User jsonToUser(JSONObject json){
        User user = null;
        try{
            user = new User(json.getInt(Constant.DataBase.ID),
                    json.getString(Constant.DataBase.NAME),
                    json.getString(Constant.DataBase.EMAIL),
                    json.getString(Constant.DataBase.PHONE),
                    json.getString(Constant.DataBase.LOCATION),
                    json.getInt(Constant.DataBase.LAWYER));
        }catch (Exception e){
            Log.d("Error", e.getMessage());
        }
        return user;
    }

    private static Article jsonToArticle(JSONObject json){
        Article article = null;
        try{
            article = new Article(json.getInt(Constant.DataBase.ID),
                    json.getString(Constant.DataBase.ARTICLE_TITLE),
                    json.getString(Constant.DataBase.ARTICLE_CONTENT),
                    json.getInt(Constant.DataBase.CREATOR_ID),
                    json.getString(Constant.DataBase.ARTICLE_ID));
        }catch (Exception e){
            Log.d("Error", e.getMessage());
        }
        return article;
    }

    private static Comment jsonToCommet(JSONObject json){
        Comment comment = null;
        try{
            comment = new Comment(json.getInt(Constant.DataBase.ID),
                    json.getInt(Constant.DataBase.CREATOR_ID),
                    json.getString(Constant.DataBase.COMMENTOTOR_OR_DISPLAYNAME),
                    json.getString(Constant.DataBase.COMMENT_CONTENT),
                    json.getInt(Constant.DataBase.ARTICLE_ID));
        }catch (Exception e){
            Log.d("Error", e.getMessage());
        }
        return comment;
    }

    private static JSONObject sendJson(String url, Response.Listener<JSONObject> responseCallback, Response.ErrorListener errCallback){
        JSONObject toReturn;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, responseCallback, errCallback);
        AppController.getInstance().addToRequestQueue(jsObjRequest);
        return null;
    }

}
