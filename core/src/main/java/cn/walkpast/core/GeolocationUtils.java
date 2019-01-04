package cn.walkpast.core;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

/**
 * Author: Kern
 * Time: 2019/1/4 12:08
 * Description: This is..
 */

public class GeolocationUtils {

    /**
     * GPS是否打开
     *
     * @param context
     * @return
     */
    public static boolean isSystemLocationEnable(Context context) {

        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gpsLocationEnable = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean networkLocationEnable = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gpsLocationEnable && networkLocationEnable;
    }


    /**
     * 跳转到设置页面GPS开关页面
     *
     * @param context
     */
    public static void actionLocation(Context context) {

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
    }
}
