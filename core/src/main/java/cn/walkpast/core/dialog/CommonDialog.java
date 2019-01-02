package cn.walkpast.core.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Author: Kern
 * Time: 2019/1/2 20:59
 * Description: This is..
 */

public class CommonDialog {

    private Activity activity;
    private String title;
    private String message;
    private String positiveBtn;
    private String negativeBtn;
    private DialogInterface.OnClickListener positiveListener;
    private DialogInterface.OnClickListener negativeListener;


    public Activity getActivity() {
        return activity;
    }

    public CommonDialog setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CommonDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getPositiveBtn() {
        return positiveBtn;
    }

    public CommonDialog setPositiveBtn(String positiveBtn) {
        this.positiveBtn = positiveBtn;
        return this;
    }

    public String getNegativeBtn() {
        return negativeBtn;
    }

    public CommonDialog setNegativeBtn(String negativeBtn) {
        this.negativeBtn = negativeBtn;
        return this;
    }

    public DialogInterface.OnClickListener getPositiveListener() {
        return positiveListener;
    }

    public CommonDialog setPositiveListener(DialogInterface.OnClickListener positiveListener) {
        this.positiveListener = positiveListener;
        return this;
    }

    public DialogInterface.OnClickListener getNegativeListener() {
        return negativeListener;
    }

    public CommonDialog setNegativeListener(DialogInterface.OnClickListener negativeListener) {
        this.negativeListener = negativeListener;
        return this;
    }


    public void show() {

        showDialog(getActivity(), getTitle(), getMessage(), getPositiveBtn(), getNegativeBtn(), getPositiveListener(), getNegativeListener());

    }

    public void showDialog(Context context, String title, String message, String positiveBtn, String negativeBtn, DialogInterface.OnClickListener positiveListener, final DialogInterface.OnClickListener negativeListener) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveBtn,
                positiveListener);

        builder.setNegativeButton(negativeBtn,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (negativeListener != null) {
                            negativeListener.onClick(dialog, which);
                        }
                    }
                });
        builder.show();
    }
}
