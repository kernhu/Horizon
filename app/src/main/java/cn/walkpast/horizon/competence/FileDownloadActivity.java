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
import cn.walkpast.core.config.DownloadConfig;
import cn.walkpast.core.constant.NetworkType;
import cn.walkpast.core.constant.NotificationType;
import cn.walkpast.horizon.R;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/6 1:50 AM
 * describe: This is...
 */

public class FileDownloadActivity extends HorizonBaseActivity {


    private static final String TAG = "FileDownloadActivity";

    @BindView(R.id.title)
    public TextView mTitle;
    @BindView(R.id.subheading)
    public TextView mSubheading;
    @BindView(R.id.frame_container)
    public FrameLayout mFrameContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_normal);
        ButterKnife.bind(this);
        mTitle.setText(getIntent().getStringExtra("title"));


        getHorizon()
                .setDownloadConfig(DownloadConfig.with(this)
                        .setStoragePath("/horizon1/")
                        .setNetworkType(NetworkType.NETWORK_MOBILE_AND_WIFI)
                        .setNotificationType(NotificationType.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
                        .setTooltipEnable(true)
                        .config()
                )
                .setHorizonClient(mHorizonClient)
                .setViewContainer(mFrameContainer)
                .setWebView(new WebView(this))
                .setOriginalUrl("http://app.so.com/")
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

            mSubheading.setText(title);

        }

    };

}
