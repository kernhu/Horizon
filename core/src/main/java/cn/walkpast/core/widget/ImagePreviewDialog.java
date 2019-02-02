package cn.walkpast.core.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import cn.walkpast.core.R;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/2/2 11:59 PM
 * describe: This is...
 */

public class ImagePreviewDialog extends Dialog {

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


        ImageView imageView = view.findViewById(R.id.image_preview);
        imageView.setImageBitmap(mBitmap);
    }

}
