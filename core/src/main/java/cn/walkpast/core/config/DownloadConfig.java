package cn.walkpast.core.config;

import android.app.Activity;

import cn.walkpast.core.constant.NetworkType;
import cn.walkpast.core.constant.NotificationType;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/1 5:40 PM
 * describe: This is...
 */

public class DownloadConfig {

    private Activity mActivity;
    private String mStoragePath = "/horizon/download/";
    private NetworkType mNetworkType = NetworkType.NETWORK_ONLY_WIFI;
    private NotificationType mNotificationType = NotificationType.VISIBILITY_VISIBLE_NOTIFY_COMPLETED;
    private boolean mTooltipEnable = true;

    public DownloadConfig(Activity activity) {
        mActivity = activity;
    }

    public static DownloadConfig with(Activity activity) {

        return new DownloadConfig(activity);
    }

    public Activity getActivity() {
        return mActivity;
    }

    public String getStoragePath() {
        return mStoragePath;
    }

    public DownloadConfig setStoragePath(String storagePath) {
        mStoragePath = storagePath;
        return this;
    }

    public NetworkType getNetworkType() {
        return mNetworkType;
    }

    public DownloadConfig setNetworkType(NetworkType networkType) {
        mNetworkType = networkType;
        return this;
    }

    public NotificationType getNotificationType() {
        return mNotificationType;
    }

    public DownloadConfig setNotificationType(NotificationType notificationType) {
        mNotificationType = notificationType;
        return this;
    }

    public boolean isTooltipEnable() {
        return mTooltipEnable;
    }

    public DownloadConfig setTooltipEnable(boolean tooltipEnable) {
        mTooltipEnable = tooltipEnable;
        return this;
    }

    public DownloadConfig config() {

        return this;
    }
}
