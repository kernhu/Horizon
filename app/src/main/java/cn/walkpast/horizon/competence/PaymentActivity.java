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
 * data_time: 2019/1/6 5:17 PM
 * describe: This is...
 */

public class PaymentActivity extends WebHorizonActivity {

    private static final String TAG = "PaymentActivity";

    @BindView(R.id.payment_title)
    public TextView mPaymentTitle;
    @BindView(R.id.payment_container)
    public FrameLayout mPaymentContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);

        getHorizon()
                .setHorizonClient(mHorizonClient)
                .setViewContainer(mPaymentContainer)
                .setWebView(new WebView(this))
                .setOriginalUrl("https://mst.vip.com/keS0_lNPJ_KHgau2Uxq4zg.php?client=mp&wapid=mst_3092239&extra_type=1")
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

            mPaymentTitle.setText(title);

        }

    };

}
