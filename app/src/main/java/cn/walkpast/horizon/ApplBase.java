package cn.walkpast.horizon;

import android.app.Application;

import cn.walkpast.utils.HorizonUtils;

/**
 * Author: Kern
 * Time: 2019/1/4 20:32
 * Description: This is..
 */

public class ApplBase extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化工具类
        HorizonUtils.init(this);
    }
}
