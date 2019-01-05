package cn.walkpast.horizon;

import android.app.Application;

import cn.walkpast.core.config.HorizonConfig;

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
