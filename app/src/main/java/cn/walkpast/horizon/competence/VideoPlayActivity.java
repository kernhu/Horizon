package cn.walkpast.horizon.competence;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.walkpast.core.WebHorizonActivity;
import cn.walkpast.core.client.HorizonClient;
import cn.walkpast.horizon.R;

/**
 * Author: Kern
 * Time: 2019/1/5 17:27
 * Description: This is..
 */

public class VideoPlayActivity extends WebHorizonActivity {

    private static final String TAG = "VideoPlayActivity";

    @BindView(R.id.video_play_title)
    public TextView mVideoPlayTitle;
    @BindView(R.id.video_play_container)
    public FrameLayout mVideoPlayContainer;

    private String mLoadUrl = "http://m.youku.com/video/id_XNzU2OTgyMzAw.html?sharefrom=iphone&from=timeline&source=";
    //private String mLoadUrl = "http://www.walkpast.cn/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video_play);
        ButterKnife.bind(this);

        getHorizon()
                .setHorizonClient(mHorizonClient)
                .setViewContainer(mVideoPlayContainer)
                .setWebView(new WebView(this))
                .setOriginalUrl(mLoadUrl)
                .preview();
    }

    HorizonClient mHorizonClient = new HorizonClient() {

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onReceiveTitle(WebView view, String title) {
            super.onReceiveTitle(view, title);

            mVideoPlayTitle.setText(title);

        }

    };
}
