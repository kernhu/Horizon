package cn.walkpast.core.wakeup;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;

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


    public Activity getActivity() {
        return mActivity;
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }



    public void wakeup(){



    }

    public void authorize(String requestor, String target) {


        CommonDialog.getInstance()
                .setTitle(mActivity.getString(R.string.wakeup_title))
                .setMessage(String.format(mActivity.getString(R.string.wakeup_message), requestor, target))
                .setPositiveBtn(mActivity.getString(R.string.wakeup_allow))
                .setPositiveListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        wakeup();

                    }
                })
                .setNegativeBtn(mActivity.getString(R.string.wakeup_refuse))
                .show();


    }

}
