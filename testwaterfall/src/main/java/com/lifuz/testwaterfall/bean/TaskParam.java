package com.lifuz.testwaterfall.bean;

import android.content.res.AssetManager;

/**
 *
 * 加载图片的参数
 *
 * 作者：李富 on 2015/8/10.
 * 邮箱：lifuzz@163.com
 */
public class TaskParam {

    private String fileName;
    private AssetManager assetManager;
    private int itemWidth;
    private int itemHeight;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public int getItemWidth() {
        return itemWidth;
    }

    public void setItemWidth(int itemWidth) {
        this.itemWidth = itemWidth;
    }

    public int getItemHeight() {
        return itemHeight;
    }

    public void setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
    }
}
