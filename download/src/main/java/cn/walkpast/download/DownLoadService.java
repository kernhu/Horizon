package cn.walkpast.download;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import java.io.File;

import cn.walkpast.utils.ToastUtils;

/**
 * Author: Kern
 * Time: 2018/9/21 17:44
 * Description: This is..  下载管理类
 */

public class DownLoadService extends Service {

    private static final String STORAGE_PATH = "/download/horizon/";

    public static final String KEY_NETWORK_TYPES = "network_types";
    public static final String KEY_NOTIFICATION_TYPE = "notification_type";
    public static final String KEY_STORAGE_PATH = "storage_path";
    public static final String KEY_URL = "download_url";
    public static final String KEY_FILENAME = "download_filename";
    public static final String KEY_MIME_TYPE = "download_mime_type";

    private DownloadManager manager;
    private DownloadCompleteReceiver receiver;

    private int networkType;
    private int notificationType;
    private String url;
    private String filename;
    private String mimetype;
    private String storagePath;

    /**
     * @param activity
     * @param downloadUrl
     * @param filename
     * @param mimeType
     * @param storagePath
     * @param networkType
     * @param notificationType
     */
    public static void startDownloadService(Activity activity, String downloadUrl, String filename, String mimeType, String storagePath, int networkType, int notificationType) {

        if (activity == null) {
            throw new NullPointerException("Activity can't be null in DownLoadService");
        }

        if (downloadUrl == null || filename == null || mimeType == null) {
            throw new NullPointerException("downloadUrl or filename or mimetype is null");
        }

        Intent service = new Intent(activity, DownLoadService.class);
        service.putExtra(DownLoadService.KEY_URL, downloadUrl);
        service.putExtra(DownLoadService.KEY_FILENAME, filename);
        service.putExtra(DownLoadService.KEY_MIME_TYPE, mimeType);
        service.putExtra(DownLoadService.KEY_STORAGE_PATH, storagePath);
        service.putExtra(DownLoadService.KEY_NETWORK_TYPES, networkType);
        service.putExtra(DownLoadService.KEY_NOTIFICATION_TYPE, notificationType);
        activity.startService(service);

    }

    /**
     *
     */
    private void download() {

        manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        receiver = new DownloadCompleteReceiver();
        DownloadManager.Request down = new DownloadManager.Request(Uri.parse(url));
        down.allowScanningByMediaScanner();
        if (networkType == 0) {
            down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        } else if (networkType == 1) {
            down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        }
        down.setAllowedOverRoaming(false);
        down.setAllowedOverMetered(true);
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = (!TextUtils.isEmpty(mimetype)) ? mimetype : mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
        down.setMimeType(mimeString);
        if (notificationType == 0) {
            down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
        } else if (notificationType == 1) {
            down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        down.setTitle(filename);
        down.setDescription("文件下载");
        down.setVisibleInDownloadsUi(true);
        down.setDestinationInExternalPublicDir(TextUtils.isEmpty(storagePath) ? STORAGE_PATH : storagePath, filename);
        manager.enqueue(down);
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        url = intent.getStringExtra(DownLoadService.KEY_URL);
        filename = intent.getStringExtra(DownLoadService.KEY_FILENAME);
        mimetype = intent.getStringExtra(DownLoadService.KEY_MIME_TYPE);
        storagePath = intent.getStringExtra(DownLoadService.KEY_STORAGE_PATH);
        networkType = intent.getIntExtra(DownLoadService.KEY_NETWORK_TYPES, 1);
        notificationType = intent.getIntExtra(DownLoadService.KEY_NETWORK_TYPES, 1);

        if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(filename) && !TextUtils.isEmpty(mimetype)) {

            ToastUtils.showLong(getApplication().getResources().getString(R.string.download_hint));

            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + (TextUtils.isEmpty(storagePath) ? STORAGE_PATH : storagePath) + filename;
            File file = new File(path);
            if (file.exists()) {
                deleteFileWithPath(path);
            }
            download();
        }

        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        if (receiver != null)
            unregisterReceiver(receiver);
        super.onDestroy();
    }

    /**
     *
     */
    class DownloadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (manager.getUriForDownloadedFile(downId) != null) {
                    installAPK(context, getRealFilePath(context, manager.getUriForDownloadedFile(downId)));
                } else {
                    ToastUtils.showShort(getBaseContext().getResources().getString(R.string.download_failed));
                }
                DownLoadService.this.stopSelf();
            }
        }

        private void installAPK(Context context, String path) {
            File file = new File(path);
            if (file.exists()) {
                openFile(file, context);
            } else {
                ToastUtils.showShort(getBaseContext().getResources().getString(R.string.download_failed));
            }
        }
    }

    /**
     * @param context
     * @param uri
     * @return
     */
    public String getRealFilePath(Context context, Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


    /**
     * @param var0
     * @param var1
     */
    public void openFile(File var0, Context var1) {
        Intent var2 = new Intent();
        var2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        var2.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri uriForFile = FileProvider.getUriForFile(var1, var1.getApplicationContext().getPackageName() + ".provider", var0);
            var2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            var2.setDataAndType(uriForFile, var1.getContentResolver().getType(uriForFile));
        } else {
            var2.setDataAndType(Uri.fromFile(var0), getMIMEType(var0));
        }
        try {
            var1.startActivity(var2);
        } catch (Exception var5) {
            var5.printStackTrace();
            ToastUtils.showShort(getBaseContext().getResources().getString(R.string.download_open_mode_error));
        }
    }

    /**
     * @param var0
     * @return
     */
    public String getMIMEType(File var0) {
        String var1 = "";
        String var2 = var0.getName();
        String var3 = var2.substring(var2.lastIndexOf(".") + 1, var2.length()).toLowerCase();
        var1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var3);
        return var1;
    }

    /**
     * @param filePath
     * @return
     */
    public static boolean deleteFileWithPath(String filePath) {
        SecurityManager checker = new SecurityManager();
        File f = new File(filePath);
        checker.checkDelete(filePath);
        if (f.isFile()) {
            f.delete();
            return true;
        }
        return false;
    }
}
