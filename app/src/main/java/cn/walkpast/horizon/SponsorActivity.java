package cn.walkpast.horizon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.walkpast.core.WebHorizonActivity;

/**
 * Author: Kern
 * Time: 2019/2/13 11:00
 * Description: This is..
 */

public class SponsorActivity extends WebHorizonActivity implements View.OnClickListener {


    private String URL = "https://github.com/KernHu/Horizon/blob/master/app/info/sponsor_list";

    @BindView(R.id.sponsor_back)
    public ImageView mSponsorBack;
    @BindView(R.id.sponsor_container)
    public FrameLayout mSponsorContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sponsor);
        ButterKnife.bind(this);

        getHorizon()
                .setViewContainer(mSponsorContainer)
                .preview()
                .loadUrl(URL);
    }


    @OnClick(R.id.sponsor_back)
    @Override
    public void onClick(View v) {
        finish();
    }
}
