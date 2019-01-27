package cn.walkpast.indicator;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Author: Kern
 * Time: 2019/1/27 11:53
 * Description: This is..
 */

public class CircleProgressBar extends ProgressBar {

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
