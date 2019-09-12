package com.lgf.mywanandroid.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.adapter.Holder.RightBigSortViewHolder;
import com.lgf.mywanandroid.adapter.Holder.RightSmallSortViewHolder;
import com.lgf.mywanandroid.bean.NavigationListBean;
import com.lgf.mywanandroid.lgf.adapter.SimpleRecyclerAdapter;
import com.lgf.mywanandroid.lgf.adapter.SimpleViewHolder;
import com.lgf.mywanandroid.lgf.utils.LogUtils;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public class NavigationRightAdapter extends SimpleRecyclerAdapter<NavigationListBean.ArticlesBean> {
    @Override
    public SimpleViewHolder<NavigationListBean.ArticlesBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.BIG_SORT) {
            LogUtils.d("111111111111111111111");
            return new RightBigSortViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_navigation_list_right, parent, false), this);
        } else {

            LogUtils.d("NavigationRightAdapter");
            return new RightSmallSortViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item_right_small_sort, parent, false), this);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mListData.get(position).viewType;
    }

}
