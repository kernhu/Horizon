package cn.walkpast.core;

import android.Manifest;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
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
import android.widget.FrameLayout;

import cn.walkpast.core.constant.CaptureStrategy;
import cn.walkpast.core.constant.Strategy;
import cn.walkpast.core.constant.Theme;
import cn.walkpast.core.dialog.CommonDialog;
import cn.walkpast.core.theme.ThemeHelper;
import cn.walkpast.core.video.VideoPlayer;
import cn.walkpast.utils.LogUtils;
import cn.walkpast.utils.permission.PermissionUtil;
import cn.walkpast.utils.permission.callback.PermissionResultCallBack;

/**
 * Author: Kern
 * Time: 2019/1/4 11:15
 * Description: This is..
 */

public class HorizonWebChromeClient extends WebChromeClient implements CaptureHelper.OnCaptureListener {

    private Horizon mHorizon;

    public HorizonWebChromeClient(Horizon horizon) {
        if (horizon == null) {
            throw new NullPointerException("Horizon can't be null in HorizonWebChromeClient");
        }
        mHorizon = horizon;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        LogUtils.d("horizon_sos", "onProgressChanged------" + newProgress);
        if (newProgress == 100) {
            mHorizon.getProgressConfig().getIndicator().setVisibility(View.GONE);

            if (mHorizon.getCoreConfig().isHardwareAccelerated()) {
                HardwareHelper.setupHwAcceleration(view, false);
            }

            if (mHorizon.getCoreConfig().getStrategy() == Strategy.CORE_PRIORITY_TEXT_IMAGE) {
                view.getSettings().setBlockNetworkImage(false);
            }

        } else {
            if (mHorizon.getProgressConfig().getIndicator().getVisibility() == View.GONE) {
                mHorizon.getProgressConfig().getIndicator().setVisibility(View.VISIBLE);
            }


            if (mHorizon.getCoreConfig().isHardwareAccelerated()) {
                HardwareHelper.setupHwAcceleration(view, true);
            }


            if (mHorizon.getCoreConfig().getStrategy() == Strategy.CORE_PRIORITY_TEXT_IMAGE) {
                view.getSettings().setBlockNetworkImage(true);
            }


            if (mHorizon.getCoreConfig().isThemeEnable() && mHorizon.getCoreConfig().getTheme() == Theme.THEME_DARK) {
                ThemeHelper.getInstance().injectDark(view);
            } else if (mHorizon.getCoreConfig().isThemeEnable() && mHorizon.getCoreConfig().getTheme() == Theme.THEME_DARK) {
                ThemeHelper.getInstance().injectLight(view);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mHorizon.getProgressConfig().getIndicator().setProgress(newProgress, false);
            } else {
                mHorizon.getProgressConfig().getIndicator().setProgress(newProgress);
            }
        }

        if (mHorizon.getCoreConfig().isPatternlessEnable()) {
            view.getSettings().setBlockNetworkImage(true);
        }

        /********************************************************************************/
        if (newProgress == 10) {

            if (mHorizon.getCaptureStrategy().ordinal() == CaptureStrategy.START_FINISH.ordinal()
                    || mHorizon.getCaptureStrategy().ordinal() == CaptureStrategy.START_MIDDLE_FINISH.ordinal()) {

                CaptureHelper
                        .getInstance()
                        .setWebView(view)
                        .setCaptureListener(this)
                        .capture();

            }

        } else if (newProgress == 50) {
            if (mHorizon.getCaptureStrategy().ordinal() == CaptureStrategy.START_MIDDLE_FINISH.ordinal()) {

                CaptureHelper
                        .getInstance()
                        .setWebView(view)
                        .setCaptureListener(this)
                        .capture();

            }
        } else if (newProgress == 100) {

            if (mHorizon.getCaptureStrategy().ordinal() == CaptureStrategy.FINISH.ordinal()
                    || mHorizon.getCaptureStrategy().ordinal() == CaptureStrategy.START_FINISH.ordinal()
                    || mHorizon.getCaptureStrategy().ordinal() == CaptureStrategy.START_MIDDLE_FINISH.ordinal()) {

                CaptureHelper
                        .getInstance()
                        .setWebView(view)
                        .setCaptureListener(this)
                        .capture();

            }

        }
        /********************************************************************************/

        super.onProgressChanged(view, newProgress);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onProgressChanged(view, newProgress);
        }

    }


    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        LogUtils.d("horizon_sos", "onReceivedTitle------" + title);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onReceiveTitle(view, title);
        }
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
        LogUtils.d("horizon_sos", "onReceivedIcon------" + (icon == null));
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onReceivedIcon(view, icon);
        }
    }

    @Override
    public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
        super.onReceivedTouchIconUrl(view, url, precomposed);
        LogUtils.d("horizon_sos", "onReceivedTouchIconUrl------" + "precomposed=" + precomposed + "URL==" + url);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onReceivedTouchIconUrl(view, url, precomposed);
        }
    }

    @Override
    public void onGeolocationPermissionsHidePrompt() {
        super.onGeolocationPermissionsHidePrompt();
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onGeolocationPermissionsHidePrompt();
        }
    }

    @Override
    public void onPermissionRequest(PermissionRequest request) {
        super.onPermissionRequest(request);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onPermissionRequest(request);
        }
    }

    @Override
    public void onPermissionRequestCanceled(PermissionRequest request) {
        super.onPermissionRequestCanceled(request);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onPermissionRequestCanceled(request);
        }
    }


    @Override
    public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
        super.onShowCustomView(view, requestedOrientation, callback);

        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onShowCustomView(view, requestedOrientation, callback);
        }
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

        if (mHorizon.getHorizonClient() != null) {
            return mHorizon.getHorizonClient().onJsPrompt(view, url, message, defaultValue, result);
        }
        return false;
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {

        if (mHorizon.getHorizonClient() != null) {
            return mHorizon.getHorizonClient().onJsConfirm(view, url, message, result);
        }
        return false;
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

        if (mHorizon.getHorizonClient() != null) {
            return mHorizon.getHorizonClient().onJsAlert(view, url, message, result);
        }
        return false;

    }

    @Override
    public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {

        if (mHorizon.getHorizonClient() != null) {
            return mHorizon.getHorizonClient().onJsBeforeUnload(view, url, message, result);
        }
        return false;

    }

    @Override
    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {

        if (mHorizon.getHorizonClient() != null) {
            return mHorizon.getHorizonClient().onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }
        return false;

    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {

        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onShowFileChooser(webView, filePathCallback, fileChooserParams);
        }

        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
    }

    @Override
    public void onCloseWindow(WebView window) {
        super.onCloseWindow(window);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onCloseWindow(window);
        }
    }

    /*******************************************************************************************
     * video play
     * @return
     */
    @Override
    public View getVideoLoadingProgressView() {

        FrameLayout frameLayout = new FrameLayout(mHorizon.getActivity());
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        return frameLayout;
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        super.onShowCustomView(view, callback);

        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onShowCustomView(view, callback);
        }

        VideoPlayer
                .getInstance()
                .setActivity(mHorizon.getActivity())
                .showCustomView(view, callback);

    }

    @Override
    public void onHideCustomView() {
        super.onHideCustomView();

        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onHideCustomView();
        }

        VideoPlayer
                .getInstance()
                .hideCustomView();

    }


    /*************************************************************************************/


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

            if (!GeolocationHelper.isGeolocationDialogShowing) {
                GeolocationHelper.isGeolocationDialogShowing = true;
                CommonDialog.getInstance()
                        .setActivity(mHorizon.getActivity())
                        .setTitle(mHorizon.getActivity().getString(R.string.geolocation_title))
                        .setMessage(String.format(mHorizon.getActivity().getString(R.string.geolocation_message), Uri.parse(origin).getHost()))
                        .setNegativeBtn(mHorizon.getActivity().getString(R.string.geolocation_refuse))
                        .setPositiveBtn(mHorizon.getActivity().getString(R.string.geolocation_allow))
                        .setPositiveListener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GeolocationHelper.isGeolocationDialogShowing = false;
                                if (GeolocationHelper.isSystemLocationEnable(mHorizon.getActivity())) {
                                    callback.invoke(origin, true, false);
                                } else {
                                    GeolocationHelper.actionLocation(mHorizon.getActivity());
                                }
                            }
                        })
                        .setNegativeListener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GeolocationHelper.isGeolocationDialogShowing = false;
                            }
                        })
                        .show();

            }
        }

        super.onGeolocationPermissionsShowPrompt(origin, callback);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onGeolocationPermissionsShowPrompt(origin, callback);
        }

    }

    @Override
    public void onRequestFocus(WebView view) {
        super.onRequestFocus(view);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onRequestFocus(view);
        }
    }

    @Override
    public boolean onJsTimeout() {

        if (mHorizon.getHorizonClient() != null) {
            return mHorizon.getHorizonClient().onJsTimeout();
        }
        return false;
    }

    @Override
    public void onConsoleMessage(String message, int lineNumber, String sourceID) {
        super.onConsoleMessage(message, lineNumber, sourceID);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onConsoleMessage(message, lineNumber, sourceID);
        }
    }

    @Override
    public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota, WebStorage.QuotaUpdater quotaUpdater) {
        super.onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater);
        }
    }

    @Override
    public void onReachedMaxAppCacheSize(long requiredStorage, long quota, WebStorage.QuotaUpdater quotaUpdater) {
        super.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater);
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater);
        }
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {

        if (mHorizon.getHorizonClient() != null) {
            return mHorizon.getHorizonClient().onConsoleMessage(consoleMessage);
        }
        return false;

    }

    @Override
    public void onCapture(Bitmap bitmap) {
        if (mHorizon.getHorizonClient() != null) {
            mHorizon.getHorizonClient().onCaptured(bitmap);
        }
    }

}
