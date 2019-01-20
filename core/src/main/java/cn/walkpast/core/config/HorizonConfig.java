package cn.walkpast.core.config;

import android.app.Application;
import android.view.View;

import cn.walkpast.utils.HorizonUtils;
import cn.walkpast.utils.LogUtils;
import cn.walkpast.utils.ToastUtils;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/4 11:22 PM
 * describe: This is...
 */

public class HorizonConfig {


    private static HorizonConfig mHorizonConfig;

    private Application mApplication;
    private int mToastBgc;
    private int mToastMsgColor;
    private boolean mLogSwitch;
    private View mErrorPage;


    public HorizonConfig(Application application) {
        mApplication = application;
    }

    public static HorizonConfig with(Application application) {

        if (mHorizonConfig == null) {
            mHorizonConfig = new HorizonConfig(application);
        }
        return mHorizonConfig;
    }

    public Application getApplication() {
        return mApplication;
    }


    public int getToastBgc() {
        return mToastBgc;
    }

    public HorizonConfig setToastBgc(int toastBgc) {
        mToastBgc = toastBgc;
        return this;
    }

    public int getToastMsgColor() {
        return mToastMsgColor;
    }

    public HorizonConfig setToastMsgColor(int toastMsgColor) {
        mToastMsgColor = toastMsgColor;
        return this;
    }

    public boolean isLogSwitch() {
        return mLogSwitch;
    }

    public HorizonConfig setLogSwitch(boolean logSwitch) {
        mLogSwitch = logSwitch;
        return this;
    }

    public View getErrorPage() {
        return mErrorPage;
    }

    public HorizonConfig setErrorPage(View errorPage) {
        mErrorPage = errorPage;
        return this;
    }

    public void config() {

        //初始化工具类
        HorizonUtils.init(getApplication());
        //Toast配置
        ToastUtils.setBgColor(getApplication().getResources().getColor(getToastBgc()));
        ToastUtils.setMsgColor(getApplication().getResources().getColor(getToastMsgColor()));
        //Log日志输出开光
        LogUtils.getConfig().setLogSwitch(isLogSwitch());

    }
}
