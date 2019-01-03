package cn.walkpast.core.wakeup;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

/**
 * Author: Kern
 * Time: 2019/1/3 10:51
 * Description: This is..
 */

public class WakeupManager {

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
            } catch (android.content.ActivityNotFoundException e) {
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
                Logger.e("error", "callLookup-Throwable=" + ignore.toString());
            }
        }
        return null;
    }

    public void deeplink() {

        if (mIntent != null) {
            mActivity.startActivity(mIntent);
        }

    }

}
