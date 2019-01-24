package cn.walkpast.core.client;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebStorage;
import android.webkit.WebView;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/1 3:59 PM
 * describe: This is...
 */

public class HorizonClient {


    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return false;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
    }

    public void onProgressChanged(WebView view, int newProgress) {
    }


    public void onReceivedIcon(WebView view, Bitmap icon) {
    }

    public void onReceiveTitle(WebView view, String title) {
    }

    public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
    }

    public void onGeolocationPermissionsHidePrompt() {

    }

    public void onPermissionRequest(PermissionRequest request) {
    }

    public void onPermissionRequestCanceled(PermissionRequest request) {

    }


    public void onShowCustomView(View view, int requestedOrientation, WebChromeClient.CustomViewCallback callback) {

    }

    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

        return false;
    }

    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {

        return false;
    }

    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

        return false;
    }

    public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
        return false;
    }

    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
        return false;
    }

    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {

        return false;
    }

    public void onCloseWindow(WebView window) {

    }

    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {

    }

    public void onRequestFocus(WebView view) {
    }


    public boolean onJsTimeout() {

        return false;
    }

    public void onConsoleMessage(String message, int lineNumber, String sourceID) {

    }

    public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota, WebStorage.QuotaUpdater quotaUpdater) {
    }

    public void onReachedMaxAppCacheSize(long requiredStorage, long quota, WebStorage.QuotaUpdater quotaUpdater) {

    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return false;
    }


    public void onPageFinished(WebView view, String url) {
    }


    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
    }


    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
    }

    public void onLoadResource(WebView view, String url) {
    }

    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
    }

    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
    }

    public void shouldInterceptRequest(WebView view, String url) {

    }

    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {

        return false;
    }

    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
    }

    public boolean onJSCallback(String scheme) {
        return false;
    }


    public void onCaptured(Bitmap bitmap) {


    }

    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
    }

    public void onHideCustomView() {
    }

}
