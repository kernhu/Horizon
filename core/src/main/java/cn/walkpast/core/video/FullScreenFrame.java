package cn.walkpast.core.video;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/16 12:05 AM
 * describe: This is...
 */

public class FullScreenFrame extends FrameLayout {

    public FullScreenFrame(Context ctx) {
        super(ctx);
        setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
    }

    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        return true;
    }
}
