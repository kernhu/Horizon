package cn.walkpast.horizon.competence;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.walkpast.horizon.R;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/1 4:49 PM
 * describe: This is...
 */

public class BasicFunFragmentActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.bff_bottom_nav_first)
    public TextView mBbnFirst;
    @BindView(R.id.bff_bottom_nav_second)
    public TextView mBbnSecond;
    @BindView(R.id.bff_frame)
    public FrameLayout mBffFrame;

    private BasicFunFirstFragment mBasicFunFirstFragment;
    private BasicFunSecondFragment mBasicFunSecondFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_basic_fun_fragment);
        ButterKnife.bind(this);

        mBasicFunFirstFragment = new BasicFunFirstFragment();
        mBasicFunSecondFragment = new BasicFunSecondFragment();
        onClick(mBbnFirst);
    }


    @OnClick({R.id.bff_bottom_nav_first, R.id.bff_bottom_nav_second})
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.bff_bottom_nav_first) {

            if (!mBasicFunFirstFragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().add(R.id.bff_frame, mBasicFunFirstFragment).hide(mBasicFunSecondFragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().show(mBasicFunFirstFragment).hide(mBasicFunSecondFragment).commit();
            }

        } else if (v.getId() == R.id.bff_bottom_nav_second) {

            if (!mBasicFunSecondFragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().add(R.id.bff_frame, mBasicFunSecondFragment).hide(mBasicFunFirstFragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().show(mBasicFunSecondFragment).hide(mBasicFunFirstFragment).commit();
            }

        }
    }
}
