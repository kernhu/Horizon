package cn.walkpast.core.theme;

import android.util.Base64;
import android.webkit.WebView;

import java.io.IOException;
import java.io.InputStream;

import cn.walkpast.core.R;
import cn.walkpast.core.constant.Theme;
import cn.walkpast.utils.HorizonUtils;

/**
 * Author: Kern
 * Time: 2019/1/2 20:42
 * Description: This is..
 */

public class ThemeHelper implements ITheme {

    private static ThemeHelper mThemeHelper;

    public static ThemeHelper getInstance() {

        if (mThemeHelper == null) {
            mThemeHelper = new ThemeHelper();
        }
        return mThemeHelper;
    }


    @Override
    public boolean injectLight(WebView view) {

        return injectJs(view, Theme.THEME_LIGHT);
    }

    @Override
    public boolean injectDark(WebView view) {

        return injectJs(view, Theme.THEME_DARK);
    }

    /**
     * inject js to switch theme
     *
     * @param webView
     */
    private boolean injectJs(WebView webView, Theme theme) {

        webView.loadUrl("javascript:(function() {" + "var parent = document.getElementsByTagName('head').item(0);" + "var style = document.createElement('style');" + "style.type = 'text/css';" + "style.innerHTML = window.atob('" + getCode(theme) + "');" + "parent.appendChild(style)" + "})();");

        return false;
    }


    private String getCode(Theme theme) {

        InputStream is = HorizonUtils.getApp().getResources().openRawResource(theme == Theme.THEME_DARK ? R.raw.theme_dark : R.raw.theme_light);
        byte[] buffer = new byte[0];
        try {
            buffer = new byte[is.available()];
            is.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Base64.encodeToString(buffer, Base64.NO_WRAP);
    }
}
