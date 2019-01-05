package cn.walkpast.horizon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import cn.walkpast.core.Horizon;
import cn.walkpast.core.config.CoreConfig;
import cn.walkpast.core.config.DownloadConfig;
import cn.walkpast.core.config.ProgressConfig;
import cn.walkpast.core.constant.NetworkType;
import cn.walkpast.core.constant.ProgressStyle;
import cn.walkpast.core.constant.Strategy;
import cn.walkpast.core.constant.Theme;

/**
 * Author: Kern
 * Time: 2019/1/5 17:23
 * Description: This is..
 */

public class HorizonBaseActivity extends AppCompatActivity {

    public Horizon mHorizon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHorizon = Horizon.getInstance()
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
                .setWebView(new WebView(this));
    }


    public Horizon getHorizon() {
        return mHorizon;
    }
}