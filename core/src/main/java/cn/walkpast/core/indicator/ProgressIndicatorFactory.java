package cn.walkpast.core.indicator;

import android.widget.ProgressBar;

/**
 * Author: Kern
 * Time: 2019/1/3 11:38
 * Description: This is..
 */

public class ProgressIndicatorFactory {

    private ProgressConfig mConfig;
    private Indicator mIndicator;

    public static ProgressIndicatorFactory getInstance() {

        return new ProgressIndicatorFactory();
    }

    public ProgressConfig getConfig() {
        return mConfig;
    }

    public ProgressIndicatorFactory setConfig(ProgressConfig config) {
        mConfig = config;
        return this;
    }

    public ProgressBar createIndicator() {

        if (getConfig() == null) {

            return null;
        }

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
