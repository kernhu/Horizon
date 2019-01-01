package cn.walkpast.core;

import android.app.Activity;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/1 5:34 PM
 * describe: This is...
 */

public class Horizon implements ILifecycle {

    private static Horizon mHorizon;
    private Activity mActivity;
    private CoreConfig mCoreConfig;
    private DownloadConfig mDownloadConfig;
    private HorizonClient mHorizonClient;
    private WebView mWebView;
    private ViewGroup mViewContainer;
    private String mOriginalUrl;

    public Activity getActivity() {
        return mActivity;
    }

    public Horizon setActivity(Activity activity) {
        mActivity = activity;
        return this;
    }

    public CoreConfig getCoreConfig() {
        return mCoreConfig;
    }

    public Horizon setCoreConfig(CoreConfig coreConfig) {
        mCoreConfig = coreConfig;
        return this;
    }

    public DownloadConfig getDownloadConfig() {
        return mDownloadConfig;
    }

    public Horizon setDownloadConfig(DownloadConfig downloadConfig) {
        mDownloadConfig = downloadConfig;
        return this;
    }

    public HorizonClient getHorizonClient() {
        return mHorizonClient;
    }

    public Horizon setHorizonClient(HorizonClient horizonClient) {
        mHorizonClient = horizonClient;
        return this;
    }

    public WebView getWebView() {
        return mWebView;
    }

    public Horizon setWebView(WebView webView) {
        mWebView = webView;
        return this;
    }

    public ViewGroup getViewContainer() {
        return mViewContainer;
    }

    public Horizon setViewContainer(ViewGroup viewContainer) {
        mViewContainer = viewContainer;
        return this;
    }

    public String getOriginalUrl() {
        return mOriginalUrl;
    }

    public Horizon setOriginalUrl(String originalUrl) {
        mOriginalUrl = originalUrl;
        return this;
    }

    public Horizon load() {

        return this;
    }

    /*******************************************/
    public static Horizon getInstance() {

        if (mHorizon == null) {
            mHorizon = new Horizon();
        }
        return mHorizon;
    }

    /*****************************************/
    @Override
    public void onPause() {

        if (mWebView != null) {
            mWebView.pauseTimers();
            mWebView.onPause();
        }

    }

    @Override
    public void onResume() {

        if (mWebView != null) {
            mWebView.resumeTimers();
            mWebView.onResume();
        }

    }

    @Override
    public void onStop() {

        if (mWebView != null) {
            mWebView.stopLoading();
            mWebView.clearCache(true);
            mWebView.clearHistory();
            mWebView.clearMatches();
            mWebView.clearSslPreferences();
            mWebView.clearAnimation();
            mWebView.clearDisappearingChildren();
        }

    }

    @Override
    public void onDestroy() {

        mCoreConfig = null;
        mDownloadConfig = null;
        mHorizonClient = null;
        mViewContainer = null;
        mWebView = null;

    }
}