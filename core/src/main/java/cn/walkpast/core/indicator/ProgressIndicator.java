package cn.walkpast.core.indicator;

import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import cn.walkpast.core.config.ProgressConfig;

/**
 * Author: Kern
 * Time: 2019/1/3 11:38
 * Description: This is..
 */

public class ProgressIndicator {

    private ProgressConfig mConfig;
    private ProgressBar mProgressBar;

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

                mProgressBar = new ProgressBar(getConfig().getActivity());
                mProgressBar.setProgressDrawable(getConfig().getActivity().getResources().getDrawable(android.R.drawable.progress_horizontal));
                mProgressBar.setIndeterminateDrawable(getConfig().getActivity().getResources().getDrawable(android.R.drawable.progress_indeterminate_horizontal));
                mProgressBar.setIndeterminate(false);
                mProgressBar.setMinimumHeight(getConfig().getHeight());
                mProgressBar.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getConfig().getHeight(), Gravity.TOP));

                break;
            case STYLE_CIRCLE_GRAVITY:

                mProgressBar = new ProgressBar(getConfig().getActivity());
                mProgressBar.setProgressDrawable(getConfig().getActivity().getResources().getDrawable(android.R.drawable.progress_horizontal));
                mProgressBar.setIndeterminateDrawable(getConfig().getActivity().getResources().getDrawable(android.R.drawable.progress_indeterminate_horizontal));
                mProgressBar.setIndeterminate(false);
                mProgressBar.setMinimumHeight(getConfig().getHeight());
                mProgressBar.setLayoutParams(new FrameLayout.LayoutParams(getConfig().getHeight(), getConfig().getHeight(), Gravity.CENTER));

                break;
        }
        return mProgressBar;
    }
}
