package com.example.itai.sueme;


import android.util.Log;

import com.android.volley.Request;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONArray;
import org.json.JSONObject;


public class DAL {


    public static int addUser(User user){
        String apiCall = UrlBuider.insertUser(user);
        LoginActivity.ActiveUser = user;
        // Actions to do when succeeding
        Response.Listener<JSONObject> response = new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try{
                    int id = response.getInt("id");
                    LoginActivity.ActiveUser.setId(id);
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

    public static User getUserByEmail (String email){
        String apiCall = UrlBuider.getUserByMail(email);

        // Actions to do when succeeding
        Response.Listener<JSONObject> response = new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                // Do stuff here with the response.
                Log.d("", "Response: " + response.toString());
                LoginActivity.ActiveUser = jsonToUser(response);
                LoginActivity.findUser = true;
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
                    json.getBoolean(Constant.DataBase.LAWYER));
        }catch (Exception e){
            Log.d("Error", e.getMessage());
        }
        return user;
    }

    private static JSONObject sendJson(String url, Response.Listener<JSONObject> responseCallback, Response.ErrorListener errCallback){
        JSONObject toReturn;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, responseCallback, errCallback);
        AppController.getInstance().addToRequestQueue(jsObjRequest);
        return null;
    }

}
