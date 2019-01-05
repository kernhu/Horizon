package cn.walkpast.horizon.competence;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebView;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.walkpast.core.client.HorizonClient;
import cn.walkpast.horizon.HorizonBaseActivity;
import cn.walkpast.horizon.R;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/6 1:50 AM
 * describe: This is...
 */

public class RedirectedActivity extends HorizonBaseActivity {


    private static final String TAG = "VideoPlayActivity";

    @BindView(R.id.frame_container)
    public FrameLayout mFrameContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_normal);
        ButterKnife.bind(this);

        String url = "https://s.click.tmall.com/g?et=wgnf%2BJ0nOClRNMnhp5l4zub%2B%2BwaytbnS&tar=https%3A%2F%2Fjx.tmall.com%2F%3Fali_trackid%3D2%3Amm_26632322_6858406_115094370%3A1546710800_256_1008866216%26e%3DFASQ8hZEbfVOVPhIvNgOIHfRMVAnf7HfPUa_G4-F9C-huKK8323HkzyHvGNuLkxACsVOAk1Z3t6GAnJv1aBSJRakgVc6i1DDpQlb-Oqnvv7UB_0s4PFJfgJpIxaK-kd4maVNObzoQkmTBYPjzDuOJIolYnKKv5OXahOeZxDaYYZWqLI3yUt2yihuRMccbyfo17Bv5bXA6do735YeP4ao_PVPy7vENfskeXX72mp250XDG_1N5hlzNg%26type%3D2%26tk_cps_param%3D26632322%26tkFlag%3D1&mb=e%3DFASQ8hZEbfVOVPhIvNgOIHfRMVAnf7HfPUa_G4-F9C-huKK8323HkzyHvGNuLkxACsVOAk1Z3t6GAnJv1aBSJRakgVc6i1DDpQlb-Oqnvv7UB_0s4PFJfgJpIxaK-kd4maVNObzoQkmTBYPjzDuOJIolYnKKv5OXahOeZxDaYYZWqLI3yUt2yihuRMccbyfo17Bv5bXA6do735YeP4ao_PVPy7vENfskeXX72mp250XDG_1N5hlzNg%26iv%3D0%26et%3D1546710800%26tk_cps_param%3D26632322%26tkFlag%3D1&op=1";

        getHorizon()
                .setHorizonClient(mHorizonClient)
                .setViewContainer(mFrameContainer)
                .setWebView(new WebView(this))
                .setOriginalUrl(url)
                .load();

    }

    HorizonClient mHorizonClient = new HorizonClient() {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onReceiveTitle(WebView view, String title) {
            super.onReceiveTitle(view, title);

            Log.e(TAG, "onReceiveTitle==" + title);
        }

        @Override
        public boolean onJSCallback(String scheme) {
            return super.onJSCallback(scheme);
        }

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            super.onDownloadStart(url, userAgent, contentDisposition, mimetype, contentLength);

        }
    };

}