package cn.walkpast.core;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import java.util.UUID;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/2/3 10:55 PM
 * describe: This is...
 */

public class ImageSaveHelper {

    public static boolean saveBitmapToAlbum(Context context, Bitmap bm) {
        String uriStr = MediaStore.Images.Media.insertImage(context.getContentResolver(), bm, "", "");
        if (uriStr == null) {
            return false;
        }

        ContentResolver contentResolver = context.getContentResolver();
        ContentValues values = new ContentValues(4);
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        values.put(MediaStore.Images.Media.ORIENTATION, 0);
        values.put(MediaStore.Images.Media.DATA, generateFileName());
        contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        return true;
    }

    /**
     * get random filename by UUID；
     *
     * @return
     */
    private static String generateFileName() {
        return UUID.randomUUID().toString();
    }

}
