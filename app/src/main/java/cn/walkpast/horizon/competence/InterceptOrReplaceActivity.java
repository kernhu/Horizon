package cn.walkpast.horizon.competence;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
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
import cn.walkpast.core.constant.FilterType;
import cn.walkpast.core.indicator.ProgressConfig;
import cn.walkpast.horizon.R;
import cn.walkpast.horizon.widget.PopupWindowTools;
import cn.walkpast.utils.ToastUtils;

/**
 * Author: Kern
 * Time: 2019/1/28 14:02
 * Description: This is..
 */

public class InterceptOrReplaceActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ior_icon)
    public ImageView mIorIcon;
    @BindView(R.id.ior_menu)
    public ImageView mIorMenu;
    @BindView(R.id.ior_title)
    public TextView mIorTitle;
    @BindView(R.id.ior_container)
    public FrameLayout mIorContainer;
    @BindView(R.id.ior_intercept_url)
    public EditText mIorInterceptEdit;
    @BindView(R.id.ior_replace_url)
    public EditText mIorReplaceEdit;

    private Horizon mHorizon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intercept_or_replace);
        ButterKnife.bind(this);

        mHorizon = Horizon.with(this)
                .setProgressConfig(ProgressConfig
                        .with(this)
                        .config()
                )
                .setCoreConfig(CoreConfig
                        .with(this)
                        .setFilterList(FilterType.TYPE_MATCH_URL, "https://www.baidu.com/", "https://www.iconfont.cn/", "https://modao.cc/signin")
                        //.setFilterList(FilterType.TYPE_CONTAINS_URL, "https://github.com/KernHu", "https://www.iconfont.cn/", "https://modao.cc/")
                        //.setFilterList(FilterType.TYPE_START_WITH, "https://github.com/KernHu", "https://www.iconfont.cn/", "https://modao.cc/signin")
                        //.setFilterList(FilterType.TYPE_MATCH_HOST, "https://github.com/KernHu", "modao.cc", "www.google-analytics.com", "zgsdk.zhugeio.com", "qiyukf.com", "da.qiyukf.com")
                        .config()
                )
                .setDownloadConfig(DownloadConfig
                        .with(this)
                        .config()
                )
                .setCaptureStrategy(CaptureStrategy.START_FINISH)
                .setHorizonClient(mHorizonClient)
                .setViewContainer(mIorContainer)
                .setOriginalUrl("https://modao.cc/signin")
                .preview();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (mHorizon != null) {
            mHorizon.onTrimMemory(level);
        }
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


    HorizonClient mHorizonClient = new HorizonClient() {

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);

            mIorIcon.setImageBitmap(icon);
        }

        @Override
        public void onReceiveTitle(WebView view, String title) {
            super.onReceiveTitle(view, title);

            mIorTitle.setText(title);
        }
    };


    @OnClick(R.id.ior_menu)
    @Override
    public void onClick(View v) {

        PopupWindowTools
                .getInstance()
                .setActivity(this)
                .setTargetView(mIorMenu)
                .setItems("TYPE_MATCH_HOST", "TYPE_START_WITH", "TYPE_MATCH_URL", "TYPE_CONTAINS_URL")
                .setItemClickListener(new PopupWindowTools.PopupWindowItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        String intercept_url = mIorInterceptEdit.getEditableText().toString().replace(" ", "").trim();
                        String replace_url = mIorReplaceEdit.getEditableText().toString().replace(" ", "").trim();

                        if (TextUtils.isEmpty(intercept_url)) {

                            ToastUtils.showLong(R.string.ior_toast_edit_empty);

                            return;
                        }

                        switch (position) {

                            case 0:

                                mHorizon.getCoreConfig().setFilterList(FilterType.TYPE_MATCH_HOST, replace_url, intercept_url);

                                break;
                            case 1:

                                mHorizon.getCoreConfig().setFilterList(FilterType.TYPE_START_WITH, replace_url, intercept_url);

                                break;
                            case 2:

                                mHorizon.getCoreConfig().setFilterList(FilterType.TYPE_MATCH_URL, replace_url, intercept_url);

                                break;
                            case 3:

                                mHorizon.getCoreConfig().setFilterList(FilterType.TYPE_CONTAINS_URL, replace_url, intercept_url);

                                break;
                        }
                        mHorizon.loadUrl(intercept_url);
                    }
                })
                .show();

    }
}
