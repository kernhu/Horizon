package cn.walkpast.core;

import android.os.Build;
import android.view.View;

/**
 * Author: Kern
 * Time: 2019/1/4 13:51
 * Description: This is..
 */

public class HardwareHelper {

    public static void setupHwAcceleration(View web, boolean hardware) {

        if (web == null) return;
        if (hardware) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                web.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                //web.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
        } else {
            web.setLayerType(View.LAYER_TYPE_NONE, null);
        }
    }
}
