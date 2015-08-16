package com.lifuz.testvolley.bean;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * 上传图片的bean类
 *
 * 作者：李富 on 2015/8/16.
 * 邮箱：lifuzz@163.com
 */
public class FormImage {
    //参数的名称
    private String mName ;
    //文件名
    private String mFileName ;
    //文件的 mime，需要根据文档查询
    private String mMime ;
    //需要上传的图片资源
    private Bitmap mBitmap ;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmFileName() {
        return mFileName;
    }

    public void setmFileName(String mFileName) {
        this.mFileName = mFileName;
    }

    public String getmMime() {
        return mMime;
    }

    public void setmMime(String mMime) {
        this.mMime = mMime;
    }

    public byte[] getmBitmap() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
        mBitmap.compress(Bitmap.CompressFormat.JPEG,80,bos) ;
        return bos.toByteArray();
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }
}
