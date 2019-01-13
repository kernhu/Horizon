package cn.walkpast.core.indicator;

import android.widget.ProgressBar;

import cn.walkpast.core.config.ProgressConfig;

/**
 * Author: Kern
 * Time: 2019/1/3 11:38
 * Description: This is..
 */

public class ProgressIndicator {

    private ProgressConfig mConfig;
    private Indicator mIndicator;

    public static ProgressIndicator getInstance() {

        return new ProgressIndicator();
    }

    public ProgressConfig getConfig() {
        return mConfig;
    }

    public ProgressIndicator setConfig(ProgressConfig config) {
        mConfig = config;
        return this;
    }

    public ProgressBar createIndicator() {

        switch (getConfig().getProgressStyle()) {

            case STYLE_HORIZONTAL_TOP:

                mIndicator = new HorizontalIndicator(getConfig());

                break;
            case STYLE_CIRCLE_GRAVITY:

                mIndicator = new CircularIndicator(getConfig());

                break;
        }
        return mIndicator.createProgress();
    }

    public Indicator getIndicator() {
        return mIndicator;
    }
}
