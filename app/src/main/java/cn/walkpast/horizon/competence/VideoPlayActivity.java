package cn.walkpast.horizon.competence;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.walkpast.core.client.HorizonClient;
import cn.walkpast.horizon.R;

/**
 * Author: Kern
 * Time: 2019/1/5 17:27
 * Description: This is..
 */

public class VideoPlayActivity extends HorizonBaseActivity {

    private static final String TAG = "VideoPlayActivity";

    @BindView(R.id.title)
    public TextView mTitle;
    @BindView(R.id.subheading)
    public TextView mSubheading;
    @BindView(R.id.frame_container)
    public FrameLayout mFrameContainer;

    private String mLoadUrl = "http://m.youku.com/video/id_XNzU2OTgyMzAw.html?sharefrom=iphone&from=timeline&source=";
    //private String mLoadUrl = "http://www.walkpast.cn/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_normal);
        ButterKnife.bind(this);
        mTitle.setText(getIntent().getStringExtra("title"));

        getHorizon()
                .setHorizonClient(mHorizonClient)
                .setViewContainer(mFrameContainer)
                .setWebView(new WebView(this))
                .setOriginalUrl(mLoadUrl)
                .load();
    }

    HorizonClient mHorizonClient = new HorizonClient() {


        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onReceiveTitle(WebView view, String title) {
            super.onReceiveTitle(view, title);

            mSubheading.setText(title);

        }

    };
}
