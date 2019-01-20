package cn.walkpast.core;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.webkit.WebView;

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

    public WebView getWebView() {
        return mWebView;
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

        if (getWebView() == null) {
            throw new NullPointerException("capture need an WebView,it can't be null");
        }
        ViewGroup mViewParent = (ViewGroup) getWebView().getParent();
        mViewParent.buildDrawingCache();//如果能够缓存图片，则创建图片缓存
        if (getCaptureListener() != null) {
            getCaptureListener().onCapture(mViewParent.getDrawingCache());
        }
        mViewParent.destroyDrawingCache();//释放缓存占用的资源</code>
        mViewParent.setDrawingCacheEnabled(false);
    }

    public interface OnCaptureListener {

        void onCapture(Bitmap bitmap);
    }
}
