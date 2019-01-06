package cn.walkpast.core.wakeup;

import android.annotation.SuppressLint;
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


    private static final String PAYMENT_WEIXIN = "weixin://";

    private static final String PAYMENT_ALIPAY = "alipays://";

    private static final String PAYMENT_MQQAPI = "mqqapi://";


    public static boolean isWakeupDialogShowing = false;
    private static WakeupHelper mWakeupHelper;
    private Activity mActivity;
    private String mScheme;
    private WakeupManager mWakeupManager;

    private boolean mSuccess = false;


    public static WakeupHelper getInstance() {

        if (mWakeupHelper == null) {
            mWakeupHelper = new WakeupHelper();
        }
        return mWakeupHelper;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public WakeupHelper setActivity(Activity activity) {
        mActivity = activity;
        return this;
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

            return callTel(getScheme());

        } else if (getScheme().startsWith("sms:")) {

            return sendSMS(getScheme());

        } else if (getScheme().startsWith(WebView.SCHEME_MAILTO)) {

            return sendEmail(getScheme());

        } else if (getScheme().startsWith(WebView.SCHEME_GEO)) {

            return sendGEO(getScheme());

        } else if (getScheme().startsWith("maps:")) {

            return sendMaps(getScheme());

        } else if (getScheme().startsWith(PAYMENT_WEIXIN)
                || getScheme().startsWith(PAYMENT_ALIPAY)
                || getScheme().startsWith(PAYMENT_MQQAPI)) {

            return payment(getScheme());

        } else {

            return deeplink(getScheme());

        }
    }

    /**
     * @param scheme
     */
    private boolean sendMaps(final String scheme) {

        mWakeupManager = new WakeupManager(getActivity());

        CommonDialog.getInstance()
                .setActivity(getActivity())
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
        return true;
    }


    /**
     * @param scheme
     */
    private boolean sendGEO(final String scheme) {

        mWakeupManager = new WakeupManager(getActivity());

        CommonDialog.getInstance()
                .setActivity(getActivity())
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
        return true;
    }

    /**
     * @param scheme
     * @param scheme
     */
    @SuppressLint("StringFormatMatches")
    private boolean sendEmail(final String scheme) {

        mWakeupManager = new WakeupManager(getActivity());

        CommonDialog.getInstance()
                .setActivity(getActivity())
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

        return true;
    }


    /**
     * @param scheme
     * @param scheme
     */
    @SuppressLint("StringFormatMatches")
    private boolean sendSMS(final String scheme) {

        mWakeupManager = new WakeupManager(getActivity());

        CommonDialog.getInstance()
                .setActivity(getActivity())
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
        return true;
    }


    /**
     * @param scheme
     * @param scheme
     */
    @SuppressLint("StringFormatMatches")
    private boolean callTel(final String scheme) {

        mWakeupManager = new WakeupManager(getActivity());

        CommonDialog.getInstance()
                .setActivity(getActivity())
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
        return true;
    }


    private boolean payment(String scheme){

      return   mWakeupManager.commonLink(scheme);
    }

    /********************************************************************************/

    /**
     * @param deeplink
     */
    private boolean deeplink(String deeplink) {

        if (!WakeupHelper.isWakeupDialogShowing) {
            WakeupHelper.isWakeupDialogShowing = true;
            mWakeupManager = new WakeupManager(getActivity());
            String target = mWakeupManager.getDeeplinkTarget(deeplink);
            if (target == null) {
                return true;
            }

            CommonDialog.getInstance()
                    .setActivity(getActivity())
                    .setTitle(mActivity.getString(R.string.wakeup_title))
                    .setMessage(String.format(mActivity.getString(R.string.deeplink_message), mWakeupManager.getUri(deeplink), TextUtils.isEmpty(target) ? mActivity.getString(R.string.wakeup_unkonw) : target))
                    .setPositiveBtn(mActivity.getString(R.string.wakeup_allow))
                    .setPositiveListener(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            WakeupHelper.isWakeupDialogShowing = false;
                            mSuccess = mWakeupManager.deeplink();

                        }
                    })
                    .setNegativeBtn(mActivity.getString(R.string.wakeup_refuse))
                    .setNegativeListener(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            WakeupHelper.isWakeupDialogShowing = false;
                        }
                    })
                    .show();
        }
        return true;
    }

}
