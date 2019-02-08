package cn.walkpast.utils;

import android.app.Service;
import android.os.Vibrator;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/2/8 9:51 PM
 * describe: This is...
 */

public class VibratorUtils {

    private static final long VIBRATOR_TIME = 100;

    private static VibratorUtils mVibratorUtils;
    private Vibrator mVibrator;

    public VibratorUtils() {

        mVibrator = (Vibrator) HorizonUtils.getApp().getSystemService(Service.VIBRATOR_SERVICE);
    }

    public static VibratorUtils getInstance() {

        if (mVibratorUtils == null) {
            mVibratorUtils = new VibratorUtils();
        }
        return mVibratorUtils;
    }

    public void vibrator() {

        mVibrator.vibrate(VIBRATOR_TIME);
    }

    public void vibrator(long milliseconds) {

        mVibrator.vibrate(milliseconds);
    }
}
