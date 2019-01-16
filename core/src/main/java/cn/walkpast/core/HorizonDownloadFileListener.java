package cn.walkpast.core;

import android.Manifest;
import android.content.DialogInterface;
import android.util.Log;
import android.webkit.DownloadListener;

import cn.walkpast.core.constant.NetworkType;
import cn.walkpast.core.dialog.CommonDialog;
import cn.walkpast.core.download.DownLoadHelper;
import cn.walkpast.utils.NetworkUtils;
import cn.walkpast.utils.permission.PermissionUtil;
import cn.walkpast.utils.permission.callback.PermissionResultCallBack;

/**
 * Author: Kern
 * Time: 2019/1/4 12:13
 * Description: This is..
 */

public class HorizonDownloadFileListener implements DownloadListener {

    private Horizon mHorizon;

    public HorizonDownloadFileListener(Horizon horizon) {
        mHorizon = horizon;
    }

    @Override
    public void onDownloadStart(final String url, String userAgent, final String contentDisposition, final String mimetype, final long contentLength) {

        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onDownloadStart(url, userAgent, contentDisposition, mimetype, contentLength);
        }

        if (mHorizon.getDownloadConfig() == null) {
            return;
        }
        //权限申请一次
        PermissionUtil
                .getInstance()
                .request(mHorizon.getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NOTIFICATION_POLICY},
                        new PermissionResultCallBack() {
                            @Override
                            public void onPermissionGranted() {

                            }

                            @Override
                            public void onPermissionGranted(String... permissions) {
                            }

                            @Override
                            public void onPermissionDenied(String... permissions) {

                                return;
                            }

                            @Override
                            public void onRationalShow(String... permissions) {

                            }
                        });

        Log.e("sos", "onDownloadStart---url=" + url + ";;;contentDisposition=" + contentDisposition + ";;;mimetype=" + mimetype + ";;;contentLength=" + contentLength);

        if (mHorizon.getDownloadConfig().getNetworkType() == NetworkType.TYPE_JUST_WIFI) {

            if (!NetworkUtils.is4G()) {

                DownLoadHelper
                        .getInstance()
                        .setActivity(mHorizon.getActivity())
                        .setDownloadUrl(url)
                        .setContentDisposition(contentDisposition)
                        .setMimetype(mimetype)
                        .setContentLength(contentLength)
                        .setStoragePath(mHorizon.getDownloadConfig().getStoragePath())
                        .justDoIt();

            }

        } else if (mHorizon.getDownloadConfig().getNetworkType() == NetworkType.TYPE_BOTH_GPRS_WIFI) {

            if (NetworkUtils.is4G()) {

                CommonDialog
                        .getInstance()
                        .setActivity(mHorizon.getActivity())
                        .setTitle(mHorizon.getActivity().getString(R.string.download_tips))
                        .setMessage(mHorizon.getActivity().getString(R.string.download_message))
                        .setPositiveBtn(mHorizon.getActivity().getString(R.string.download_sure))
                        .setNegativeBtn(mHorizon.getActivity().getString(R.string.download_cancel))
                        .setPositiveListener(new DialogInterface.OnClickListener() {
                                                 @Override
                                                 public void onClick(DialogInterface dialog, int which) {

                                                     DownLoadHelper
                                                             .getInstance()
                                                             .setActivity(mHorizon.getActivity())
                                                             .setDownloadUrl(url)
                                                             .setContentDisposition(contentDisposition)
                                                             .setMimetype(mimetype)
                                                             .setContentLength(contentLength)
                                                             .setStoragePath(mHorizon.getDownloadConfig().getStoragePath())
                                                             .justDoIt();

                                                 }
                                             }
                        )
                        .show();

            } else {

                DownLoadHelper
                        .getInstance()
                        .setActivity(mHorizon.getActivity())
                        .setDownloadUrl(url)
                        .setContentDisposition(contentDisposition)
                        .setMimetype(mimetype)
                        .setContentLength(contentLength)
                        .setStoragePath(mHorizon.getDownloadConfig().getStoragePath())
                        .justDoIt();

            }
        }

    }

}
