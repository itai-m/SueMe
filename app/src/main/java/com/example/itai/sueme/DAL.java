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


    private JSONObject jsonObject;

    public DAL(){
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }


    public int addUser(User user){
        String apiCall = UrlBuider.insertUser(user);

        // Actions to do when succeeding
        Response.Listener<JSONObject> response = new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try{
                    Log.d("test", "" + response.getInt("id"));
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

        JSONObject json = sendJson(apiCall, response, error);
        return 0;
    }

    public User getUserByID (int id){
        String apiCall = UrlBuider.getUser(id);

        // Actions to do when succeeding
        Response.Listener<JSONObject> response = new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                // Do stuff here with the response.
                Log.d("", "Response: " + response.toString());
                setJsonObject(response);
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

    private JSONObject sendJson(String url, Response.Listener<JSONObject> responseCallback, Response.ErrorListener errCallback){
        JSONObject toReturn;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, responseCallback, errCallback);
        AppController.getInstance().addToRequestQueue(jsObjRequest);
        return null;
    }

}
