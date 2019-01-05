package cn.walkpast.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Author: Kern
 * Time: 2019/1/2 10:29
 * Description: This is..
 */

public class NetworkUtils {

    /**
     * 判断网络是否连接
     *
     * @return boolean 网络连接状态
     */
    public static boolean isConnected() {

        ConnectivityManager mConnectivityManager = (ConnectivityManager) HorizonUtils.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        //获取连接对象
        if (mNetworkInfo != null) {
            //判断是TYPE_MOBILE网络
            if (ConnectivityManager.TYPE_MOBILE == mNetworkInfo.getType()) {
                Log.i("NetworkUtils", "network type：TYPE_MOBILE");
                //判断移动网络连接状态
                NetworkInfo.State STATE_MOBILE = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
                if (STATE_MOBILE == NetworkInfo.State.CONNECTED) {
                    Log.i("NetworkUtils", "network type：TYPE_MOBILE,CONNECTED SUCCESS");
                    return mNetworkInfo.isAvailable();
                }
            }
            //判断是TYPE_WIFI网络
            if (ConnectivityManager.TYPE_WIFI == mNetworkInfo.getType()) {
                Log.i("NetworkUtils", "network type：TYPE_WIFI");
                //判断WIFI网络状态
                NetworkInfo.State STATE_WIFI = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
                if (STATE_WIFI == NetworkInfo.State.CONNECTED) {
                    Log.i("NetworkUtils", "network type：TYPE_WIFI, CONNECTED SUCCESS！");
                    return mNetworkInfo.isAvailable();
                }
            }
        }

        return false;
    }


    public static boolean is4G() {

        ConnectivityManager mConnectivityManager = (ConnectivityManager) HorizonUtils.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        //获取连接对象
        if (mNetworkInfo != null) {
            //判断是TYPE_MOBILE网络
            if (ConnectivityManager.TYPE_MOBILE == mNetworkInfo.getType()) {
                return true;
            }
        }

        return false;
    }
}
