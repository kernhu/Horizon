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
import cn.walkpast.horizon.competence.NormalActivity;
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
        mData.add("网页加载");
        mData.add("重定向加载");
        mData.add("视频播放");
        mData.add("SSL安全认证");
        mData.add("唤醒拉活");
        mData.add("文件下载");
        mCompetenceAdapter.updata(mData);
        mRecyclerView.setAdapter(mCompetenceAdapter);

    }

    @Override
    public void onItemClick(View view, int position) {

        Intent mIntent = new Intent(this, NormalActivity.class);
        startActivity(mIntent);

    }
}
