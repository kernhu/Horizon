package cn.walkpast.horizon.competence;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.walkpast.core.Horizon;
import cn.walkpast.core.config.CoreConfig;
import cn.walkpast.core.error.BindEventCallback;
import cn.walkpast.core.error.DefaultErrorPage;
import cn.walkpast.core.indicator.ProgressConfig;
import cn.walkpast.core.constant.FilterType;
import cn.walkpast.core.constant.ProgressStyle;
import cn.walkpast.core.constant.Strategy;
import cn.walkpast.core.constant.Theme;
import cn.walkpast.horizon.BuildConfig;
import cn.walkpast.horizon.R;
import cn.walkpast.utils.ToastUtils;

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

        mHorizon = Horizon.with(this)
                .setProgressConfig(ProgressConfig.with(this)
                        .setBackgroundColor(R.color.ProgressBackground)
                        .setProgressColor(R.color.ProgressColor)
                        .setHeight(R.dimen.IndicatorHorizontal)
                        .setProgressStyle(ProgressStyle.STYLE_HORIZONTAL_TOP)
                        .config()
                )
                .setCoreConfig(CoreConfig.with(this)
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
                        .config()
                )
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
                );
    }


    public Horizon getHorizon() {
        return mHorizon;
    }
}
