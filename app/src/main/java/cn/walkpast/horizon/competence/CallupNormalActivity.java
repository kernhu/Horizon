package cn.walkpast.horizon.competence;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.walkpast.core.Horizon;
import cn.walkpast.core.client.HorizonClient;
import cn.walkpast.core.config.CoreConfig;
import cn.walkpast.core.config.DownloadConfig;
import cn.walkpast.core.constant.FilterType;
import cn.walkpast.core.constant.NetworkType;
import cn.walkpast.core.constant.NotificationType;
import cn.walkpast.core.constant.ProgressStyle;
import cn.walkpast.core.constant.Strategy;
import cn.walkpast.core.constant.Theme;
import cn.walkpast.core.error.BindEventCallback;
import cn.walkpast.core.error.DefaultErrorPage;
import cn.walkpast.core.indicator.ProgressConfig;
import cn.walkpast.horizon.BuildConfig;
import cn.walkpast.horizon.R;
import cn.walkpast.horizon.errorpage.CustomErrorPage;
import cn.walkpast.utils.ToastUtils;

/**
 * Author: Kern
 * Time: 2019/1/15 21:07
 * Description: This is..
 */

public class CallupNormalActivity extends AppCompatActivity {

    @BindView(R.id.title)
    public TextView mTitle;
    @BindView(R.id.subheading)
    public TextView mSubheading;
    @BindView(R.id.frame_container)
    public FrameLayout mFrameContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_normal);
        ButterKnife.bind(this);

        mTitle.setText(getIntent().getStringExtra("title"));


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
                .setHorizonClient(mHorizonClient)
                .setViewContainer(mFrameContainer)
                .setWebView(new WebView(this))
                .setOriginalUrl("file:///android_asset/callup_normal.html")
                .setErrorPage(new DefaultErrorPage()
                        .setContext(this)
                        .setLayout(R.layout.layout_custom_error_page)
                        .setBindEventCallback(new BindEventCallback() {
                                                  @Override
                                                  public void bindEvent(View view) {

                                                      view.findViewById(R.id.custom_error_page_left).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {

                                                              ToastUtils.showShort("检查网络");
                                                          }
                                                      });

                                                      view.findViewById(R.id.custom_error_page_right).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {

                                                              ToastUtils.showShort("重新刷新");
                                                          }
                                                      });

                                                  }
                                              }
                        )
                        .createView()
                )
                .preview();

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Horizon.getInstance().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Horizon.getInstance().onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ///Horizon.getInstance().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Horizon.getInstance().onDestroy();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Horizon.getInstance().onKeyDown(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }

    HorizonClient mHorizonClient = new HorizonClient() {


        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onReceiveTitle(WebView view, String title) {
            super.onReceiveTitle(view, title);

            mSubheading.setText(title);

        }

    };
}
