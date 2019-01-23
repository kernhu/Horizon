package cn.walkpast.core.capture;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: Kern
 * Time: 2019/1/22 13:58
 * Description: This is..
 */

public class CaptureUtils {

    /**
     * Obtain bitmap's size
     *
     * @param bitmap bitmap
     * @return Returns the minimum number of bytes that can be used to store this bitmap's pixels.
     */
    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {    //API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {//API 12
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }


    /**
     * Capture view screenshot
     *
     * @param view
     * @return return a bitmap from View's parent
     */
    public static Bitmap capture(View view) {

        ViewGroup mViewParent = (ViewGroup) view.getParent();
        Bitmap mCapture = Bitmap.createBitmap(mViewParent.getWidth(), mViewParent.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(mCapture);
        mViewParent.draw(canvas);

        return mCapture;
    }


}
