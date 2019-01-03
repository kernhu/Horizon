package cn.walkpast.core.wakeup;

import android.app.Activity;
import android.content.DialogInterface;
import android.text.TextUtils;

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
    private WakeupManager mWakeupManager;

    public Activity getActivity() {
        return mActivity;
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }


    public void wakeup() {


    }


    /**
     * @param requestor
     * @param target
     */
    private void sendMaps(String requestor, final String target) {

        mWakeupManager = new WakeupManager(getActivity());

        CommonDialog.getInstance()
                .setTitle(mActivity.getString(R.string.wakeup_title))
                .setMessage(String.format(mActivity.getString(R.string.call_maps_message), mWakeupManager.getUri(requestor), TextUtils.isEmpty(target) ? mActivity.getString(R.string.wakeup_unkonw) : target))
                .setPositiveBtn(mActivity.getString(R.string.wakeup_allow))
                .setPositiveListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mWakeupManager.commonLink(target);

                    }
                })
                .setNegativeBtn(mActivity.getString(R.string.wakeup_refuse))
                .show();

    }


    /**
     * @param requestor
     * @param target
     */
    private void sendGEO(String requestor, final String target) {

        mWakeupManager = new WakeupManager(getActivity());

        CommonDialog.getInstance()
                .setTitle(mActivity.getString(R.string.wakeup_title))
                .setMessage(String.format(mActivity.getString(R.string.request_geo_message), mWakeupManager.getUri(requestor), TextUtils.isEmpty(target) ? mActivity.getString(R.string.wakeup_unkonw) : target))
                .setPositiveBtn(mActivity.getString(R.string.wakeup_allow))
                .setPositiveListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mWakeupManager.commonLink(target);

                    }
                })
                .setNegativeBtn(mActivity.getString(R.string.wakeup_refuse))
                .show();

    }

    /**
     * @param requestor
     * @param target
     */
    private void sendEmail(String requestor, final String target) {

        mWakeupManager = new WakeupManager(getActivity());

        CommonDialog.getInstance()
                .setTitle(mActivity.getString(R.string.wakeup_title))
                .setMessage(String.format(mActivity.getString(R.string.send_email_message), mWakeupManager.getUri(requestor), TextUtils.isEmpty(target) ? mActivity.getString(R.string.wakeup_unkonw) : target))
                .setPositiveBtn(mActivity.getString(R.string.wakeup_allow))
                .setPositiveListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mWakeupManager.commonLink(target);

                    }
                })
                .setNegativeBtn(mActivity.getString(R.string.wakeup_refuse))
                .show();

    }


    /**
     * @param requestor
     * @param target
     */
    private void sendSMS(String requestor, final String target) {

        mWakeupManager = new WakeupManager(getActivity());

        CommonDialog.getInstance()
                .setTitle(mActivity.getString(R.string.wakeup_title))
                .setMessage(String.format(mActivity.getString(R.string.send_sms_message), mWakeupManager.getUri(requestor), TextUtils.isEmpty(target) ? mActivity.getString(R.string.wakeup_unkonw) : target))
                .setPositiveBtn(mActivity.getString(R.string.wakeup_allow))
                .setPositiveListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mWakeupManager.commonLink(target);

                    }
                })
                .setNegativeBtn(mActivity.getString(R.string.wakeup_refuse))
                .show();

    }


    /**
     * @param requestor
     * @param target
     */
    private void callTel(String requestor, final String target) {

        mWakeupManager = new WakeupManager(getActivity());

        CommonDialog.getInstance()
                .setTitle(mActivity.getString(R.string.wakeup_title))
                .setMessage(String.format(mActivity.getString(R.string.call_tel_message),  mWakeupManager.getUri(requestor), TextUtils.isEmpty(target) ? mActivity.getString(R.string.wakeup_unkonw) : target))
                .setPositiveBtn(mActivity.getString(R.string.wakeup_allow))
                .setPositiveListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mWakeupManager.commonLink(target);

                    }
                })
                .setNegativeBtn(mActivity.getString(R.string.wakeup_refuse))
                .show();

    }


    /********************************************************************************/

    /**
     * @param deeplink
     */
    private void authorizeDeeplink(String deeplink) {

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
