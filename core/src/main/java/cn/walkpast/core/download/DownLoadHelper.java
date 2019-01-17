package cn.walkpast.core.download;

import android.app.Activity;

import cn.walkpast.download.DownLoadService;
import cn.walkpast.utils.LogUtils;

/**
 * Author: Kern
 * Time: 2019/1/16 20:55
 * Description: This is..
 */

public class DownLoadHelper {

    private static final String SPECHARS_FILENAME = "filename=";
    private static final String SPECHARS_DOUBLE_QUOTE = "\"";
    private static final String SPECHARS_BACKSLASH = "/";
    private static final String SPECHARS_QUESTION_MARK = "[?]";

    private Activity mActivity;
    private String downloadUrl;
    private String contentDisposition;
    private String mimetype;
    private long contentLength;
    private String storagePath;
    private int networkType;
    private int notificationType;


    public Activity getActivity() {
        return mActivity;
    }

    public DownLoadHelper setActivity(Activity activity) {
        mActivity = activity;
        return this;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public DownLoadHelper setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
        return this;
    }

    public String getContentDisposition() {
        return contentDisposition;
    }

    public DownLoadHelper setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
        return this;
    }

    public String getMimetype() {
        return mimetype;
    }

    public DownLoadHelper setMimetype(String mimetype) {
        this.mimetype = mimetype;
        return this;
    }

    public long getContentLength() {
        return contentLength;
    }

    public DownLoadHelper setContentLength(long contentLength) {
        this.contentLength = contentLength;
        return this;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public DownLoadHelper setStoragePath(String storagePath) {
        this.storagePath = storagePath;
        return this;
    }

    public int getNetworkType() {
        return networkType;
    }

    public DownLoadHelper setNetworkType(int networkType) {
        this.networkType = networkType;
        return this;
    }

    public int getNotificationType() {
        return notificationType;
    }

    public DownLoadHelper setNotificationType(int notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    public static DownLoadHelper getInstance() {

        return new DownLoadHelper();
    }

    public void justDoIt() {

        String filename;
        if (getContentDisposition().contains(SPECHARS_FILENAME)) {
            filename = getContentDisposition().split(SPECHARS_FILENAME)[1].replace(SPECHARS_DOUBLE_QUOTE, "");
        } else {
            filename = getDownloadUrl().substring(getDownloadUrl().lastIndexOf(SPECHARS_BACKSLASH) + 1).split(SPECHARS_QUESTION_MARK)[0];
        }
        LogUtils.i("download_sos", "justDoIt====url==" + getDownloadUrl() + ";;;;filename=" + filename + ";;;;mimetype=" + mimetype);

        DownLoadService
                .startDownloadService(getActivity(),
                        getDownloadUrl(),
                        filename,
                        getMimetype(),
                        getStoragePath(),
                        getNetworkType(),
                        getNotificationType());
    }
}
