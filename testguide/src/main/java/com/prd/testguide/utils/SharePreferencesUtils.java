package com.prd.testguide.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 跟SharePreferences文件相关的工具类
 * <p/>
 * 作者：李富 on 2016/3/2.
 * 邮箱：lifuzz@163.com
 */
public class SharePreferencesUtils {

    /**
     * 判断app是否是第一次启动
     * 如果是返回true，如果不是返回false
     * @param context
     * @return
     */
    public static boolean isFirst(Context context) {

        SharedPreferences share = context.getSharedPreferences("login",context.MODE_PRIVATE);

        return share.getBoolean("first",true);

    }

    /**
     * 设置first属性为false
     * @param context
     */
    public static void setFirst(Context context) {

        SharedPreferences share = context.getSharedPreferences("login",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();

        editor.putBoolean("first",false);
        editor.commit();
    }

}
