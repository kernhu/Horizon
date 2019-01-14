package cn.walkpast.horizon.competence;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.walkpast.core.Horizon;
import cn.walkpast.core.client.HorizonClient;
import cn.walkpast.core.config.CoreConfig;
import cn.walkpast.core.config.DownloadConfig;
import cn.walkpast.core.indicator.ProgressConfig;
import cn.walkpast.core.constant.FilterType;
import cn.walkpast.core.constant.NetworkType;
import cn.walkpast.core.constant.ProgressStyle;
import cn.walkpast.core.constant.Strategy;
import cn.walkpast.core.constant.Theme;
import cn.walkpast.horizon.BuildConfig;
import cn.walkpast.horizon.R;
import cn.walkpast.horizon.widget.PopupWindowTools;

/**
 * Author: Kern
 * Time: 2019/1/14 14:20
 * Description: This is..
 */

public class BasicFunActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.basic_fun_icon)
    public ImageView mBfIcon;
    @BindView(R.id.basic_fun_title)
    public TextView mBfTitle;
    @BindView(R.id.basic_fun_menu)
    public ImageView mBfMenu;
    @BindView(R.id.basic_fun_container)
    public FrameLayout mBfContainer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_basic_fun);
        ButterKnife.bind(this);


        Horizon.with(this)
                .setProgressConfig(ProgressConfig
                        .with(this)
                        .setBackgroundColor(R.color.ProgressBackground)
                        .setProgressColor(R.color.ProgressColor)
                        .setHeight(R.dimen.IndicatorHorizontal)
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
                        .setFilterList(FilterType.TYPE_MATCH_HOST, "www.qq.com", "www.bbbb.com", "www.bbbb.com", "www.bbbb.com", "www.bbbb.com")
                        .setStrategy(Strategy.CORE_BOTH_TEXT_IMAGE)
                        .setErrorPage("")
                        .config()
                )
                .setDownloadConfig(DownloadConfig
                        .with(this)
                        .setStoragePath("download")
                        .setNetworkType(NetworkType.TYPE_BOTH_GPRS_WIFI)
                        .config()
                )
                .setHorizonClient(mHorizonClient)
                .setViewContainer(mBfContainer)
                .setWebView(new WebView(this))
                .setOriginalUrl("https://www.hao123.com/")
                .load();


    }


    @OnClick(R.id.basic_fun_menu)
    @Override
    public void onClick(View v) {

        PopupWindowTools
                .getInstance()
                .setActivity(this)
                .setTargetView(mBfMenu)
                .setItems("刷新", "前进", "后退", "无图模式", "有图模式")
                .setItemClickListener(new PopupWindowTools.PopupWindowItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                })
                .show();
    }

    HorizonClient mHorizonClient = new HorizonClient() {

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);

            mBfIcon.setImageBitmap(icon);
        }

        @Override
        public void onReceiveTitle(WebView view, String title) {
            super.onReceiveTitle(view, title);

            mBfTitle.setText(title);
        }
    };
}