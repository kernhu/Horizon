package cn.walkpast.core;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import cn.walkpast.core.constant.EventPoint;
import cn.walkpast.core.widget.ItemLongClickedPopWindow;
import cn.walkpast.utils.DensityUtil;
import cn.walkpast.utils.ToastUtils;
import cn.walkpast.utils.permission.PermissionUtil;
import cn.walkpast.utils.permission.callback.PermissionResultCallBack;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/6 11:04 AM
 * describe: This is...
 */

public class ImageDownloadHelper {

    private Activity mActivity;
    private View mTargetView;
    private String mImageUrl;
    private String mDownloadPath;


    public static ImageDownloadHelper getInstance() {

        return new ImageDownloadHelper();
    }


    public Activity getActivity() {
        return mActivity;
    }

    public ImageDownloadHelper setActivity(Activity activity) {
        mActivity = activity;
        return this;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public View getTargetView() {
        return mTargetView;
    }

    public ImageDownloadHelper setTargetView(View targetView) {
        mTargetView = targetView;
        return this;
    }

    public ImageDownloadHelper setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
        return this;
    }

    public String getDownloadPath() {
        return mDownloadPath;
    }

    public ImageDownloadHelper setDownloadPath(String downloadPath) {
        mDownloadPath = downloadPath;
        return this;
    }

    public void load() {


        final ItemLongClickedPopWindow itemLongClickedPopWindow = new ItemLongClickedPopWindow(getActivity(),
                ItemLongClickedPopWindow.IMAGE_VIEW_POPUPWINDOW, DensityUtil.dp2px(120), DensityUtil.dp2px(90));
        itemLongClickedPopWindow.showAtLocation(getTargetView(), Gravity.TOP | Gravity.LEFT, EventPoint.downX, EventPoint.downY + 10);
        itemLongClickedPopWindow.getView(R.id.item_longclicked_viewImage)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemLongClickedPopWindow.dismiss();
                    }
                });
        itemLongClickedPopWindow.getView(R.id.item_longclicked_saveImage)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemLongClickedPopWindow.dismiss();

                        PermissionUtil
                                .getInstance()
                                .request(getActivity(),
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NOTIFICATION_POLICY},
                                        new PermissionResultCallBack() {
                                            @Override
                                            public void onPermissionGranted() {

                                            }

                                            @Override
                                            public void onPermissionGranted(String... permissions) {
                                            }

                                            @Override
                                            public void onPermissionDenied(String... permissions) {

                                                return;
                                            }

                                            @Override
                                            public void onRationalShow(String... permissions) {

                                            }
                                        });


                        new SaveImage(getImageUrl()).execute();
                    }
                });
    }


    /**
     * download image
     */
    private class SaveImage extends AsyncTask<String, Void, String> {

        private String imageUrl;

        public SaveImage(String imageUrl) {
            this.imageUrl = imageUrl;

        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                String sdcard = Environment.getExternalStorageDirectory().toString();
                File file = new File(sdcard + "/Download");
                if (!file.exists()) {
                    file.mkdirs();
                }
                int idx = imageUrl.lastIndexOf(".");
                String ext = imageUrl.substring(idx);
                file = new File(sdcard + "/Download/" + new Date().getTime() + ext);
                InputStream inputStream = null;
                URL url = new URL(imageUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(20000);
                if (conn.getResponseCode() == 200) {
                    inputStream = conn.getInputStream();
                }
                byte[] buffer = new byte[4096];
                int len = 0;
                FileOutputStream outStream = new FileOutputStream(file);
                while ((len = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                outStream.close();
                displayToGallery(getActivity(), file);
                result = mActivity.getString(R.string.image_download_success) + file.getAbsolutePath();
            } catch (Exception e) {
                result = mActivity.getString(R.string.image_download_error) + e.getLocalizedMessage();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            ToastUtils.showShort(result);

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {


                }
            });

        }
    }

    /**
     * insert and refresh Gallery
     *
     * @param context
     * @param photoFile
     */
    public static void displayToGallery(Context context, File photoFile) {
        if (photoFile == null || !photoFile.exists()) {
            return;
        }
        String photoPath = photoFile.getAbsolutePath();
        String photoName = photoFile.getName();
        // insert photo into Gallery
        try {
            ContentResolver contentResolver = context.getContentResolver();
            MediaStore.Images.Media.insertImage(contentResolver, photoPath, photoName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // refresh Gallery
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + photoPath)));
    }

}


