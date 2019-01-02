package cn.walkpast.core.theme;

import android.webkit.WebView;

import cn.walkpast.core.constant.Theme;

/**
 * Author: Kern
 * Time: 2019/1/2 20:42
 * Description: This is..
 */

public class ThemeHelper implements ITheme {

    private static ThemeHelper mThemeHelper;

    public ThemeHelper getInstance() {

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

        switch (theme) {

            case THEME_LIGHT:

                //API19，android4.4
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    webView.evaluateJavascript("document.body.style.backgroundColor=\"white\";document.body.style.color=\"black\";", null);
                } else {
                    webView.loadUrl("javascript:document.body.style.backgroundColor=\"#white\";document.body.style.color=\"black\";");
                }
                return true;

            case THEME_DARK:

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    webView.evaluateJavascript("document.body.style.backgroundColor=\"black\";document.body.style.color=\"white\";", null);
                } else {
                    webView.loadUrl("javascript:document.body.style.backgroundColor=\"#black\";document.body.style.color=\"white\";");
                }
                return true;
        }
        return false;
    }
}
