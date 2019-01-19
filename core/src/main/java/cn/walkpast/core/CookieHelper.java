package cn.walkpast.core;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

/**
 * Author: Kern
 * Time: 2019/1/18 11:38
 * Description: This is..
 */

public class CookieHelper {

//    public static boolean syncCookie(Context context, String url,) {
//
//        CookieManager cookieManager = CookieManager.getInstance();
//        cookieManager.setCookie(url, "website_name=" + spManager.getLong(CSJSharedPreferencesManager.KEY_USER_ID));
//        cookieManager.setCookie(url, "website_token=" + "294539a5631280a2cdbf99f0e906dc21");
//        String newCookie = cookieManager.getCookie(url);
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(context);
//            cookieSyncManager.sync();
//        }
//        return TextUtils.isEmpty(newCookie) ? false : true;
//    }


//    /**
//     * 设置Cookie
//     *
//     * @param url
//     */
//    private void setCookies(String url) {
//        if (!TextUtils.isEmpty(strCookies)) {
//            String arrayCookies[] = strCookies.split(";");
//            if (arrayCookies != null && arrayCookies.length > 0) {
//                for (String cookie : arrayCookies) {
//                    // synCookies(url, cookie);
//                    synCookies(this, url, cookie);
//                }
//            }
//        }
//    }

    /**
     * @param url
     * @return
     */
    public static String getCookie(String url) {
        CookieManager cookieManager = CookieManager.getInstance();
        String cookieStr = cookieManager.getCookie(url);
        return cookieStr;
    }

    /**
     * 同步Cookie
     *
     * @param url
     * @param cookie 格式：uid=21233 如需设置多个，需要多次调用
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void synCookies(String url, String cookie) {
        try {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.setCookie(url, cookie);//cookies是在HttpClient中获得的cookie
            cookieManager.flush();
        } catch (Exception e) {

        }
    }


    /**
     * 设置Cookie
     *
     * @param context
     * @param url
     * @param cookie  格式：uid=21233 如需设置多个，需要多次调用
     */
    public void synCookies(Context context, String url, String cookie) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, cookie + ";Domain=hotspot.******;Path=/");//cookies格式自定义
        CookieSyncManager.getInstance().sync();
    }


    /**
     * 清除Cookie
     *
     * @param context
     */
    public static void removeCookie(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

}
