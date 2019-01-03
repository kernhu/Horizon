package cn.walkpast.core.config;

import android.app.Activity;
import android.widget.ProgressBar;

import cn.walkpast.core.constant.ProgressStyle;
import cn.walkpast.core.indicator.ProgressIndicator;

/**
 * Author: Kern
 * Time: 2019/1/2 9:59
 * Description: This is..
 */

public class ProgressConfig {

    private Activity mActivity;
    private int mTheme;
    private int mHeight;
    private ProgressStyle mProgressStyle;


    public ProgressConfig(Activity activity) {
        mActivity = activity;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public ProgressConfig setActivity(Activity activity) {
        mActivity = activity;
        return this;
    }

    public int getTheme() {
        return mTheme;
    }

    public ProgressConfig setTheme(int theme) {
        mTheme = theme;
        return this;
    }

    public int getHeight() {
        return mHeight;
    }

    public ProgressConfig setHeight(int height) {
        mHeight = height;
        return this;
    }

    public ProgressStyle getProgressStyle() {
        return mProgressStyle;
    }

    public ProgressConfig setProgressStyle(ProgressStyle progressStyle) {
        mProgressStyle = progressStyle;
        return this;
    }


    public ProgressConfig build() {

        mProgressBar = ProgressIndicator.getInstance()
                .setConfig(this)
                .createIndicator();

        return this;
    }

    private ProgressBar mProgressBar;

    public ProgressBar getIndicator() {
        return mProgressBar;
    }


}
