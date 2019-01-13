package cn.walkpast.core.indicator;

import android.widget.ProgressBar;

/**
 * Author: Kern
 * Time: 2019/1/11 20:48
 * Description: This is..
 */

public interface Indicator {

    ProgressBar createProgress();

    void setProgress(int progress);

    void setVisible();

    void setGone();
}
