package com.lifuz.testnetconn.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 判断网络的连接状态
 * <p/>
 * 作者：李富 on 2015/8/17.
 * 邮箱：lifuzz@163.com
 */
public class NetworkUtils {


    public static boolean isNetworkConnnected(Context context) {

        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo info = manager.getActiveNetworkInfo();

            if (info != null) {
                return info.isAvailable();
            }

        }

        return false;
    }

}
