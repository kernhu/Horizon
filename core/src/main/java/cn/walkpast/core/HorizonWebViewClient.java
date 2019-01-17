package cn.walkpast.core;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Author: Kern
 * Time: 2019/1/4 11:17
 * Description: This is..
 */

public class HorizonWebViewClient extends WebViewClient {


    private Horizon mHorizon;

    public HorizonWebViewClient(Horizon horizon) {

        if (horizon == null) {
            throw new NullPointerException("Horizon can't be null in HorizonWebViewClient");
        }
        mHorizon = horizon;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().shouldOverrideUrlLoading(view, request);
        }

        return shouldOverrideUrlLoading(view, request.getUrl().toString());
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().shouldOverrideUrlLoading(view, url);
        }

        return DefaultShouldOverrideUrlLoading.shouldOverrideUrlLoading(mHorizon.getActivity(), url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onPageStarted(view, url, favicon);
        }
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onPageFinished(view, url);
        }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onReceivedError(view, errorCode, description, failingUrl);
        }
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onReceivedSslError(view, handler, error);
        }
        handler.proceed();// 接受所有网站的证书

    }


    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onLoadResource(view, url);
        }
    }

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onReceivedHttpError(view, request, errorResponse);
        }
    }

    @Override
    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
        super.doUpdateVisitedHistory(view, url, isReload);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().doUpdateVisitedHistory(view, url, isReload);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().shouldInterceptRequest(view, request);
        }
        return shouldInterceptRequest(view, request.getUrl().toString());
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().shouldInterceptRequest(view, url);
        }
        if (mHorizon.getCoreConfig().getFilterList() == null
                || !FilterHelper.isNeedFilter(mHorizon.getCoreConfig().getFilterType(), mHorizon.getCoreConfig().getFilterList(), url)) {
            return super.shouldInterceptRequest(view, url);
        } else {
            return new WebResourceResponse(null, null, null);
        }

    }

    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {

        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().shouldOverrideKeyEvent(view, event);
        }
        return super.shouldOverrideKeyEvent(view, event);
    }
}
