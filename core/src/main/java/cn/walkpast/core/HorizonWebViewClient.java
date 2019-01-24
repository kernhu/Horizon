package cn.walkpast.core;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.ByteArrayInputStream;

import cn.walkpast.core.constant.CaptureStrategy;

/**
 * Author: Kern
 * Time: 2019/1/4 11:17
 * Description: This is..
 */

public class HorizonWebViewClient extends WebViewClient {


    private Horizon mHorizon;
    private boolean mReceivedError = false;

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
        Log.e("sos", "shouldOverrideUrlLoading-----111");
        mReceivedError = false;
        return shouldOverrideUrlLoading(view, request.getUrl().toString());
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().shouldOverrideUrlLoading(view, url);
        }
        Log.e("sos", "shouldOverrideUrlLoading-----222" + url);
        mReceivedError = false;
        return DefaultShouldOverrideUrlLoading.shouldOverrideUrlLoading(mHorizon.getActivity(), url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onPageStarted(view, url, favicon);
        }
        Log.e("sos", "onPageStarted-----");
        mReceivedError = false;

        /***************************************** capture bitmap ******************************************/
        if (mHorizon.getCaptureStrategy().ordinal() == CaptureStrategy.START_FINISH.ordinal()
                || mHorizon.getCaptureStrategy().ordinal() == CaptureStrategy.START_MIDDLE_FINISH.ordinal()) {

            CaptureHelper
                    .getInstance()
                    .setWebView(view)
                    .setCaptureListener(mCaptureListener)
                    .capture();
        }
        /***************************************** capture bitmap ******************************************/
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onPageFinished(view, url);
        }

        Log.e("sos", "onPageFinished-----");
        /***************************************** Remove Error Page View ******************************************/
        if (!mReceivedError) {
            ViewGroup mViewParent = (ViewGroup) view.getParent();
            int i = mViewParent.indexOfChild(mHorizon.getErrorPage());
            if (i != -1) {
                view.setVisibility(View.VISIBLE);
                view.bringToFront();
                mViewParent.removeView(mHorizon.getErrorPage());
                mReceivedError = false;
            }
        }
        /***************************************** Remove Error Page View ******************************************/


        /***************************************** capture bitmap ******************************************/
        if (mHorizon.getCaptureStrategy().ordinal() == CaptureStrategy.FINISH.ordinal()
                || mHorizon.getCaptureStrategy().ordinal() == CaptureStrategy.START_FINISH.ordinal()
                || mHorizon.getCaptureStrategy().ordinal() == CaptureStrategy.START_MIDDLE_FINISH.ordinal()) {

            CaptureHelper
                    .getInstance()
                    .setWebView(view)
                    .setCaptureListener(mCaptureListener)
                    .capture();
        }
        /***************************************** capture bitmap ******************************************/
    }

    @Override
    public void onReceivedError(final WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onReceivedError(view, errorCode, description, failingUrl);
        }
        Log.e("sos", "onReceivedError-----errorCode=" + errorCode + ";;;description=" + description);
        /***************************************** Create Error Page View ******************************************/
        mReceivedError = true;
        ViewGroup mViewParent = (ViewGroup) view.getParent();
        mHorizon.getErrorPage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.reload();
            }
        });

        int j = mViewParent.indexOfChild(view);
        if (mViewParent.indexOfChild(mHorizon.getErrorPage()) == -1) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            view.setVisibility(View.GONE);
            mViewParent.addView(mHorizon.getErrorPage(), j, lp);
            mHorizon.getErrorPage().setVisibility(View.VISIBLE);
            mHorizon.getErrorPage().bringToFront();
        }
        /***************************************** Create Error Page View ******************************************/
        view.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mHorizon.getCaptureStrategy().ordinal() == CaptureStrategy.FINISH.ordinal()
                        || mHorizon.getCaptureStrategy().ordinal() == CaptureStrategy.START_FINISH.ordinal()
                        || mHorizon.getCaptureStrategy().ordinal() == CaptureStrategy.START_MIDDLE_FINISH.ordinal()) {

                    CaptureHelper
                            .getInstance()
                            .setWebView(view)
                            .setCaptureListener(mCaptureListener)
                            .capture();
                }

            }
        }, 80);

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

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().shouldInterceptRequest(view, url);
        }

        /***************************************** Intercept Request Url ******************************************/
        if (mHorizon.getCoreConfig().getFilterList() != null && mHorizon.getCoreConfig().getFilterList().length != 0) {
            if (FilterHelper.isNeedFilter(mHorizon.getCoreConfig().getFilterType(), mHorizon.getCoreConfig().getFilterList(), url)) {
                String replaceUrl;
                if (mHorizon.getCoreConfig().getFilterReplaceUrl() != null && !mHorizon.getCoreConfig().getFilterReplaceUrl().isEmpty()) {
                    replaceUrl = FilterHelper.getUrlToString(mHorizon.getCoreConfig().getFilterReplaceUrl());
                    replaceUrl = replaceUrl == null ? FilterHelper.DEFAULT_REPLACE_URL : replaceUrl;
                } else {
                    replaceUrl = FilterHelper.DEFAULT_REPLACE_URL;
                }
                return new WebResourceResponse("text/html", "utf-8", new ByteArrayInputStream(replaceUrl.getBytes()));
            }
        }

        return super.shouldInterceptRequest(view, url);
    }

    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {

        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().shouldOverrideKeyEvent(view, event);
        }
        return super.shouldOverrideKeyEvent(view, event);
    }

    CaptureHelper.OnCaptureListener mCaptureListener = new CaptureHelper.OnCaptureListener() {
        @Override
        public void onCapture(Bitmap bitmap) {
            if (mHorizon.getHorizonClient() != null) {
                mHorizon.getHorizonClient().onCaptured(bitmap);
            }
        }
    };
}
