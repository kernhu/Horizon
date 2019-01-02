package cn.walkpast.core.config;

import android.app.Activity;
import android.graphics.Color;
import android.widget.ProgressBar;

import cn.walkpast.core.R;
import cn.walkpast.core.constant.ProgressStyle;
import cn.walkpast.indicator.CircularLimeIndicator;
import cn.walkpast.indicator.SmoothLimeIndicator;

/**
 * Author: Kern
 * Time: 2019/1/2 9:59
 * Description: This is..
 */

public class ProgressConfig {

    private Activity mActivity;
    private int mTheme;
    private float mHeight;
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

    public float getHeight() {
        return mHeight;
    }

    public ProgressConfig setHeight(float height) {
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

        mIndicator = createIndicator();
        mIndicator.setMinimumHeight((int) getHeight());

        return this;
    }

    private ProgressBar mIndicator;

    public ProgressBar getIndicator() {
        return mIndicator;
    }

    public ProgressBar createIndicator() {

        ProgressBar mProgressBar = null;
        switch (getProgressStyle()) {
            case STYLE_HORIZONTAL_TOP:

                mProgressBar = new SmoothLimeIndicator(getActivity());
                ((SmoothLimeIndicator) mProgressBar).applyStyle(R.style.OrdinaryIndicator);

                break;
            case STYLE_CIRCLE_GRAVITY:

                mProgressBar = new CircularLimeIndicator(getActivity());
                ((CircularLimeIndicator) mProgressBar).setBackgroundColor(Color.YELLOW);

                break;
        }
        return mProgressBar;
    }
}
