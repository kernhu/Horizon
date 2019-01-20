package cn.walkpast.core.error;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/20 3:44 PM
 * describe: This is...
 */

public class DefaultErrorPage extends BaseErrorPage {

    private Context mContext;
    private BindEventCallback mBindEventCallback;
    private View mView;
    private int mLayout;

    @Override
    public DefaultErrorPage setContext(Context context) {
        mContext = context;
        return this;
    }

    @Override
    public DefaultErrorPage setBindEventCallback(BindEventCallback callback) {
        mBindEventCallback = callback;
        return this;
    }

    @Override
    public DefaultErrorPage setLayout(int layout) {
        mLayout = layout;
        return this;
    }

    @Override
    public View createView() {

        mView = LayoutInflater.from(mContext).inflate(mLayout, null, false);
        if (mBindEventCallback != null) {
            mBindEventCallback.bindEvent(mView);
        }

        return mView;
    }


}
