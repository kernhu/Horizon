package cn.walkpast.core.theme;

import android.webkit.WebView;

/**
 * Author: Kern
 * Time: 2019/1/2 20:43
 * Description: This is..
 */

public interface ITheme {

    boolean injectLight(WebView view);

    boolean injectDark(WebView view);
}
