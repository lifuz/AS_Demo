package com.lifuz.testvolley.utils;

import com.lifuz.testvolley.bean.FormImage;
import com.lifuz.testvolley.bean.FormText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 生成消息体
 *
 * 作者：李富 on 2015/8/17.
 * 邮箱：lifuzz@163.com
 */
public class MessageBodyUtils {

    public static byte[] pakageBitmap (FormImage formImage , String BOUNDARY){

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
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

        return bos.toByteArray();

    }

    public static byte[] pakageText(FormText formText, String BOUNDARY) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

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

        return bos.toByteArray();
    }

}
