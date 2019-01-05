package cn.walkpast.horizon.competence;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.walkpast.core.Horizon;
import cn.walkpast.core.client.HorizonClient;
import cn.walkpast.core.config.CoreConfig;
import cn.walkpast.core.config.DownloadConfig;
import cn.walkpast.core.config.ProgressConfig;
import cn.walkpast.core.constant.NetworkType;
import cn.walkpast.core.constant.ProgressStyle;
import cn.walkpast.core.constant.Strategy;
import cn.walkpast.core.constant.Theme;
import cn.walkpast.horizon.BuildConfig;
import cn.walkpast.horizon.R;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/1 4:49 PM
 * describe: This is...
 */

public class NormalActivity extends AppCompatActivity {

    @BindView(R.id.frame_container)
    public FrameLayout mFrameContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_normal);
        ButterKnife.bind(this);

        Horizon.getInstance()
                .setActivity(this)
                .setProgressConfig(new ProgressConfig(this)
                        .setBackgroundColor(getResources().getColor(R.color.ProgressBackground))
                        .setProgressColor(getResources().getColor(R.color.ProgressColor))
                        .setHeight(getResources().getDimensionPixelSize(R.dimen.IndicatorHorizontal))
                        .setProgressStyle(ProgressStyle.STYLE_HORIZONTAL_TOP)
                        .build()
                )
                .setCoreConfig(new CoreConfig()
                        .setFontSize(16)
                        .setHardwareAccelerated(true)
                        .setPatternlessEnable(false)
                        .setSavePassword(true)
                        .setWakeupEnable(true)
                        .setAdblockPlusEnable(true)
                        .setGeolocationEnalbe(true)
                        .setThemeEnable(true)
                        .setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
                        .setTheme(Theme.THEME_LIGHT)
                        .setFilterList("www.bbbb.com", "www.bbbb.com", "www.bbbb.com", "www.bbbb.com", "www.bbbb.com")
                        .setStrategy(Strategy.CORE_BOTH_TEXT_IMAGE)
                        .setErrorPage("")
                        .build()
                )
                .setDownloadConfig(new DownloadConfig()
                        .setStoragePath("download")
                        .setNetworkType(NetworkType.TYPE_BOTH_GPRS_WIFI)
                        .build()
                )
                .setHorizonClient(mHorizonClient)
                .setViewContainer(mFrameContainer)
                .setWebView(new WebView(this))
                .setOriginalUrl("https://www.hao123.com/")
                .load();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Horizon.getInstance().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Horizon.getInstance().onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Horizon.getInstance().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Horizon.getInstance().onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Horizon.getInstance().onKeyDown(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }

    HorizonClient mHorizonClient = new HorizonClient() {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onReceiveTitle(WebView view, String title) {
            super.onReceiveTitle(view, title);
        }

        @Override
        public boolean onJSCallback(String scheme) {
            return super.onJSCallback(scheme);
        }

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            super.onDownloadStart(url, userAgent, contentDisposition, mimetype, contentLength);

        }
    };
}
