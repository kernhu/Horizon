package cn.walkpast.core;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import cn.walkpast.core.wakeup.WakeupHelper;

/**
 * Author: Kern
 * Time: 2019/1/4 13:46
 * Description: This is..
 */

public class HorizonOnLongClickListener implements View.OnLongClickListener {

    private Horizon mHorizon;

    public HorizonOnLongClickListener(Horizon horizon) {
        mHorizon = horizon;
    }

    @Override
    public boolean onLongClick(View v) {

        WebView.HitTestResult result = ((WebView) v).getHitTestResult();
        int type = result.getType();
        switch (type) {

            case WebView.HitTestResult.EDIT_TEXT_TYPE: // 选中的文字类型


                break;
            case WebView.HitTestResult.EMAIL_TYPE: // 处理Email

                WakeupHelper
                        .getInstance()
                        .setActivity(mHorizon.getActivity())
                        .setScheme(result.getExtra())
                        .wakeup();

                break;
            case WebView.HitTestResult.GEO_TYPE: // 　地图类型

                WakeupHelper
                        .getInstance()
                        .setActivity(mHorizon.getActivity())
                        .setScheme(result.getExtra())
                        .wakeup();


                break;
            case WebView.HitTestResult.SRC_ANCHOR_TYPE: // 超链接


                WakeupHelper
                        .getInstance()
                        .setActivity(mHorizon.getActivity())
                        .setScheme(result.getExtra())
                        .wakeup();


                break;
            case WebView.HitTestResult.IMAGE_TYPE://图片类型

                ImageDownloadHelper
                        .getInstance()
                        .setActivity(mHorizon.getActivity())
                        .setTargetView(v)
                        .setDownloadPath(mHorizon.getDownloadConfig().getStoragePath())
                        .setImageUrl(result.getExtra())
                        .load();

                break;
            case WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE://图片类型

                ImageDownloadHelper
                        .getInstance()
                        .setActivity(mHorizon.getActivity())
                        .setTargetView(v)
                        .setImageUrl(result.getExtra())
                        .setDownloadPath(mHorizon.getDownloadConfig().getStoragePath())
                        .load();

                break;
            case WebView.HitTestResult.PHONE_TYPE: //处理拨号

                String telNum = result.getExtra();
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
