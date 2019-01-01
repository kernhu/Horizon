package cn.walkpast.core;

import android.graphics.Bitmap;
import android.webkit.WebView;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/1 3:59 PM
 * describe: This is...
 */

public class HorizonClient {


    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }

    public void onPageStarted(WebView view, String url) {
    }

    public void onProgressChanged(WebView view, int newProgress) {
    }


    public void onReceivedIcon(WebView view, Bitmap icon) {
    }

    public void onReceiveTitle(WebView view, String title) {
    }


    public void onPageFinished(WebView view, String url) {
    }

    public void onDownload(String url, String contentDisposition, String mimetype, long contentLength) {
    }

    public boolean onJSCallback(String scheme) {
        return false;
    }

}
