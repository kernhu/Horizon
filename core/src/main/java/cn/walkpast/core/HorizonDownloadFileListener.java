package cn.walkpast.core;

import android.Manifest;
import android.content.Intent;
import android.webkit.DownloadListener;

import cn.walkpast.download.DownLoadService;
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
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

        mHorizon.getHorizonClient().onDownloadStart(url, userAgent, contentDisposition, mimetype, contentLength);

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

        String filename = "";
        if (contentDisposition.contains("filename")) {
            filename = contentDisposition.split("filename")[1]
                    .replaceAll("\"", "").replace("=", "");
        }
        Intent service = new Intent(mHorizon.getActivity(), DownLoadService.class);
        service.putExtra(DownLoadService.KEY_URL, url);
        service.putExtra(DownLoadService.KEY_FILENAME, filename);
        mHorizon.getActivity().startService(service);
    }
}
