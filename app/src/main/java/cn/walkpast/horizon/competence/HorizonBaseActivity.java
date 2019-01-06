package cn.walkpast.horizon.competence;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import cn.walkpast.core.Horizon;
import cn.walkpast.core.config.CoreConfig;
import cn.walkpast.core.config.DownloadConfig;
import cn.walkpast.core.config.ProgressConfig;
import cn.walkpast.core.constant.FilterType;
import cn.walkpast.core.constant.NetworkType;
import cn.walkpast.core.constant.ProgressStyle;
import cn.walkpast.core.constant.Strategy;
import cn.walkpast.core.constant.Theme;
import cn.walkpast.horizon.BuildConfig;
import cn.walkpast.horizon.R;

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
                .setProgressConfig(ProgressConfig.getInstance()
                        .setActivity(this)
                        .setBackgroundColor(getResources().getColor(R.color.ProgressBackground))
                        .setProgressColor(getResources().getColor(R.color.ProgressColor))
                        .setHeight(getResources().getDimensionPixelSize(R.dimen.IndicatorHorizontal))
                        .setProgressStyle(ProgressStyle.STYLE_HORIZONTAL_TOP)
                        .build()
                )
                .setCoreConfig(CoreConfig.getInstance()
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
                        .setFilterList(FilterType.TYPE_MATCH_HOST, "www.bbbb.com", "www.bbbb.com", "www.bbbb.com", "www.bbbb.com", "www.bbbb.com")
                        .setStrategy(Strategy.CORE_BOTH_TEXT_IMAGE)
                        .setErrorPage("")
                        .build()
                );
    }


    public Horizon getHorizon() {
        return mHorizon;
    }
}
