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
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/6 1:50 AM
 * describe: This is...
 */

public class MapActivity extends WebHorizonActivity {


    private static final String TAG = "MapActivity";

    @BindView(R.id.map_title)
    public TextView mMapTitle;
    @BindView(R.id.map_container)
    public FrameLayout mMapContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);

        getHorizon()
                .setHorizonClient(mHorizonClient)
                .setViewContainer(mMapContainer)
                .setWebView(new WebView(this))
                .setOriginalUrl("https://map.baidu.com/mobile/webapp/index/index/?third_party=hao123")
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

            mMapTitle.setText(title);
        }

    };

}
