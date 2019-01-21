package cn.walkpast.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.walkpast.core.client.HorizonClient;
import cn.walkpast.core.config.CacheConfig;
import cn.walkpast.core.config.CoreConfig;
import cn.walkpast.core.config.DownloadConfig;
import cn.walkpast.core.constant.CaptureStrategy;
import cn.walkpast.core.constant.EventPoint;
import cn.walkpast.core.error.BindEventCallback;
import cn.walkpast.core.error.DefaultErrorPage;
import cn.walkpast.core.indicator.ProgressConfig;
import cn.walkpast.utils.LogUtils;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/1 5:34 PM
 * describe: This is...
 */

public class Horizon implements ILifecycle, View.OnKeyListener, View.OnTouchListener {

    private static String TAG = "Horizon";

    private Map<Object, Horizon> mHorizonMap = new HashMap<>();

    private Activity mActivity;
    private Context mContext;
    private Fragment mFragment;

    private CoreConfig mCoreConfig = null;
    private DownloadConfig mDownloadConfig = null;
    private ProgressConfig mProgressConfig = null;
    private HorizonClient mHorizonClient;

    private CaptureStrategy mCaptureStrategy = CaptureStrategy.NEVER;
    private WebView mWebView;
    private ViewGroup mViewContainer;
    private String mOriginalUrl;
    private View mErrorPage;
    private GestureDetector mGestureDetector;

    /*********************** 构造方法 ***********************/
    public Horizon(Activity activity) {
        this.mActivity = activity;
        this.mHorizonMap.put(activity, this);

        this.mWebView = new WebView(activity);
    }

    public Horizon(Context context) {

        this.mContext = context;
        this.mHorizonMap.put(context, this);

        this.mWebView = new WebView(context);
    }

    public Horizon(Fragment fragment) {
        this.mFragment = fragment;
        this.mHorizonMap.put(fragment, this);

        this.mWebView = new WebView(fragment.getActivity());
    }

    /*********************** 实例化Horizon ***********************/
    public static Horizon with(Activity activity) {

        return new Horizon(activity);
    }

    public static Horizon with(Context context) {

        return new Horizon(context);
    }

    public static Horizon with(Fragment fragment) {

        return new Horizon(fragment);
    }

    /*********************** 参数get and set ***********************/
    public Activity getActivity() {
        return mActivity;
    }

    public Context getContext() {
        return mContext;
    }

    public Fragment getFragment() {
        return mFragment;
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

    public CaptureStrategy getCaptureStrategy() {
        return mCaptureStrategy;
    }

    public Horizon setCaptureStrategy(CaptureStrategy captureStrategy) {
        mCaptureStrategy = captureStrategy;
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

    public Horizon setOriginalUrl(String originalUrl) {
        mOriginalUrl = originalUrl;
        return this;
    }

    public String getOriginalUr() {
        return mOriginalUrl;
    }

    public View getErrorPage() {
        return mErrorPage;
    }

    public Horizon setErrorPage(View errorPage) {
        mErrorPage = errorPage;
        return this;
    }

    /*********************** 加载 ***********************/
    public Horizon preview() {

        if (getViewContainer() == null) {
            throw new NullPointerException("ViewContainer can't be null,because horizon need a view container for adding webview.");
        }
        // Ⅰ
        DefaultWebSettings.getInstance()
                .setConfig(getCoreConfig() != null ? getCoreConfig() : new CoreConfig(getActivity()).config())
                .setWebView(getWebView())
                .build();
        //
        if (getErrorPage() == null) {
            setErrorPage(new DefaultErrorPage()
                    .setContext(getActivity())
                    .setLayout(R.layout.layout_default_error_page)
                    .setBindEventCallback(mBindEventCallback)
                    .createView());
        }

        // Ⅱ
        getWebView().setWebChromeClient(new HorizonWebChromeClient(this));
        getWebView().setWebViewClient(new HorizonWebViewClient(this));
        // Ⅲ
        loadUrl(getOriginalUr());
        // Ⅳ
        getWebView().setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        // Ⅴ
        getViewContainer().addView(getWebView());
        getWebView().setDownloadListener(new HorizonDownloadFileListener(this));
        getWebView().setOnLongClickListener(new HorizonOnLongClickListener(this));
        getWebView().setOnKeyListener(this);
        getWebView().setOnTouchListener(this);
        mGestureDetector = new GestureDetector(getActivity(), mSimpleOnGestureListener);
        getViewContainer().addView(mProgressConfig.getIndicator());

        return this;
    }


    /**
     *
     */
    BindEventCallback mBindEventCallback = new BindEventCallback() {
        @Override
        public void bindEvent(View view) {

            view.findViewById(R.id.default_error_page_check).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getActivity().startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

                }
            });

            view.findViewById(R.id.default_error_page_reload).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getWebView().reload();

                }
            });
        }
    };

    public void loadUrl(String loadUrl) {
        if (!TextUtils.isEmpty(loadUrl)) {
            if (getWebView() != null) {
                try {
                    getWebView().loadUrl(loadUrl);
                } catch (Exception e) {
                    LogUtils.e(TAG, "horizon error: webview is exception-->>:" + e.toString());
                }
            }
        }
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
    public void onTrimMemory(int level) {

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

    /*****************************************/
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return mGestureDetector.onTouchEvent(event);
    }

    GestureDetector.SimpleOnGestureListener mSimpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);

            EventPoint.downX = (int) e.getX();
            EventPoint.downY = (int) e.getY();
        }
    };
    /*****************************************/
}
