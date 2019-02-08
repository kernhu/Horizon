package cn.walkpast.core;

import android.content.Intent;
import android.view.KeyEvent;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/1 11:14 PM
 * describe: This is...
 */

public interface ILifecycle {

    void onPause();

    void onResume();

    void onStop();

    void onDestroy();

    void onTrimMemory(int level);

    boolean onKeyDown(int keyCode, KeyEvent event);

    void onActivityResult(int requestCode, int resultCode, Intent data);

}
