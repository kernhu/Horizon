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
import cn.walkpast.horizon.competence.CallupNormalActivity;
import cn.walkpast.horizon.competence.DeeplinkActivity;
import cn.walkpast.horizon.competence.FileDownloadActivity;
import cn.walkpast.horizon.competence.MapActivity;
import cn.walkpast.horizon.competence.NormalActivity;
import cn.walkpast.horizon.competence.PatternlessActivity;
import cn.walkpast.horizon.competence.PaymentActivity;
import cn.walkpast.horizon.competence.ThemeSwitchActivity;
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
        mData.add("Activity中基础功能 演示");
        mData.add("Fragment中基础功能 演示");
        mData.add("地图定位 演示");
        mData.add("文件下载 演示");
        mData.add("文件上传 演示");
        mData.add("相机调用 演示");
        mData.add("打电话/发短信/发邮件 演示");
        mData.add("支付宝/微信/财付通支付 演示");
        mData.add("js实现夜间模式切换 演示");
        mData.add("js回调 演示");
        mData.add("广告url拦截 演示");
        mData.add("js去广告 演示");
        mData.add("获取网页快照 演示");
        mData.add("加载策略对比体验 演示");
        mData.add("加载进度条样式 演示");
//        mData.add("Cookies清除");
        mData.add("关于Horizon");
        mCompetenceAdapter.updata(mData);
        mRecyclerView.setAdapter(mCompetenceAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {

        switch (mCompetenceAdapter.getCurrentItem(position)) {

            case "Activity中基础功能 演示":

                mIntent = new Intent(this, BasicFunActivity.class);

                break;
            case "Fragment中基础功能 演示":

                mIntent = new Intent(this, NormalActivity.class);

                break;
            case "地图定位 演示":

                mIntent = new Intent(this, MapActivity.class);

                break;
            case "文件下载 演示":

                mIntent = new Intent(this, FileDownloadActivity.class);

                break;
            case "打电话/发短信/发邮件 演示":

                mIntent = new Intent(this, CallupNormalActivity.class);

                break;
            case "支付宝/微信/财付通支付 演示":



                break;

            case "deeplink拉活":

                mIntent = new Intent(this, DeeplinkActivity.class);

                break;

            case "支付宝/微信/财付通支付":

                mIntent = new Intent(this, PaymentActivity.class);

                break;


            case "js实现夜间模式":

                mIntent = new Intent(this, ThemeSwitchActivity.class);

                break;


            case "无图模式":

                mIntent = new Intent(this, PatternlessActivity.class);

                break;
                case "关于Horizon":

                mIntent = new Intent(this, AboutUsActivity.class);

                break;
        }
        if (mIntent != null) {

            mIntent.putExtra("title", mCompetenceAdapter.getCurrentItem(position));
            startActivity(mIntent);

        }

    }
}
