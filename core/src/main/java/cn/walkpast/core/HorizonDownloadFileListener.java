package cn.walkpast.core;

import android.content.Intent;
import android.webkit.DownloadListener;

import cn.walkpast.download.DownLoadService;

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
