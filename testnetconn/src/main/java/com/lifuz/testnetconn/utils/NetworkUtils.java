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


    /**
     * 测试网络连接是否正常
     * @param context
     * @return
     */
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

    /**
     * 判断WiFi网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWiFiConnected (Context context) {

        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if (info != null) {
                return info.isAvailable();
            }

        }

        return false;
    }

    /**
     * 判断移动网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isMobileConnected (Context context) {

        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null) {
                return info.isAvailable();
            }
        }

        return false;
    }

}
