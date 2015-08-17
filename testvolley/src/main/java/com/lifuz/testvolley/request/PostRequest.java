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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 普通参数的bean类
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

    @Override
    public byte[] getBody() throws AuthFailureError {

        if (mListItem == null && mListItem.size() == 0) {
            return super.getBody();
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int N = mListItem.size();

        for (int i = 0; i < N; i++) {

            Object obj = mListItem.get(i);
            Log.i("tag", mListItem.size() + "");

            if (obj instanceof FormImage) {
                FormImage formImage = (FormImage) obj;
                StringBuffer sb = new StringBuffer();
            /*第一行*/
                //`"--" + BOUNDARY + "\r\n"`
                sb.append("--" + BOUNDARY);
                sb.append("\r\n");
            /*第二行*/
                //Content-Disposition: form-data; name="参数的名称"; filename="上传的文件名" + "\r\n"
                sb.append("Content-Disposition: form-data;");
                sb.append(" name=\"");
                sb.append(formImage.getmName());
                sb.append("\"");
                sb.append("; filename=\"");
                sb.append(formImage.getmFileName());
                sb.append("\"");
                sb.append("\r\n");
            /*第三行*/
                //Content-Type: 文件的 mime 类型 + "\r\n"
                sb.append("Content-Type: ");
                sb.append(formImage.getmMime());
                sb.append("\r\n");
            /*第四行*/
                //"\r\n"
                sb.append("\r\n");
                try {
                    bos.write(sb.toString().getBytes("utf-8"));
                /*第五行*/
                    //文件的二进制数据 + "\r\n"
                    bos.write(formImage.getmBitmap());
                    bos.write("\r\n".getBytes("utf-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (obj instanceof FormText) {

                FormText formText = (FormText) obj;

                StringBuffer sb = new StringBuffer();
            /*第一行:"--" + boundary + "\r\n" ;*/
                sb.append("--" + BOUNDARY);
                sb.append("\r\n");
            /*第二行:"Content-Disposition: form-data; name="参数的名称"" + "\r\n" ;*/
                sb.append("Content-Disposition: form-data;");
                sb.append("name=\"");
                sb.append(formText.getName());
                sb.append("\"");
                sb.append("\r\n");
            /*第三行:"\r\n" ;*/
                sb.append("\r\n");
            /*第四行:"参数的值" + "\r\n" ;*/
                sb.append(formText.getValue());
                sb.append("\r\n");
                try {
                    bos.write(sb.toString().getBytes("utf-8"));
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