package cn.walkpast.horizon.errorpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import cn.walkpast.core.error.BaseErrorPage;
import cn.walkpast.core.error.BindEventCallback;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/20 4:12 PM
 * describe: This is...
 *
 * @deprecated give up using...
 */

public class CustomErrorPage extends BaseErrorPage {

    private Context mContext;
    private BindEventCallback mBindEventCallback;
    private View mView;
    private int mLayout;

    @Override
    public CustomErrorPage setContext(Context context) {
        mContext = context;
        return this;
    }

    @Override
    public CustomErrorPage setBindEventCallback(BindEventCallback callback) {
        mBindEventCallback = callback;
        return this;
    }

    @Override
    public CustomErrorPage setLayout(int layout) {
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
