package cn.walkpast.core.indicator;

import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
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

                mProgressBar = new ProgressBar(getConfig().getActivity(), null, android.R.attr.progressBarStyleHorizontal);
                setColors(mProgressBar, mConfig.getBackgroundColor(), mConfig.getProgressColor());
                mProgressBar.setIndeterminate(false);//must false
                mProgressBar.setMax(100);
                mProgressBar.setProgress(0);
                mProgressBar.setSecondaryProgress(0);
                mProgressBar.setMinimumHeight(getConfig().getHeight());
                mProgressBar.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getConfig().getHeight(), Gravity.TOP));

                break;
            case STYLE_CIRCLE_GRAVITY:

                mProgressBar = new ProgressBar(getConfig().getActivity(), null, android.R.attr.progressBarStyleHorizontal);
                 setColors(mProgressBar, mConfig.getBackgroundColor(), mConfig.getProgressColor());
                mProgressBar.setIndeterminate(false);
                mProgressBar.setMax(100);
                mProgressBar.setProgress(0);
                mProgressBar.setSecondaryProgress(0);
                mProgressBar.setMinimumHeight(getConfig().getHeight());
                mProgressBar.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getConfig().getHeight(), Gravity.TOP));

                break;
        }
        return mProgressBar;
    }

    /**
     * @param progressBar
     * @param backgroundColor bgColor blue
     * @param progressColor   progressColor red
     */
    public void setColors(ProgressBar progressBar, int backgroundColor, int progressColor) {
        //Background
        ClipDrawable bgClipDrawable = new ClipDrawable(new ColorDrawable(backgroundColor), Gravity.LEFT, ClipDrawable.HORIZONTAL);
        bgClipDrawable.setLevel(10000);
        //Progress
        ClipDrawable progressClip = new ClipDrawable(new ColorDrawable(progressColor), Gravity.LEFT, ClipDrawable.HORIZONTAL);
        //Setup LayerDrawable and assign to progressBar
        Drawable[] progressDrawables = {bgClipDrawable, progressClip/*second*/, progressClip};
        LayerDrawable progressLayerDrawable = new LayerDrawable(progressDrawables);
        progressLayerDrawable.setId(0, android.R.id.background);
        progressLayerDrawable.setId(1, android.R.id.secondaryProgress);
        progressLayerDrawable.setId(2, android.R.id.progress);
        progressBar.setProgressDrawable(progressLayerDrawable);
    }
}
