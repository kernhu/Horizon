package cn.walkpast.horizon.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import cn.walkpast.horizon.R;

/**
 * Author: Kern
 * Time: 2019/1/14 13:41
 * Description: This is..
 */

public class PopupWindowTools {

    private static PopupWindowTools mPopupWindowTools;
    private Activity mActivity;
    private String[] mItems;
    private PopupWindowItemClickListener mItemClickListener;
    private View mTargetView;

    private PopupWindow mPopupWindow;

    public static PopupWindowTools getInstance() {

        mPopupWindowTools = new PopupWindowTools();

        return mPopupWindowTools;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public PopupWindowTools setActivity(Activity activity) {
        mActivity = activity;
        return this;
    }

    public String[] getItems() {
        return mItems;
    }

    public PopupWindowTools setItems(String... items) {
        mItems = items;
        return this;
    }

    public PopupWindowItemClickListener getItemClickListener() {
        return mItemClickListener;
    }

    public PopupWindowTools setItemClickListener(PopupWindowItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
        return this;
    }

    public View getTargetView() {
        return mTargetView;
    }

    public PopupWindowTools setTargetView(View targetView) {
        mTargetView = targetView;
        return this;
    }

    public void show() {

        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_for_popup_window, null, false);
        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.shape_popup_window_bg));
        //mPopupWindow.showAtLocation(getTargetView(), Gravity.TOP, 0, 0);
        mPopupWindow.showAsDropDown(getTargetView());

        initView(contentView);

    }

    private void initView(View contentView) {

        RecyclerView mRecyclerView = contentView.findViewById(R.id.popup_window_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        PopupWindowAdapter mPopupWindowAdapter = new PopupWindowAdapter(getActivity(), getItems(), mPopupWindowItemClickListener);
        mRecyclerView.setAdapter(mPopupWindowAdapter);
    }

    PopupWindowItemClickListener mPopupWindowItemClickListener = new PopupWindowItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

            if (getItemClickListener() != null) {
                getItemClickListener().onItemClick(view, position);
            }
            mPopupWindow.dismiss();
        }
    };


    class PopupWindowAdapter extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener {

        private Context mContext;
        private String[] mItems;
        private PopupWindowItemClickListener mItemClickListener;

        public PopupWindowAdapter(Context context, String[] items, PopupWindowItemClickListener itemClickListener) {
            mContext = context;
            mItems = items;
            mItemClickListener = itemClickListener;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.item_for_popup_window, parent,false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.mPopupWindowItem.setText(mItems[position]);
            holder.mPopupWindowItem.setOnClickListener(this);
            holder.mPopupWindowItem.setTag(position);
        }


        @Override
        public int getItemCount() {
            return mItems.length;
        }

        @Override
        public void onClick(View v) {

            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, (Integer) v.getTag());
            }
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mPopupWindowItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mPopupWindowItem = itemView.findViewById(R.id.popup_window_item);
        }
    }

    public interface PopupWindowItemClickListener {

        void onItemClick(View view, int position);
    }


}
