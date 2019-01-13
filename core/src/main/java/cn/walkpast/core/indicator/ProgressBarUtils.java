package cn.walkpast.core.indicator;

import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.widget.ProgressBar;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/13 8:50 PM
 * describe: This is...
 */

public class ProgressBarUtils {


    public static ProgressBarUtils getInstance() {

        return new ProgressBarUtils();
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
