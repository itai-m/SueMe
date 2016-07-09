package com.example.itai.sueme;


import android.util.Log;

import com.android.volley.Request;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class DAL {


    public DAL(){
    }

    public int addUser(User user){
        String url = String.format(Constant.DataBase.URL);
        url = "http://sueme.berry-games.com/api.php?todo=insertUser&Name=[name]&Email=[Email]&Phonenumber=[phone]&Location=[Location]&Lawyer=0";
        JSONObject json = getJson(url);
        return 0;
    }

    public User getUserByID (int id){
        String url = String.format(Constant.DataBase.URL);
        url = "http://sueme.berry-games.com/api.php?todo=getUserByID&Id=165";
        JSONObject json = getJson(url);
        return null;
    }

    private JSONObject getJson(String url){
        JSONObject toReturn;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("", "Response: " + response.toString());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("", "Error");

            }
        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);
        return null;
    }
}
