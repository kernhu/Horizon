package cn.walkpast.horizon.competence;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import cn.walkpast.horizon.R;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/1 4:49 PM
 * describe: This is...
 */

public class BasicFunFragmentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_basic_fun_fragment);
        ButterKnife.bind(this);

//        Horizon.with(this)
//                .setProgressConfig(ProgressConfig
//                        .with(this)
//                        .setBackgroundColor(R.color.ProgressBackground)
//                        .setProgressColor(R.color.ProgressColor)
//                        .setHeight(R.dimen.IndicatorHorizontal)
//                        .setProgressStyle(ProgressStyle.STYLE_HORIZONTAL_TOP)
//                        .config()
//                )
//                .setCoreConfig(CoreConfig
//                        .with(this)
//                        .setFontSize(16)
//                        .setHardwareAccelerated(true)
//                        .setPatternlessEnable(false)
//                        .setSavePassword(true)
//                        .setWakeupEnable(true)
//                        .setAdblockPlusEnable(true)
//                        .setGeolocationEnalbe(true)
//                        .setThemeEnable(true)
//                        .setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
//                        .setTheme(Theme.THEME_LIGHT)
//                        .setFilterList(FilterType.TYPE_MATCH_HOST, "http://www.walkpast.cn/","www.qq.com", "www.bbbb.com", "www.bbbb.com", "www.bbbb.com", "www.bbbb.com")
//                        .setStrategy(Strategy.CORE_BOTH_TEXT_IMAGE)
//                        .config()
//                )
//                .setDownloadConfig(DownloadConfig
//                        .with(this)
//                        .setStoragePath("/download/horizon/")
//                        .setNetworkType(NetworkType.NETWORK_MOBILE_AND_WIFI)
//                        .setNotificationType(NotificationType.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//                        .setTooltipEnable(true)
//                        .config()
//                )
//                .setCaptureStrategy(CaptureStrategy.NEVER)
//                .setHorizonClient(mHorizonClient)
//                .setViewContainer(mFrameContainer)
//                .setWebView(new WebView(this))
//                .setOriginalUrl("https://www.hao123.com/")
////                .setErrorPage(new DefaultErrorPage()
////                        .setContext(this)
////                        .setLayout(R.layout.layout_default_error_page)
////                        .setBindEventCallback(new BindEventCallback() {
////                                                  @Override
////                                                  public void bindEvent(View view) {
////
////                                                      view.findViewById(R.id.default_error_page_check).setOnClickListener(new View.OnClickListener() {
////                                                          @Override
////                                                          public void onClick(View v) {
////
////                                                              ToastUtils.showShort("检查网络");
////                                                          }
////                                                      });
////
////                                                      view.findViewById(R.id.default_error_page_reload).setOnClickListener(new View.OnClickListener() {
////                                                          @Override
////                                                          public void onClick(View v) {
////
////                                                              ToastUtils.showShort("重新刷新");
////                                                          }
////                                                      });
////
////                                                  }
////                                              }
////                        )
////                        .createView()
////                )
//                .preview()
//                .loadUrl("https://www.hao123.com/");
//

    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        Horizon.with(this).onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Horizon.with(this).onResume();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Horizon.with(this).onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Horizon.with(this).onDestroy();
//    }
//
//    @Override
//    public void onTrimMemory(int level) {
//        super.onTrimMemory(level);
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Horizon.with(this).onKeyDown(keyCode, event);
//        return super.onKeyDown(keyCode, event);
//    }
//
//    HorizonClient mHorizonClient = new HorizonClient() {
//
//        @Override
//        public void onReceivedIcon(WebView view, Bitmap icon) {
//            super.onReceivedIcon(view, icon);
//        }
//
//        @Override
//        public void onReceiveTitle(WebView view, String title) {
//            super.onReceiveTitle(view, title);
//
//
//
//        }
//
//    };
}
