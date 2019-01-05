package cn.walkpast.core;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

import java.io.File;

import cn.walkpast.core.client.HorizonClient;
import cn.walkpast.core.config.CacheConfig;
import cn.walkpast.core.config.CoreConfig;
import cn.walkpast.core.config.DownloadConfig;
import cn.walkpast.core.config.ProgressConfig;
import cn.walkpast.utils.LogUtils;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/1 5:34 PM
 * describe: This is...
 */

public class Horizon implements ILifecycle, View.OnKeyListener {

    private static String TAG = "Horizon";
    private static Horizon mHorizon;
    private Activity mActivity;
    private CoreConfig mCoreConfig;
    private DownloadConfig mDownloadConfig;
    private ProgressConfig mProgressConfig;
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

    public ProgressConfig getProgressConfig() {
        return mProgressConfig;
    }

    public Horizon setProgressConfig(ProgressConfig progressConfig) {
        mProgressConfig = progressConfig;
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

        //Ⅰ
        if (getWebView() == null) {
            mWebView = new WebView(getActivity());
        }
        try {
            getWebView().loadUrl(getOriginalUrl());
        } catch (Exception e) {
            LogUtils.e(TAG, "horizon error: webview is exception");
        }

        //Ⅱ
        DefaultWebSettings.getInstance()
                .setConfig(getCoreConfig())
                .setWebView(getWebView())
                .build();
        //Ⅲ
        getWebView().setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        getViewContainer().addView(getWebView());
        //Ⅳ
        getViewContainer().addView(mProgressConfig.getIndicator());


        getWebView().setWebChromeClient(new HorizonWebChromeClient(this));

        getWebView().setWebViewClient(new HorizonWebViewClient(this));

        getWebView().setDownloadListener(new HorizonDownloadFileListener(this));

        getWebView().setOnLongClickListener(new HorizonOnLongClickListener(this));

        getWebView().setOnKeyListener(this);

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
        getActivity().deleteDatabase("webview.db");
        getActivity().deleteDatabase("webviewCache.db");
        getActivity().deleteDatabase("horizon_core_cache.db");

        String mWebviewCacheDir = CacheConfig.getCachePath(getActivity());
        if (new File(mWebviewCacheDir).exists()) {
            getActivity().deleteFile(mWebviewCacheDir);
        }
        System.gc();
        System.runFinalization();
    }

    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.destroy();
        }
        mCoreConfig = null;
        mDownloadConfig = null;
        mHorizonClient = null;
        mViewContainer = null;
        mWebView = null;

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //mWebView.onKeyDown(keyCode, event);
        return false;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK && getWebView().canGoBack()) {
                getWebView().goBack();
                return true;
            }
        }
        return false;
    }
}
