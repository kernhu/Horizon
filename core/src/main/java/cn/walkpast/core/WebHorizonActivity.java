package cn.walkpast.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.walkpast.core.config.CoreConfig;
import cn.walkpast.core.config.DownloadConfig;
import cn.walkpast.core.constant.CaptureStrategy;
import cn.walkpast.core.constant.NetworkType;
import cn.walkpast.core.constant.NotificationType;
import cn.walkpast.core.constant.ProgressStyle;
import cn.walkpast.core.constant.Strategy;
import cn.walkpast.core.constant.Theme;
import cn.walkpast.core.indicator.ProgressConfig;

/**
 * Author: Kern
 * Time: 2019/1/27 11:29
 * Description: This is..
 */

public class WebHorizonActivity extends AppCompatActivity {


    private Horizon mHorizon;

    public Horizon getHorizon() {
        return mHorizon;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHorizon = Horizon.with(this)
                .setTag("horizon_1")
                .setProgressConfig(ProgressConfig
                        .with(this)
                        .setBackgroundColor(R.color.default_progress_background)
                        .setProgressColor(R.color.default_progress_color)
                        .setHeight(R.dimen.default_progress_height)
                        .setProgressStyle(ProgressStyle.STYLE_HORIZONTAL_TOP)
                        .config()
                )
                .setCoreConfig(CoreConfig
                        .with(this)
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
                        .setStrategy(Strategy.CORE_BOTH_TEXT_IMAGE)
                        .config()
                )
                .setDownloadConfig(DownloadConfig
                        .with(this)
                        .setStoragePath("download")
                        .setNetworkType(NetworkType.NETWORK_MOBILE_AND_WIFI)
                        .setNotificationType(NotificationType.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setTooltipEnable(true)
                        .config()
                )
                .setCaptureStrategy(CaptureStrategy.START_FINISH)
                .setWebView(null);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (mHorizon != null) {
            mHorizon.onTrimMemory(level);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mHorizon != null) {
            mHorizon.onResume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mHorizon != null) {
            mHorizon.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mHorizon != null) {
            mHorizon.onStop();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHorizon != null) {
            mHorizon.onDestroy();
        }
    }

}
