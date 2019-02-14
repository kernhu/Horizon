package cn.walkpast.core;

import android.app.Activity;
import android.content.Intent;

/**
 * Author: Kern
 * Time: 2019/2/14 15:27
 * Description: This is..
 */

public class UploadFileHelper {

    public static final int FILE_CHOOSER_RESULT_CODE = 1024 * 3;


    public static void openFileChooser(Activity activity) {

        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(i, "Horizon Chooser"), UploadFileHelper.FILE_CHOOSER_RESULT_CODE);

    }
}
