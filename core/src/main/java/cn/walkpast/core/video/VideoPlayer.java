package cn.walkpast.core.video;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;

import cn.walkpast.core.R;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/16 12:07 AM
 * describe: This is...
 */

public class VideoPlayer {

    /**
     * 视频全屏参数
     */
    protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS =
            new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    public static VideoPlayer mVideoPlayer;
    public Activity mActivity;

    private FullScreenFrame mFullScreenFrame;
    private View mShowCustomView;
    private WebChromeClient.CustomViewCallback mCallback;

    public Activity getActivity() {
        return mActivity;
    }

    public VideoPlayer setActivity(Activity activity) {
        mActivity = activity;
        return this;
    }

    public static VideoPlayer getInstance() {

        if (mVideoPlayer == null) {
            mVideoPlayer = new VideoPlayer();
        }

        return mVideoPlayer;
    }


    private void setStatusBarVisibility(Activity activity, boolean visible) {

        int flag = visible ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN;
        activity.getWindow().setFlags(flag, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }


    public void showCustomView(View view, WebChromeClient.CustomViewCallback callback) {

        if (mShowCustomView != null) {
            callback.onCustomViewHidden();
            return;
        }
        //view.setBackgroundColor(mActivity.getResources().getColor(R.color.video_default_bgc));
        FrameLayout mDecorView = (FrameLayout) mActivity.getWindow().getDecorView();
        mDecorView.setBackgroundColor(mActivity.getResources().getColor(android.R.color.black));
        mFullScreenFrame = new FullScreenFrame(mActivity);
        mFullScreenFrame.addView(view, COVER_SCREEN_PARAMS);
        mDecorView.addView(mFullScreenFrame, COVER_SCREEN_PARAMS);
        mShowCustomView = view;
        setStatusBarVisibility(mActivity, false);
        mCallback = callback;
    }


    /**
     * 隐藏视频全屏
     */
    public void hideCustomView() {

        if (mShowCustomView == null) {
            return;
        }

        setStatusBarVisibility(mActivity, true);
        FrameLayout mDecorView = (FrameLayout) mActivity.getWindow().getDecorView();
        mDecorView.removeView(mFullScreenFrame);
        mFullScreenFrame = null;
        mShowCustomView = null;
        mCallback.onCustomViewHidden();
    }


}
