package com.lgf.mywanandroid.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.adapter.Holder.LeftViewHolder;
import com.lgf.mywanandroid.bean.NavigationListBean;
import com.lgf.mywanandroid.lgf.adapter.SimpleRecyclerAdapter;
import com.lgf.mywanandroid.lgf.adapter.SimpleViewHolder;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public class NavigationLeftAdapter extends SimpleRecyclerAdapter<NavigationListBean> {

    private int mSelectedPosition;

    @Override
    public SimpleViewHolder<NavigationListBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LeftViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_navigate_list_left, parent, false), this);
    }

    public void setSelectedPosition(int position) {
        mListData.get(mSelectedPosition).isSelected = false;
        notifyItemChanged(mSelectedPosition);
        mListData.get(position).isSelected = true;
        notifyItemChanged(position);
        mSelectedPosition = position;
    }
}
