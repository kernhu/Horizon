package cn.walkpast.horizon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.walkpast.horizon.adapter.CompetenceAdapter;
import cn.walkpast.horizon.competence.BasicFunActivity;
import cn.walkpast.horizon.competence.CallupActivity;
import cn.walkpast.horizon.competence.FileDownloadActivity;
import cn.walkpast.horizon.competence.FileUploadActivity;
import cn.walkpast.horizon.competence.InterceptOrReplaceActivity;
import cn.walkpast.horizon.competence.MapActivity;
import cn.walkpast.horizon.competence.NativeJsInteractActivity;
import cn.walkpast.horizon.competence.NormalActivity;
import cn.walkpast.horizon.competence.PaymentActivity;
import cn.walkpast.horizon.competence.SnapshotActivity;
import cn.walkpast.horizon.competence.VideoPlayActivity;
import cn.walkpast.horizon.listener.RecyclerItemClickListener;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/1 4:14 PM
 * describe: This is...
 */

public class MainActivity extends AppCompatActivity implements RecyclerItemClickListener {

    @BindView(R.id.recycler_view)
    public RecyclerView mRecyclerView;
    private CompetenceAdapter mCompetenceAdapter;
    private Intent mIntent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mCompetenceAdapter = new CompetenceAdapter(this, this);
        List<String> mData = new ArrayList<>();
        mData.add(getString(R.string.item_horizon_in_activity));
        mData.add(getString(R.string.item_horizon_in_fragment));
        mData.add(getString(R.string.item_video_fullscreen_play));
        mData.add(getString(R.string.item_map_location));
        mData.add(getString(R.string.item_file_download));
        mData.add(getString(R.string.item_file_uploading));
        mData.add(getString(R.string.item_tel_sms_email));
        mData.add(getString(R.string.item_payment));
        mData.add(getString(R.string.item_js_callback));
        mData.add(getString(R.string.item_url_intercept));
        mData.add(getString(R.string.item_capture_snapshoot));
        mData.add(getString(R.string.item_about_horizon));
        mCompetenceAdapter.updata(mData);
        mRecyclerView.setAdapter(mCompetenceAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {

        switch (position) {

            case 0:

                mIntent = new Intent(this, BasicFunActivity.class);

                break;
            case 1:

                mIntent = new Intent(this, NormalActivity.class);

                break;
            case 2:

                mIntent = new Intent(this, VideoPlayActivity.class);

                break;
            case 3:

                mIntent = new Intent(this, MapActivity.class);

                break;
            case 4:

                mIntent = new Intent(this, FileDownloadActivity.class);

                break;
            case 5:

                mIntent = new Intent(this, FileUploadActivity.class);

                break;
            case 6:

                mIntent = new Intent(this, CallupActivity.class);

                break;

            case 7:

                mIntent = new Intent(this, PaymentActivity.class);

                break;

            case 8:

                mIntent = new Intent(this, NativeJsInteractActivity.class);

                break;

            case 9:

                mIntent = new Intent(this, InterceptOrReplaceActivity.class);

                break;
            case 10:

                mIntent = new Intent(this, SnapshotActivity.class);

                break;
            case 11:

                mIntent = new Intent(this, AboutUsActivity.class);

                break;
        }
        if (mIntent != null) {

            startActivity(mIntent);

        }

    }
}
