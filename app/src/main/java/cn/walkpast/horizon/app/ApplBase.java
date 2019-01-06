package cn.walkpast.horizon.app;

import android.app.Application;

import cn.walkpast.core.config.HorizonConfig;
import cn.walkpast.horizon.BuildConfig;
import cn.walkpast.horizon.R;

/**
 * Author: Kern
 * Time: 2019/1/4 20:32
 * Description: This is..
 */

public class ApplBase extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        HorizonConfig.getInstance()
                .setApplication(this)
                .setToastBgc(R.color.ToastBgc)
                .setToastMsgColor(R.color.TextWhite)
                .setLogSwitch(BuildConfig.DEBUG)
                .build();

    }
}
