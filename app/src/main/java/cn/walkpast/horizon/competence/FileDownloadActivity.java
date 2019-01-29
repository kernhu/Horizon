package cn.walkpast.horizon.competence;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.walkpast.core.WebHorizonActivity;
import cn.walkpast.core.client.HorizonClient;
import cn.walkpast.core.config.DownloadConfig;
import cn.walkpast.core.constant.NetworkType;
import cn.walkpast.core.constant.NotificationType;
import cn.walkpast.horizon.R;
import cn.walkpast.horizon.widget.PopupWindowTools;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/6 1:50 AM
 * describe: This is...
 */

public class FileDownloadActivity extends WebHorizonActivity implements View.OnClickListener {

    private static final String TAG = "FileDownloadActivity";

    @BindView(R.id.download_icon)
    public ImageView mDownloadIcon;
    @BindView(R.id.download_menu)
    public ImageView mDownloadMenu;
    @BindView(R.id.download_title)
    public TextView mDownloadTitle;
    @BindView(R.id.download_container)
    public FrameLayout mFrameContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file_download);
        ButterKnife.bind(this);

        getHorizon()
                .setDownloadConfig(DownloadConfig.with(this)
                        .setStoragePath("/horizon/")
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

            mDownloadTitle.setText(title);
        }

    };

    @OnClick(R.id.download_menu)
    @Override
    public void onClick(View v) {

        PopupWindowTools
                .getInstance()
                .setActivity(this)
                .setTargetView(mDownloadMenu)
                .setItems("应用下载", "图片下载", "音视频下载", "压缩包下载", "NOTIFY_ONLY_COMPLETION", "NOTIFY_ONLY_COMPLETION", "MOBILE_AND_WIFI", "NETWORK_ONLY_WIFI")
                .setItemClickListener(new PopupWindowTools.PopupWindowItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        switch (position) {

                            case 0:

                                getHorizon().loadUrl("http://app.so.com/");

                                break;
                            case 1:

                                getHorizon().loadUrl("https://m.image.so.com/i?src=onebox_entity&q=%E9%AB%98%E5%9C%86%E5%9C%86");

                                break;
                            case 2:

                                getHorizon().loadUrl("http://app.so.com/");

                                break;
                            case 3:

                                getHorizon().loadUrl("http://app.so.com/");

                                break;
                            case 4:

                                getHorizon().getDownloadConfig().setNotificationType(NotificationType.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);

                                break;
                            case 5:

                                getHorizon().getDownloadConfig().setNotificationType(NotificationType.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                                break;
                            case 6:

                                getHorizon().getDownloadConfig().setNetworkType(NetworkType.NETWORK_MOBILE_AND_WIFI);

                                break;
                            case 7:

                                getHorizon().getDownloadConfig().setNetworkType(NetworkType.NETWORK_ONLY_WIFI);

                                break;
                        }
                    }
                })
                .show();
    }
}
