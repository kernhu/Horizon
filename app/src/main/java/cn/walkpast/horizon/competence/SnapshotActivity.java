package cn.walkpast.horizon.competence;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.walkpast.core.Horizon;
import cn.walkpast.core.client.HorizonClient;
import cn.walkpast.core.config.CoreConfig;
import cn.walkpast.core.config.DownloadConfig;
import cn.walkpast.core.constant.CaptureStrategy;
import cn.walkpast.core.indicator.ProgressConfig;
import cn.walkpast.horizon.R;
import cn.walkpast.horizon.widget.PopupWindowTools;

/**
 * Author: Kern
 * Time: 2019/1/27 15:31
 * Description: This is..
 */

public class SnapshotActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.snapshot_icon)
    public ImageView mSnapshotIcon;
    @BindView(R.id.snapshot_menu)
    public ImageView mSnapshotMenu;
    @BindView(R.id.snapshot_title)
    public TextView mSnapshotTitle;
    @BindView(R.id.snapshot_container)
    public FrameLayout mSnapshotContainer;
    @BindView(R.id.snapshot_image)
    public ImageView mSnapshotImage;

    private Horizon mHorizon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_snapshot);
        ButterKnife.bind(this);


        mHorizon = Horizon.with(this)
                .setProgressConfig(ProgressConfig
                        .with(this)
                        .config()
                )
                .setCoreConfig(CoreConfig
                        .with(this)
                        .config()
                )
                .setDownloadConfig(DownloadConfig
                        .with(this)
                        .config()
                )
                .setCaptureStrategy(CaptureStrategy.START_FINISH)
                .setHorizonClient(mHorizonClient)
                .setViewContainer(mSnapshotContainer)
                .setOriginalUrl("https://www.bilibili.com/")
                .preview();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mHorizon != null) {
            mHorizon.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mHorizon != null) {
            mHorizon.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mHorizon != null) {
            mHorizon.onStop();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHorizon != null) {
            mHorizon.onDestroy();
        }
    }


    @OnClick(R.id.snapshot_menu)
    @Override
    public void onClick(View v) {

        PopupWindowTools
                .getInstance()
                .setActivity(this)
                .setTargetView(mSnapshotMenu)
                .setItems("NEVER", "FINISH", "START_FINISH", "START_MIDDLE_FINISH", "")
                .setItemClickListener(new PopupWindowTools.PopupWindowItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                })
                .show();
    }


    HorizonClient mHorizonClient = new HorizonClient() {

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);

            mSnapshotIcon.setImageBitmap(icon);
        }

        @Override
        public void onReceiveTitle(WebView view, String title) {
            super.onReceiveTitle(view, title);

            mSnapshotTitle.setText(title);
        }

        @Override
        public void onCaptured(Bitmap bitmap) {
            super.onCaptured(bitmap);

            mSnapshotImage.setImageBitmap(bitmap);
        }
    };
}
