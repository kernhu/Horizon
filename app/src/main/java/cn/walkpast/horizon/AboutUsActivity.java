package cn.walkpast.horizon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: Kern
 * Time: 2019/1/14 14:52
 * Description: This is..
 */

public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.about_back)
    public ImageView mAboutBack;
    @BindView(R.id.about_version)
    public TextView mAboutVersion;
    @BindView(R.id.about_sponsor)
    public TextView mAboutSponsor;
    private Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);

        mAboutVersion.setText(BuildConfig.VERSION_NAME);
    }

    @OnClick({R.id.about_back, R.id.about_sponsor})
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.about_back) {

            finish();

        } else if (v.getId() == R.id.about_sponsor) {

            // mIntent=new Intent(this,)
        }
    }
}
