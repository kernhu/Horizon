package cn.walkpast.core.indicator;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

/**
 * Author: Kern
 * Time: 2019/1/11 20:38
 * Description: This is..
 */

public class CircularIndicator implements Indicator {

    private ProgressConfig mConfig;
    private ProgressBar mProgressBar;

    public CircularIndicator(ProgressConfig config) {
        mConfig = config;
    }

    @Override
    public ProgressBar createProgress() {

        mProgressBar = new ProgressBar(mConfig.getActivity(), null, android.R.attr.progressBarStyleHorizontal);
        ProgressBarUtils.getInstance().setColors(mProgressBar, mConfig.getBackgroundColor(), mConfig.getProgressColor());
        mProgressBar.setIndeterminate(false);//must false
        mProgressBar.setMax(100);
        mProgressBar.setProgress(0);
        mProgressBar.setSecondaryProgress(0);
        mProgressBar.setMinimumHeight(mConfig.getHeight());
        mProgressBar.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, mConfig.getHeight(), Gravity.TOP));

        return mProgressBar;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void setProgress(int progress) {

        if (mProgressBar != null) {
            mProgressBar.setProgress(progress, false);
        }
    }

    @Override
    public void setVisible() {

        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setGone() {

        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
