package cn.walkpast.horizon.competence;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.walkpast.core.CoreConfig;
import cn.walkpast.core.DownloadConfig;
import cn.walkpast.core.Horizon;
import cn.walkpast.core.HorizonClient;
import cn.walkpast.core.constant.NetworkType;
import cn.walkpast.core.constant.Strategy;
import cn.walkpast.core.constant.Theme;
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
                .setCoreConfig(new CoreConfig()
                        .setFontSize(16)
                        .setHardwareAccelerated(true)
                        .setPatternlessEnable(true)
                        .setSavePassword(true)
                        .setWakeupEnable(true)
                        .setAdblockPlusEnable(true)
                        .setTheme(Theme.THEME_LIGHT)
                        .setFilterList(null)
                        .setStrategy(Strategy.CORE_BOTH_TEXT_IMAGE)
                        .build()
                )
                .setDownloadConfig(new DownloadConfig()
                        .setStoragePath("download")
                        .setNetworkType(NetworkType.TYPE_BOTH_4G_WIFI)
                        .build()
                )
                .setHorizonClient(mHorizonClient)
                .setViewContainer(mFrameContainer)
                .setWebView(new WebView(this))
                .setOriginalUrl("https://www.baidu.com")
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

    HorizonClient mHorizonClient = new HorizonClient() {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url) {
            super.onPageStarted(view, url);
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
        public void onDownload(String url, String contentDisposition, String mimetype, long contentLength) {
            super.onDownload(url, contentDisposition, mimetype, contentLength);

        }
    };
}
