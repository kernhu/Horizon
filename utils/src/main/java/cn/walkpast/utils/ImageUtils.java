package cn.walkpast.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Author: Kern
 * Time: 2019/1/16 20:10
 * Description: This is..
 */

public class ImageUtils {


    /**
     * 压缩图片
     *
     * @param bm
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap scaleImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 设置想要的大小
        int newWidth1 = newWidth;
        int newHeight1 = newHeight;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth1) / width;
        float scaleHeight = ((float) newHeight1) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        matrix.postRotate(0);

        return Bitmap.createScaledBitmap(bm, newWidth, newHeight, false);
    }


    /**
     * 存图片到sdcard
     *
     * @author Michael.Zhang
     * @param
     */
    public static void storeInSD(Bitmap bitmap, String img_name) {
        File file = new File("horizon");
        File imageFile = new File(file, img_name);
        try {
            imageFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
