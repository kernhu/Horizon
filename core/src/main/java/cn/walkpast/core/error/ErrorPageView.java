package cn.walkpast.core.error;

import android.content.Context;
import android.view.View;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/20 3:41 PM
 * describe: This is...
 */

public interface ErrorPageView {


    BaseErrorPage setContext(Context context);


    BaseErrorPage setBindEventCallback(BindEventCallback callback);

    BaseErrorPage setLayout(int layout);

    View createView();


}
