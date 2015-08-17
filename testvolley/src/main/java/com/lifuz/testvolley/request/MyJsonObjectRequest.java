package com.lifuz.testvolley.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.util.Map;

/**
 * 自己定义Request，使用Gson
 *
 * 作者：李富 on 2015/8/12.
 * 邮箱：lifuzz@163.com
 */
public class MyJsonObjectRequest<T> extends Request<T> {

    private Gson gson;

    private Class<T> mclass;

    private Map<String,String> params;

    private Response.Listener<T> tListener;

    public MyJsonObjectRequest(int method, String url, Map<String,String> params, Class<T> clazz, Response.Listener<T> listener,
                               Response.ErrorListener errorListener) {
        super(method, url, errorListener);

        mclass =clazz;

        tListener = listener;
        this.params = params;

        gson = new Gson();

    }

    public MyJsonObjectRequest(String url,Map<String,String> params, Class<T> clazz, Response.Listener<T> listener,
                               Response.ErrorListener errorListener) {
        this(Method.GET, url, params,clazz, listener, errorListener);


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
    public Map<String, String> getParams() {
        return params;
    }

    @Override
    protected void deliverResponse(T t) {

        tListener.onResponse(t);

    }
}
