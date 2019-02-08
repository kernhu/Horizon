package cn.walkpast.core.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/2/6 7:55 AM
 * describe: This is...
 */

public class Tooltip {


    private Activity activity;
    private String title;
    private String message;
    private String negativeBtn;
    private DialogInterface.OnClickListener negativeListener;

    public static CommonDialog getInstance() {

        return new CommonDialog();
    }

    public Activity getActivity() {
        return activity;
    }

    public Tooltip setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Tooltip setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Tooltip setMessage(String message) {
        this.message = message;
        return this;
    }


    public String getNegativeBtn() {
        return negativeBtn;
    }

    public Tooltip setNegativeBtn(String negativeBtn) {
        this.negativeBtn = negativeBtn;
        return this;
    }

    public DialogInterface.OnClickListener getNegativeListener() {
        return negativeListener;
    }

    public Tooltip setNegativeListener(DialogInterface.OnClickListener negativeListener) {
        this.negativeListener = negativeListener;
        return this;
    }

    public void show() {

        showDialog(getActivity(), getTitle(), getMessage(), getNegativeBtn(), getNegativeListener());
    }

    public void showDialog(Context context, String title, String message, String negativeBtn, final DialogInterface.OnClickListener negativeListener) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        mBuilder.setTitle(title);
        mBuilder.setMessage(message);

        mBuilder.setNegativeButton(negativeBtn,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (negativeListener != null) {
                            negativeListener.onClick(dialog, which);
                        }
                        dialog.dismiss();
                    }
                });

        mBuilder.show();
    }

}
