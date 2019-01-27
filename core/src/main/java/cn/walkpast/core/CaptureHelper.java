package cn.walkpast.core;

import android.graphics.Bitmap;
import android.webkit.WebView;

import cn.walkpast.core.capture.CaptureUtils;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/14 11:15 PM
 * describe: This is...
 */

public class CaptureHelper {

    private WebView mWebView;
    private OnCaptureListener mCaptureListener;

    public static CaptureHelper getInstance() {

        return new CaptureHelper();
    }

    public CaptureHelper setWebView(WebView webView) {
        mWebView = webView;
        return this;
    }

    public OnCaptureListener getCaptureListener() {
        return mCaptureListener;
    }

    public CaptureHelper setCaptureListener(OnCaptureListener captureListener) {
        mCaptureListener = captureListener;
        return this;
    }

    public void capture() {

        if (getCaptureListener() != null) {
            getCaptureListener().onCapture(CaptureUtils.capture(mWebView));
        }
    }

    public interface OnCaptureListener {

        void onCapture(Bitmap bitmap);
    }
}
