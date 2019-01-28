package cn.walkpast.core;

import java.util.Map;

/**
 * Author: Kern
 * Time: 2019/1/28 10:03
 * Description: This is..
 */

public interface IHorizon {

    void loadUrl(String loadUrl);

    void loadUrl(String url, Map<String, String> additionalHttpHeaders);

    void loadData(String data, String mimeType, String encoding);

    void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl);

    void sysnCapture();

    boolean canGoBack();

    boolean canGoForward();

    void goBack();

    void goForward();

    void reload();

    void stopLoading();

    void resumeTimers();

    void addJavascriptInterface(Object object, String name);

    void clearHistory();

}
