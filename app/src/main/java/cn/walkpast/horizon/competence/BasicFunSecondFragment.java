package cn.walkpast.horizon.competence;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.walkpast.core.WebHorizonFragment;
import cn.walkpast.core.client.HorizonClient;
import cn.walkpast.horizon.R;

/**
 * Author: Kern
 * Time: 2019/1/29 18:12
 * Description: This is..
 */

public class BasicFunSecondFragment extends WebHorizonFragment {

    @BindView(R.id.second_icon)
    public ImageView mSecondIcon;
    @BindView(R.id.second_title)
    public TextView mSecondTitle;
    @BindView(R.id.second_menu)
    public ImageView mSecondMenu;
    @BindView(R.id.second_container)
    public FrameLayout mSecondContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_basic_fun_second, null, false);

        ButterKnife.bind(this, view);

        Log.e("sos", "getHorizon==" + (getHorizon() == null));
        getHorizon()
                .setViewContainer(mSecondContainer)
                .setHorizonClient(mHorizonClient)
                .preview()
                .loadUrl("https://www.jd.com/");

        return view;
    }

    HorizonClient mHorizonClient = new HorizonClient() {

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);

            mSecondIcon.setImageBitmap(icon);
        }

        @Override
        public void onReceiveTitle(WebView view, String title) {
            super.onReceiveTitle(view, title);

            mSecondTitle.setText(title);
        }

    };
}
