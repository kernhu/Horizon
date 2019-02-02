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
import cn.walkpast.core.constant.CaptureStrategy;
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
import cn.walkpast.horizon.widget.PopupWindowTools;
import cn.walkpast.utils.NetworkUtils;

/**
 * Author: Kern
 * Time: 2019/1/29 14:41
 * Description: This is..
 */

public class FileUploadActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.file_upload_title)
    public TextView mFileUploadTitle;
    @BindView(R.id.file_upload_container)
    public FrameLayout mFileUploadContainer;
    @BindView(R.id.file_upload_icon)
    public ImageView mFileUploadIcon;
    @BindView(R.id.file_upload_menu)
    public ImageView mFileUploadMenu;

    private Horizon mHorizon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file_upload);
        ButterKnife.bind(this);

        mHorizon = Horizon.with(this)
                .setTag(BasicFunActivity.class.toString())
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
                        .setFilterList(FilterType.TYPE_CONTAINS_URL, "http://www.walkpast.cn/", "www.123.com", "www.126.com", "www.qq.com", "www.wechat.com")
                        .setStrategy(Strategy.CORE_BOTH_TEXT_IMAGE)
                        .config()
                )
                .setDownloadConfig(DownloadConfig
                        .with(this)
                        .setStoragePath("/download/")
                        .setNetworkType(NetworkType.NETWORK_MOBILE_AND_WIFI)
                        .setNotificationType(NotificationType.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setTooltipEnable(true)
                        .config()
                )
                .setCaptureStrategy(CaptureStrategy.START_FINISH)
                .setHorizonClient(mHorizonClient)
                .setViewContainer(mFileUploadContainer)
                .setWebView(new WebView(this))
                .setOriginalUrl("https://www.hao123.com/")
                .setErrorPage(new DefaultErrorPage()
                        .setContext(this)
                        .setLayout(R.layout.layout_default_error_page)
                        .setBindEventCallback(new BindEventCallback() {
                                                  @Override
                                                  public void bindEvent(View view) {
                                                      view.findViewById(R.id.default_error_page_check).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {
                                                              NetworkUtils.settingNetwork(FileUploadActivity.this);
                                                          }
                                                      });
                                                      view.findViewById(R.id.default_error_page_reload).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {
                                                              mHorizon.reload();
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

    @OnClick(R.id.basic_fun_menu)
    @Override
    public void onClick(View v) {

        PopupWindowTools
                .getInstance()
                .setActivity(this)
                .setTargetView(mFileUploadMenu)
                .setItems("刷新", "前进", "后退", "无图模式", "有图模式")
                .setItemClickListener(new PopupWindowTools.PopupWindowItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        /**
                         *
                         **/

                    }
                })
                .show();
    }

    HorizonClient mHorizonClient = new HorizonClient() {

        @Override
        public void onReceiveTitle(WebView view, String title) {
            super.onReceiveTitle(view, title);

            mFileUploadTitle.setText(title);
        }

    };
}
