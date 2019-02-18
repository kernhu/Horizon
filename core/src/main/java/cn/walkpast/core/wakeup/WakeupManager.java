package cn.walkpast.core.wakeup;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;

import cn.walkpast.utils.LogUtils;
import cn.walkpast.utils.ToastUtils;

/**
 * Author: Kern
 * Time: 2019/1/3 10:51
 * Description: This is..
 */

public class WakeupManager {

    private static final String TAG = "WakeupManager";
    private Activity mActivity;
    private Intent mIntent;

    public WakeupManager(Activity activity) {
        mActivity = activity;
    }


    public String getUri(String scheme) {

        Uri uri = Uri.parse(scheme);
        return uri.getScheme();
    }


    /**
     * 跳转拨号键、邮件、短信、位置、地图
     *
     * @param url 需要跳转的url
     */
    public boolean commonLink(String url) {

        if (!TextUtils.isEmpty(url)) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(url));
                mActivity.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException e) {
                ToastUtils.showShort("暂不支持，请先安装该应用");
                return false;
            }
        }
        return false;
    }


    /**
     * @param deeplink
     */
    public String getDeeplinkTarget(String deeplink) {

        if (!TextUtils.isEmpty(deeplink)) {
            String scheme_url = deeplink.split(";")[0];
            try {
                PackageManager packageManager = mActivity.getPackageManager();
                mIntent = Intent.parseUri(scheme_url, Intent.URI_INTENT_SCHEME);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ResolveInfo info = packageManager.resolveActivity(mIntent, PackageManager.MATCH_DEFAULT_ONLY);
                if (info != null) {
                    String mAppLable = info.loadLabel(packageManager).toString();
                    return mAppLable;
                }
            } catch (Throwable ignore) {
                ignore.printStackTrace();
                LogUtils.e(TAG, "callLookup-Throwable=" + ignore.toString());
            }
        }
        return null;
    }

    public boolean deeplink() {
        try {

            mActivity.startActivity(mIntent);

            return true;
        } catch (Exception e) {

        }
        return false;
    }

}
