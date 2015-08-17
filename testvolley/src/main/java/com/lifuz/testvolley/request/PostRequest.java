package com.lifuz.testvolley.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.lifuz.testvolley.bean.FormImage;
import com.lifuz.testvolley.bean.FormText;
import com.lifuz.testvolley.utils.MessageBodyUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 图片和文字同时上传的Request请求
 * <p/>
 * 作者：李富 on 2015/8/17.
 * 邮箱：lifuzz@163.com
 */
public class PostRequest extends Request<String> {

    /**
     * 正确数据的时候回掉用
     */
    private Response.Listener mListener;
    /*请求 数据通过参数的形式传入*/
    private List<Object> mListItem;

    private String BOUNDARY = "--------------520-13-14"; //数据分隔线
    private String MULTIPART_FORM_DATA = "multipart/form-data";

    public PostRequest(String url, List<Object> listItem, Response.Listener listener,
                       Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        this.mListener = listener;
        setShouldCache(false);
        mListItem = listItem;
        //设置请求的响应事件，因为文件上传需要较长的时间，所以在这里加大了，设为5秒
        setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    /**
     * 消息体
     *
     * @return
     * @throws AuthFailureError
     */
    @Override
    public byte[] getBody() throws AuthFailureError {

        //如果传进来的参数为空，则返回，默认的消息体
        if (mListItem == null && mListItem.size() == 0) {
            return super.getBody();
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        //把所有的参数都写入消息体中
        for (Object obj : mListItem) {

            //因为我们是混合上传，所以我们有两种类型的消息：图片，文字
            //判断参数的类是否为图片
            if (obj instanceof FormImage) {
                //如果为图片则采用图片的格式进行上传
                FormImage formImage = (FormImage) obj;

                try {
                    bos.write(MessageBodyUtils.pakageBitmap(formImage, BOUNDARY));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (obj instanceof FormText) {

                //如果是文字类型，则采用文字类型的消息体
                FormText formText = (FormText) obj;

                try {
                    bos.write(MessageBodyUtils.pakageText(formText, BOUNDARY));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }
        /*结尾行*/
        //`"--" + BOUNDARY + "--" + "\r\n"`
        String endLine = "--" + BOUNDARY + "--" + "\r\n";
        try {
            bos.write(endLine.toString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.v("zgy", "=====formImage====\n" + bos.toString());
        return bos.toByteArray();
    }

    @Override
    public String getBodyContentType() {
        return MULTIPART_FORM_DATA + "; boundary=" + BOUNDARY;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            String mString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.v("zgy", "====mString===" + mString);

            return Response.success(mString,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(String s) {

        mListener.onResponse(s);

    }
}
