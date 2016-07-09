package com.example.itai.sueme;


import android.util.Log;

import com.android.volley.Request;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONObject;


public class DAL {

    public DAL(){
    }

    public int addUser(User user){
        String apiCall = UrlBuider.insertUser(user);
        JSONObject json = sendJson(apiCall);
        try{
            Log.d("test", "" + json.getInt("id"));
        }catch (Exception e){

        }
        return 0;
    }

    public User getUserByID (int id){
        String apiCall = UrlBuider.getUser(id);
        sendJson(apiCall);
        return null;
    }

    private JSONObject sendJson(String url){
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
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
        try {
            JSONObject response = future.get();
            return response;
        } catch (Exception e) {
            return null;
        }
    }


}
