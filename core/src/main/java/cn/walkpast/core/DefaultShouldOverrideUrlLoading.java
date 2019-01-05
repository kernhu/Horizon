package cn.walkpast.core;

import android.app.Activity;

import cn.walkpast.core.wakeup.WakeupHelper;

/**
 * Author: Kern
 * Time: 2019/1/5 15:55
 * Description: This is..    // 1、对电话号码的处理；
 * // 2、对短信的处理；
 * // 3、对邮件的处理；
 * // 4、对位置的处理：
 * // 5、对地图的处理：
 * //6.拉活
 * //7.常规
 */

public class DefaultShouldOverrideUrlLoading {

    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";
    private static final String FILE = "file://";

    public static boolean shouldOverrideUrlLoading(Activity activity, String url) {
        if (url.startsWith(HTTP) || url.startsWith(HTTPS) || url.startsWith(FILE)) {

            return false;
        } else {

            return WakeupHelper
                    .getInstance()
                    .setActivity(activity)
                    .setScheme(url)
                    .wakeup();
        }
    }
}
