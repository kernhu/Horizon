package cn.walkpast.core;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Message;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;

import cn.walkpast.core.constant.Strategy;
import cn.walkpast.core.constant.Theme;
import cn.walkpast.core.dialog.CommonDialog;
import cn.walkpast.core.theme.ThemeHelper;
import cn.walkpast.utils.LogUtils;
import cn.walkpast.utils.permission.PermissionUtil;
import cn.walkpast.utils.permission.callback.PermissionResultCallBack;

/**
 * Author: Kern
 * Time: 2019/1/4 11:15
 * Description: This is..
 */

public class HorizonWebChromeClient extends WebChromeClient {

    private Horizon mHorizon;

    public HorizonWebChromeClient(Horizon horizon) {
        mHorizon = horizon;
    }

    @SuppressLint("NewApi")
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        LogUtils.d("horizon_sos", "onProgressChanged------" + newProgress);
        if (newProgress == 100) {
            mHorizon.getProgressConfig().getIndicator().setVisibility(View.GONE);

            if (mHorizon.getCoreConfig().isHardwareAccelerated()) {
                HardwareUtils.setupHwAcceleration(view, false);
            }

            if (mHorizon.getCoreConfig().getStrategy() == Strategy.CORE_PRIORITY_TEXT_IMAGE) {
                view.getSettings().setBlockNetworkImage(false);
            }

        } else {
            if (mHorizon.getProgressConfig().getIndicator().getVisibility() == View.GONE) {
                mHorizon.getProgressConfig().getIndicator().setVisibility(View.VISIBLE);
            }


            if (mHorizon.getCoreConfig().isHardwareAccelerated()) {
                HardwareUtils.setupHwAcceleration(view, true);
            }


            if (mHorizon.getCoreConfig().getStrategy() == Strategy.CORE_PRIORITY_TEXT_IMAGE) {
                view.getSettings().setBlockNetworkImage(true);
            }


            if (mHorizon.getCoreConfig().isThemeEnable() && mHorizon.getCoreConfig().getTheme() == Theme.THEME_DARK) {
                ThemeHelper.getInstance().injectDark(view);
            } else if (mHorizon.getCoreConfig().isThemeEnable() && mHorizon.getCoreConfig().getTheme() == Theme.THEME_DARK) {
                ThemeHelper.getInstance().injectLight(view);
            }

            mHorizon.getProgressConfig().getIndicator().setProgress(newProgress, false);
        }

        if (mHorizon.getCoreConfig().isPatternlessEnable()) {
            view.getSettings().setBlockNetworkImage(true);
        }

        super.onProgressChanged(view, newProgress);
        mHorizon.getHorizonClient().onProgressChanged(view, newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        LogUtils.d("horizon_sos", "onReceivedTitle------" + title);
        mHorizon.getHorizonClient().onReceiveTitle(view, title);
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
        LogUtils.d("horizon_sos", "onReceivedIcon------" + (icon == null));
        mHorizon.getHorizonClient().onReceivedIcon(view, icon);
    }

    @Override
    public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
        super.onReceivedTouchIconUrl(view, url, precomposed);
        LogUtils.d("horizon_sos", "onReceivedTouchIconUrl------" + "precomposed=" + precomposed + "URL==" + url);
        mHorizon.getHorizonClient().onReceivedTouchIconUrl(view, url, precomposed);
    }

    @Override
    public void onGeolocationPermissionsHidePrompt() {
        super.onGeolocationPermissionsHidePrompt();
    }

    @Override
    public void onPermissionRequest(PermissionRequest request) {
        super.onPermissionRequest(request);

        mHorizon.getHorizonClient().onPermissionRequest(request);
    }

    @Override
    public void onPermissionRequestCanceled(PermissionRequest request) {
        super.onPermissionRequestCanceled(request);
    }


    @Override
    public void onHideCustomView() {
        super.onHideCustomView();

        mHorizon.getHorizonClient().onHideCustomView();
    }

    @Override
    public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
        super.onShowCustomView(view, requestedOrientation, callback);

        mHorizon.getHorizonClient().onShowCustomView(view, requestedOrientation, callback);
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

        return mHorizon.getHorizonClient().onJsPrompt(view, url, message, defaultValue, result);
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {

        return mHorizon.getHorizonClient().onJsConfirm(view, url, message, result);
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

        return mHorizon.getHorizonClient().onJsAlert(view, url, message, result);
    }

    @Override
    public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {

        return mHorizon.getHorizonClient().onJsBeforeUnload(view, url, message, result);
    }

    @Override
    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {

        return mHorizon.getHorizonClient().onCreateWindow(view, isDialog, isUserGesture, resultMsg);
    }


    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {

        return mHorizon.getHorizonClient().onShowFileChooser(webView, filePathCallback, fileChooserParams);
    }

    @Override
    public void onCloseWindow(WebView window) {
        super.onCloseWindow(window);

        mHorizon.getHorizonClient().onCloseWindow(window);
    }

    @Override
    public void onGeolocationPermissionsShowPrompt(final String origin, final GeolocationPermissions.Callback callback) {


        if (mHorizon.getCoreConfig().isGeolocationEnalbe()) {

            PermissionUtil
                    .getInstance()
                    .request(mHorizon.getActivity(),
                            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE},
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

            if (!GeolocationUtils.isGeolocationDialogShowing) {
                GeolocationUtils.isGeolocationDialogShowing = true;
                CommonDialog.getInstance()
                        .setActivity(mHorizon.getActivity())
                        .setTitle(mHorizon.getActivity().getString(R.string.geolocation_title))
                        .setMessage(String.format(mHorizon.getActivity().getString(R.string.geolocation_message), Uri.parse(origin).getHost()))
                        .setNegativeBtn(mHorizon.getActivity().getString(R.string.geolocation_refuse))
                        .setPositiveBtn(mHorizon.getActivity().getString(R.string.geolocation_allow))
                        .setPositiveListener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GeolocationUtils.isGeolocationDialogShowing = false;
                                if (GeolocationUtils.isSystemLocationEnable(mHorizon.getActivity())) {
                                    callback.invoke(origin, true, false);
                                } else {
                                    GeolocationUtils.actionLocation(mHorizon.getActivity());
                                }
                            }
                        })
                        .setNegativeListener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GeolocationUtils.isGeolocationDialogShowing = false;
                            }
                        })
                        .show();

            }
        }

        super.onGeolocationPermissionsShowPrompt(origin, callback);
        mHorizon.getHorizonClient().onGeolocationPermissionsShowPrompt(origin, callback);

    }

    @Override
    public void onRequestFocus(WebView view) {
        super.onRequestFocus(view);

        mHorizon.getHorizonClient().onRequestFocus(view);
    }

    @Override
    public boolean onJsTimeout() {

        return mHorizon.getHorizonClient().onJsTimeout();
    }

    @Override
    public void onConsoleMessage(String message, int lineNumber, String sourceID) {
        super.onConsoleMessage(message, lineNumber, sourceID);
        mHorizon.getHorizonClient().onConsoleMessage(message, lineNumber, sourceID);
    }

    @Override
    public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota, WebStorage.QuotaUpdater quotaUpdater) {
        super.onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater);
        mHorizon.getHorizonClient().onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater);
    }

    @Override
    public void onReachedMaxAppCacheSize(long requiredStorage, long quota, WebStorage.QuotaUpdater quotaUpdater) {
        super.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater);

        mHorizon.getHorizonClient().onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater);
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {

        return mHorizon.getHorizonClient().onConsoleMessage(consoleMessage);
    }
}
