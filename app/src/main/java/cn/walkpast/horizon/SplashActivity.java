package cn.walkpast.horizon;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;
import com.qq.e.comm.util.AdError;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.walkpast.horizon.constants.GdtAd;
import cn.walkpast.utils.ToastUtils;

/**
 * Author: Kern
 * Time: 2019/1/25 14:26
 * Description: This is..
 */

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SKIP_TEXT = "点击 %d";

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @BindView(R.id.splash_container)
    public ViewGroup mSplashContainer;
    @BindView(R.id.splash_skip)
    public TextView mSplashSkip;

    private SplashAD mSplashAD;
    private Intent mIntent;
    private boolean mCanJump = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        /*******************************************  权限 ************************************************/
        if (Build.VERSION.SDK_INT >= 23) {
            checkAndRequestPermission();
        } else {
            fetchSplashAD(this, mSplashContainer, mSplashSkip, GdtAd.APP_ID, GdtAd.SPLASH_POS_ID, mSplashADListener, 0);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCanJump = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCanJump) {
            skipNext();
        }
        mCanJump = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1024 && hasAllPermissionsGranted(grantResults)) {
            fetchSplashAD(this, mSplashContainer, mSplashSkip, GdtAd.APP_ID, GdtAd.SPLASH_POS_ID, mSplashADListener, 0);
        } else {
            ToastUtils.showLong("应用缺少必要的权限！请点击\"权限\"，打开所需要的权限。");
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            finish();
        }
    }

    private boolean hasAllPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }


    @OnClick(R.id.splash_skip)
    @Override
    public void onClick(View v) {

        skipNext();
    }

    private void skipNext() {

        mIntent = new Intent(this, MainActivity.class);
        startActivity(mIntent);
        finish();

    }

    /***********************************************************************************************************************/
    @TargetApi(Build.VERSION_CODES.M)
    private void checkAndRequestPermission() {
        List<String> lackedPermission = new ArrayList<String>();
        if (!(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (!(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (lackedPermission.size() == 0) {
            fetchSplashAD(this, mSplashContainer, mSplashSkip, GdtAd.APP_ID, GdtAd.SPLASH_POS_ID, mSplashADListener, 0);
        } else {
            String[] requestPermissions = new String[lackedPermission.size()];
            lackedPermission.toArray(requestPermissions);
            requestPermissions(requestPermissions, 1024);
        }
    }

    /***********************************************************************************************************************/
    private void fetchSplashAD(Activity activity, ViewGroup adContainer, View skipContainer, String appId, String posId, SplashADListener adListener, int fetchDelay) {
        mSplashAD = new SplashAD(activity, adContainer, skipContainer, appId, posId, adListener, fetchDelay);
    }

    SplashADListener mSplashADListener = new SplashADListener() {
        @Override
        public void onADDismissed() {
            if (mCanJump) {
                skipNext();
            } else {
                mCanJump = true;
            }
        }

        @Override
        public void onNoAD(AdError adError) {
            Log.i("AD_DEMO", "onNoAD");
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    skipNext();
                }
            }, 1000);
        }

        @Override
        public void onADPresent() {
            mSplashSkip.setVisibility(View.VISIBLE);
            mSplashContainer.setVisibility(View.VISIBLE);
            Log.i("AD_DEMO", "SplashADPresent");
        }

        @Override
        public void onADClicked() {
            Log.i("AD_DEMO", "onADClicked");
        }

        @Override
        public void onADTick(long l) {
            Log.i("AD_DEMO", "onADTick===" + l);
            mSplashSkip.setText(String.format(SKIP_TEXT, l / 1000L));
        }

        @Override
        public void onADExposure() {
            Log.i("AD_DEMO", "onADExposure");
        }
    };
}
