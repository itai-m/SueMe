package com.example.itai.sueme;

import android.app.VoiceInteractor;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

public class DAL {


    public int addUser(User user){
        String uri = String.format(Constant.DataBase.URL);

        StringRequest myReq = new StringRequest(Method.GET,
                uri,
                createMyReqSuccessListener(),
                createMyReqErrorListener());
        queue.add(myReq);

        return 0;
    }
}
