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
    private int mBackgroundColor = 0xffFFFFFF;
    private int mProgressColor = 0xff00BE7E;
    private int mHeight = 7;
    private ProgressStyle mProgressStyle = ProgressStyle.STYLE_HORIZONTAL_TOP;

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

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public ProgressConfig setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
        return this;
    }

    public int getProgressColor() {
        return mProgressColor;
    }

    public ProgressConfig setProgressColor(int progressColor) {
        mProgressColor = progressColor;
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
