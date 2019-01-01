package cn.walkpast.core;

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

}
