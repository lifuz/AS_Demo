package com.lifuz.testvolley.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

/**
 * 自己定义Request，使用Gson
 *
 * 作者：李富 on 2015/8/12.
 * 邮箱：lifuzz@163.com
 */
public class JsonObjectRequest<T> extends Request<T> {

    private Gson gson;

    private Class<T> mclass;

    private Response.Listener<T> tListener;

    public JsonObjectRequest(int method, String url, Class<T> clazz, Response.Listener<T> listener,
                             Response.ErrorListener errorListener) {
        super(method, url, errorListener);

        mclass =clazz;

        tListener = listener;

        gson = new Gson();

    }

    public JsonObjectRequest(String url, Class<T> clazz, Response.Listener<T> listener,
                             Response.ErrorListener errorListener) {
        this(Method.GET, url, clazz, listener, errorListener);
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {
            String jsonStr = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            return Response.success(gson.fromJson(jsonStr,mclass),
                    HttpHeaderParser.parseCacheHeaders(response));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void deliverResponse(T t) {

        tListener.onResponse(t);

    }
}
