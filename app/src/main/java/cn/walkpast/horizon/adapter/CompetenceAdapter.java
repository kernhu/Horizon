package cn.walkpast.horizon.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.walkpast.horizon.R;
import cn.walkpast.horizon.listener.RecyclerItemClickListener;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/1 4:29 PM
 * describe: This is...
 */

public class CompetenceAdapter extends RecyclerView.Adapter<CompetenceAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private RecyclerItemClickListener mItemClickListener;
    private List<String> mDatas;

    public CompetenceAdapter(Context context, RecyclerItemClickListener listener) {
        mContext = context;
        mItemClickListener = listener;
        mDatas = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_competence, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
        holder.setCompetenceName(mDatas.get(position));


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void updata(List<String> datas) {

        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public String getCurrentItem(int position) {

        return mDatas.get(position);

    }

    @Override
    public void onClick(View v) {

        int position = (int) v.getTag();
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(v, position);
        }

    }


    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.competence_name)
        TextView mCompetenceName;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void setCompetenceName(String competenceName) {
            mCompetenceName.setText(competenceName);
        }
    }
}
