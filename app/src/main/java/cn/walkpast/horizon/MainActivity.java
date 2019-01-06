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
import cn.walkpast.horizon.competence.FileDownloadActivity;
import cn.walkpast.horizon.competence.ImageDownloadActivity;
import cn.walkpast.horizon.competence.MapActivity;
import cn.walkpast.horizon.competence.NormalActivity;
import cn.walkpast.horizon.competence.RedirectedActivity;
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
        mData.add("普通网页加载");
        mData.add("重定向网页加载");
        mData.add("视频播放");
        mData.add("地图定位");
        mData.add("图片长按保存");
        mData.add("文件下载");
        mData.add("文件上传");
        mData.add("相机调用");
        mData.add("deeplink拉活");
        mData.add("打电话/发短信/发邮件");
        mData.add("支付宝/微信支付");
        mData.add("js实现夜间模式");
        mData.add("js回调");
        mData.add("无图模式");
        mData.add("字体大小调节");
        mData.add("广告url拦截");
        mData.add("js去广告");
        mData.add("基础功能（前进/后退/刷新）");
        mData.add("获取网页快照");
        mData.add("加载策略对比体验");
        mData.add("加载进度条样式");
        mData.add("Cookies清除");
        mData.add("关于Horizon");
        mCompetenceAdapter.updata(mData);
        mRecyclerView.setAdapter(mCompetenceAdapter);

    }

    @Override
    public void onItemClick(View view, int position) {

        switch (position) {

            case 0:

                mIntent = new Intent(this, NormalActivity.class);

                break;
            case 1:

                mIntent = new Intent(this, RedirectedActivity.class);


                break;
            case 2:

                mIntent = new Intent(this, VideoPlayActivity.class);

                break;
            case 3:

                mIntent = new Intent(this, MapActivity.class);

                break;
            case 4:

                mIntent = new Intent(this, ImageDownloadActivity.class);

                break;
            case 5:

                mIntent = new Intent(this, FileDownloadActivity.class);

                break;
        }

        mIntent.putExtra("title", mCompetenceAdapter.getCurrentItem(position));
        startActivity(mIntent);
    }
}
