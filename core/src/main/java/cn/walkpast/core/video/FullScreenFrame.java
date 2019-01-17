package cn.walkpast.core.video;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/16 12:05 AM
 * describe: This is...
 */

public class FullScreenFrame extends FrameLayout {

    private static final int BOTTOM_PADDING = 50;

    public FullScreenFrame(Context context) {
        this(context, null);
    }

    public FullScreenFrame(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FullScreenFrame(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setBackgroundColor(context.getResources().getColor(android.R.color.black));
        setPadding(0, 0, 0, BOTTOM_PADDING);

    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.e("sos", "onConfigurationChanged:"+newConfig.orientation);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            setPadding(0, 0, 0, BOTTOM_PADDING);

        } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

            setPadding(0, BOTTOM_PADDING, 0, 0);

        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        return true;
    }


}
