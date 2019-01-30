package cn.walkpast.horizon.competence;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
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
import cn.walkpast.utils.NetworkUtils;

/**
 * Author: Kern
 * Time: 2019/1/29 18:12
 * Description: This is..
 */

public class BasicFunFirstFragment extends Fragment {

    @BindView(R.id.first_icon)
    public ImageView mFirstIcon;
    @BindView(R.id.first_title)
    public TextView mFirstTitle;
    @BindView(R.id.first_menu)
    public ImageView mFirstMenu;
    @BindView(R.id.first_container)
    public FrameLayout mFirstContainer;

    private Horizon mHorizon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_basic_fun_first, null, false);

        ButterKnife.bind(this, view);


        mHorizon = Horizon.with(this)
                .setTag(BasicFunActivity.class.toString())
                .setProgressConfig(ProgressConfig
                        .with(getActivity())
                        .setBackgroundColor(R.color.ProgressBackground)
                        .setProgressColor(R.color.ProgressColor)
                        .setHeight(R.dimen.IndicatorHorizontal)
                        .setProgressStyle(ProgressStyle.STYLE_HORIZONTAL_TOP)
                        .config()
                )
                .setCoreConfig(CoreConfig
                        .with(getActivity())
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
                        .with(getActivity())
                        .setStoragePath("/download/")
                        .setNetworkType(NetworkType.NETWORK_MOBILE_AND_WIFI)
                        .setNotificationType(NotificationType.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setTooltipEnable(true)
                        .config()
                )
                .setCaptureStrategy(CaptureStrategy.START_FINISH)
                .setHorizonClient(mHorizonClient)
                .setViewContainer(mFirstContainer)
                .setWebView(new WebView(getActivity()))
                .setOriginalUrl("https://www.taobao.com/")
                .setErrorPage(new DefaultErrorPage()
                        .setContext(getActivity())
                        .setLayout(R.layout.layout_default_error_page)
                        .setBindEventCallback(new BindEventCallback() {
                                                  @Override
                                                  public void bindEvent(View view) {
                                                      view.findViewById(R.id.default_error_page_check).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {
                                                              NetworkUtils.settingNetwork(getActivity());
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

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mHorizon != null) {
            mHorizon.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mHorizon != null) {
            mHorizon.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mHorizon != null) {
            mHorizon.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHorizon != null) {
            mHorizon.onDestroy();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            if (mHorizon != null) {
                mHorizon.onPause();
            }
        } else {
            if (mHorizon != null) {
                mHorizon.onResume();
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            if (mHorizon != null) {
                mHorizon.onResume();
            }
        } else {
            if (mHorizon != null) {
                mHorizon.onPause();
            }
        }
    }

    HorizonClient mHorizonClient = new HorizonClient() {

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);

            mFirstIcon.setImageBitmap(icon);
        }

        @Override
        public void onReceiveTitle(WebView view, String title) {
            super.onReceiveTitle(view, title);

            mFirstTitle.setText(title);
        }

    };
}
