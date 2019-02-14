package cn.walkpast.horizon.competence;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.walkpast.core.WebHorizonActivity;
import cn.walkpast.horizon.R;

/**
 * Author: Kern
 * Time: 2019/1/15 21:07
 * Description: This is..
 */

public class CallupActivity extends WebHorizonActivity {

    @BindView(R.id.callup_title)
    public TextView mCallupTitle;
    @BindView(R.id.callup_container)
    public FrameLayout mCallupContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_callup);
        ButterKnife.bind(this);

        mCallupTitle.setText("打电话/发信息/发邮件");

        getHorizon()
                .setViewContainer(mCallupContainer)
                .preview()
                .loadUrl("file:///android_asset/callup/callup_normal.html");

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
    }

}
