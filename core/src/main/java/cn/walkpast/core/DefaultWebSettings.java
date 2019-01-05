package cn.walkpast.core;

import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import cn.walkpast.core.config.CacheConfig;
import cn.walkpast.core.config.CoreConfig;
import cn.walkpast.core.config.WebSettingConfig;
import cn.walkpast.utils.LogUtils;
import cn.walkpast.utils.NetworkUtils;

/**
 * Author: Kern
 * Time: 2019/1/2 18:06
 * Description: This is..
 */

public class DefaultWebSettings {

    private static String TAG = "DefaultWebSettings";
    private WebView mWebView;
    private CoreConfig mConfig;
    private WebSettings mWebSettings;

    public static DefaultWebSettings getInstance() {

        return new DefaultWebSettings();
    }

    public WebView getWebView() {
        return mWebView;
    }

    public DefaultWebSettings setWebView(WebView webView) {
        mWebView = webView;
        return this;
    }

    public CoreConfig getConfig() {
        return mConfig;
    }

    public DefaultWebSettings setConfig(CoreConfig config) {
        mConfig = config;
        return this;
    }

    public DefaultWebSettings build() {

        mWebSettings = setDefaultWebSettings();

        return this;
    }

    public WebSettings getDefaultSetting() {

        return mWebSettings;
    }

    private WebSettings setDefaultWebSettings() {

        getWebView().setVerticalScrollBarEnabled(false);
        getWebView().setHorizontalScrollBarEnabled(false);
        getWebView().setScrollbarFadingEnabled(true);
        getWebView().setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        WebSettings settings = getWebView().getSettings();
        HttpsTrustManager.allowAllSSL();
        settings.setAllowContentAccess(true);
        settings.setSaveFormData(false);
        settings.setUseWideViewPort(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setBuiltInZoomControls(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setSavePassword(getConfig().isSavePassword());
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            if (getConfig().isHardwareAccelerated()) {
                getWebView().setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (getConfig().isHardwareAccelerated()) {
                getWebView().setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            if (getConfig().isHardwareAccelerated()) {
                getWebView().setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
        }

        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        if (NetworkUtils.isConnected()) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        settings.setSupportMultipleWindows(false);
        settings.setBlockNetworkImage(getConfig().isPatternlessEnable());
        settings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        } else {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        settings.setLoadWithOverviewMode(true);
        settings.setNeedInitialFocus(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setDefaultFontSize(getConfig().getFontSize());
        //settings.setTextZoom(getConfig().getFontSize());
        settings.setMinimumFontSize(8);//min font size
        settings.setGeolocationEnabled(mConfig.isGeolocationEnalbe());
        //
        String dir = CacheConfig.getCachePath(getWebView().getContext());
        //setting database
        settings.setGeolocationDatabasePath(dir);
        settings.setDatabasePath(dir);
        settings.setAppCachePath(dir);
        //
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setUserAgentString(settings
                .getUserAgentString()
                .concat("  ")
                .concat(WebSettingConfig.CORE_PPRV)
                .concat("  ")
                .concat(WebSettingConfig.CORE_PPRV)
                .concat("  ")
                .concat(WebSettingConfig.CORE_VERSION_NAME));

        /// M: Add to disable overscroll mode
        getWebView().setOverScrollMode(View.OVER_SCROLL_NEVER);
        final PackageManager pm = getWebView().getContext().getPackageManager();
        boolean supportsMultiTouch =
                pm.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH)
                        || pm.hasSystemFeature(PackageManager.FEATURE_FAKETOUCH_MULTITOUCH_DISTINCT);
        getWebView().getSettings().setDisplayZoomControls(!supportsMultiTouch);

        //cookie
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(getWebView(), cookieManager.acceptCookie());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(mConfig.isWebContentsDebuggingEnabled());
        }

        LogUtils.d(TAG, "UA--->>>" + settings.getUserAgentString());

        return settings;
    }

}
