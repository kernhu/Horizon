package cn.walkpast.core.error;

import android.content.Context;
import android.view.View;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/20 3:38 PM
 * describe: This is...
 */

public class BaseErrorPage implements ErrorPageView {


    @Override
    public BaseErrorPage setContext(Context context) {

        return this;
    }

    @Override
    public BaseErrorPage setBindEventCallback(BindEventCallback callback) {
        return this;
    }

    @Override
    public BaseErrorPage setLayout(int layout) {
        return this;
    }

    @Override
    public View createView() {

        return null;
    }

}
