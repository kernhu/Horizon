package cn.walkpast.core.wakeup;

import android.app.Activity;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.webkit.WebView;

import cn.walkpast.core.R;
import cn.walkpast.core.dialog.CommonDialog;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/2 10:19 PM
 * describe: This is...
 */

public class WakeupHelper {

    private Activity mActivity;
    private String mScheme;
    private WakeupManager mWakeupManager;

    public Activity getActivity() {
        return mActivity;
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }


    public String getScheme() {
        return mScheme;
    }

    public WakeupHelper setScheme(String scheme) {
        mScheme = scheme;
        return this;
    }

    public boolean wakeup() {

        if (getScheme().startsWith(WebView.SCHEME_TEL)) {

            callTel(getScheme());

        } else if (getScheme().startsWith("sms:")) {

            sendSMS(getScheme());

        } else if (getScheme().startsWith(WebView.SCHEME_MAILTO)) {

            sendEmail(getScheme());

        } else if (getScheme().startsWith(WebView.SCHEME_GEO)) {

            sendGEO(getScheme());
            return true;

        } else if (getScheme().startsWith("maps:")) {

            sendMaps(getScheme());
            return true;

        } else {

            deeplink(getScheme());
            return true;
        }
        return false;
    }

    /**
     * @param scheme
     */
    private void sendMaps(final String scheme) {

        mWakeupManager = new WakeupManager(getActivity());

        CommonDialog.getInstance()
                .setTitle(mActivity.getString(R.string.wakeup_title))
                .setMessage(String.format(mActivity.getString(R.string.call_maps_message), mWakeupManager.getUri(scheme), TextUtils.isEmpty(scheme) ? mActivity.getString(R.string.wakeup_unkonw) : scheme))
                .setPositiveBtn(mActivity.getString(R.string.wakeup_allow))
                .setPositiveListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mWakeupManager.commonLink(scheme);

                    }
                })
                .setNegativeBtn(mActivity.getString(R.string.wakeup_refuse))
                .show();

    }


    /**
     * @param scheme
     */
    private void sendGEO(final String scheme) {

        mWakeupManager = new WakeupManager(getActivity());

        CommonDialog.getInstance()
                .setTitle(mActivity.getString(R.string.wakeup_title))
                .setMessage(String.format(mActivity.getString(R.string.request_geo_message), mWakeupManager.getUri(scheme), TextUtils.isEmpty(scheme) ? mActivity.getString(R.string.wakeup_unkonw) : scheme))
                .setPositiveBtn(mActivity.getString(R.string.wakeup_allow))
                .setPositiveListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mWakeupManager.commonLink(scheme);

                    }
                })
                .setNegativeBtn(mActivity.getString(R.string.wakeup_refuse))
                .show();

    }

    /**
     * @param scheme
     * @param scheme
     */
    private void sendEmail(final String scheme) {

        mWakeupManager = new WakeupManager(getActivity());

        CommonDialog.getInstance()
                .setTitle(mActivity.getString(R.string.wakeup_title))
                .setMessage(String.format(mActivity.getString(R.string.send_email_message), mWakeupManager.getUri(scheme), TextUtils.isEmpty(scheme) ? mActivity.getString(R.string.wakeup_unkonw) : scheme))
                .setPositiveBtn(mActivity.getString(R.string.wakeup_allow))
                .setPositiveListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mWakeupManager.commonLink(scheme);

                    }
                })
                .setNegativeBtn(mActivity.getString(R.string.wakeup_refuse))
                .show();

    }


    /**
     * @param scheme
     * @param scheme
     */
    private void sendSMS(final String scheme) {

        mWakeupManager = new WakeupManager(getActivity());

        CommonDialog.getInstance()
                .setTitle(mActivity.getString(R.string.wakeup_title))
                .setMessage(String.format(mActivity.getString(R.string.send_sms_message), mWakeupManager.getUri(scheme), TextUtils.isEmpty(scheme) ? mActivity.getString(R.string.wakeup_unkonw) : scheme))
                .setPositiveBtn(mActivity.getString(R.string.wakeup_allow))
                .setPositiveListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mWakeupManager.commonLink(scheme);

                    }
                })
                .setNegativeBtn(mActivity.getString(R.string.wakeup_refuse))
                .show();

    }


    /**
     * @param scheme
     * @param scheme
     */
    private void callTel(final String scheme) {

        mWakeupManager = new WakeupManager(getActivity());

        CommonDialog.getInstance()
                .setTitle(mActivity.getString(R.string.wakeup_title))
                .setMessage(String.format(mActivity.getString(R.string.call_tel_message), mWakeupManager.getUri(scheme), TextUtils.isEmpty(scheme) ? mActivity.getString(R.string.wakeup_unkonw) : scheme))
                .setPositiveBtn(mActivity.getString(R.string.wakeup_allow))
                .setPositiveListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mWakeupManager.commonLink(scheme);

                    }
                })
                .setNegativeBtn(mActivity.getString(R.string.wakeup_refuse))
                .show();

    }


    /********************************************************************************/

    /**
     * @param deeplink
     */
    private void deeplink(String deeplink) {

        mWakeupManager = new WakeupManager(getActivity());
        String target = mWakeupManager.getDeeplinkTarget(deeplink);

        CommonDialog.getInstance()
                .setTitle(mActivity.getString(R.string.wakeup_title))
                .setMessage(String.format(mActivity.getString(R.string.deeplink_message), mWakeupManager.getUri(deeplink), TextUtils.isEmpty(target) ? mActivity.getString(R.string.wakeup_unkonw) : target))
                .setPositiveBtn(mActivity.getString(R.string.wakeup_allow))
                .setPositiveListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mWakeupManager.deeplink();

                    }
                })
                .setNegativeBtn(mActivity.getString(R.string.wakeup_refuse))
                .show();
    }

}
