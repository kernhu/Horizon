package cn.walkpast.core;

import android.view.View;
import android.webkit.WebView;

import cn.walkpast.core.wakeup.WakeupHelper;

/**
 * Author: Kern
 * Time: 2019/1/4 13:46
 * Description: This is..
 */

public class HorizonOnLongClickListener implements View.OnLongClickListener {

    Horizon mHorizon;

    public HorizonOnLongClickListener(Horizon horizon) {
        mHorizon = horizon;
    }

    @Override
    public boolean onLongClick(View v) {

        WebView.HitTestResult result = ((WebView) v).getHitTestResult();
        int type = result.getType();
        switch (type) {
            case WebView.HitTestResult.IMAGE_TYPE://图片类型

                //图片逻辑下载
                //ImageOperateManager.downloadImage(v.getContext(), result.getExtra());

                break;
            case WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE://图片类型

                //ImageOperateManager.downloadImage(v.getContext(), result.getExtra());

                break;
            case WebView.HitTestResult.PHONE_TYPE://电话号码类型

                String telNum = result.getExtra();
                //TelCallManager.showCallTelDialog(v.getContext(), telNum);
                WakeupHelper
                        .getInstance()
                        .setActivity(mHorizon.getActivity())
                        .setScheme(telNum)
                        .wakeup();

                break;

            default:


                break;
        }

        return false;
    }
}
