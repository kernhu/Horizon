package cn.walkpast.core.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import cn.walkpast.core.ImageSaveHelper;
import cn.walkpast.core.R;
import cn.walkpast.utils.ToastUtils;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/2/2 11:59 PM
 * describe: This is...
 */

public class ImagePreviewDialog extends Dialog implements View.OnClickListener {

    private Bitmap mBitmap;

    public ImagePreviewDialog(@NonNull Context context, Bitmap bitmap) {
        super(context);
        this.mBitmap = bitmap;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_image_preview, null);
        setContentView(view);
        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        ImageView preview = view.findViewById(R.id.preview_image);
        preview.setImageBitmap(mBitmap);

        ImageView close = view.findViewById(R.id.preview_close);
        ImageView save = view.findViewById(R.id.preview_save);
        ImageView share = view.findViewById(R.id.preview_share);
        close.setOnClickListener(this);
        save.setOnClickListener(this);
        share.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (mBitmap == null) {
            return;
        }

        int i = v.getId();

        if (i == R.id.preview_close) {

            this.dismiss();

        } else if (i == R.id.preview_save) {

            boolean isSave = ImageSaveHelper.saveBitmapToAlbum(getContext(), mBitmap);
            ToastUtils.showShort(isSave ? getContext().getString(R.string.save_image_success) : getContext().getString(R.string.save_image_fail));


        } else if (i == R.id.preview_share) {

            Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContext().getContentResolver(), mBitmap, null, null));
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent = Intent.createChooser(intent, getContext().getString(R.string.share_title));
            getContext().startActivity(intent);

        }
    }
}
