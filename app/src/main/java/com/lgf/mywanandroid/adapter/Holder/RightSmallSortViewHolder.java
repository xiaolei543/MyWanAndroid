package com.lgf.mywanandroid.adapter.Holder;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.bean.NavigationListBean;
import com.lgf.mywanandroid.lgf.adapter.SimpleRecyclerAdapter;
import com.lgf.mywanandroid.lgf.adapter.SimpleViewHolder;


public class RightSmallSortViewHolder extends SimpleViewHolder<NavigationListBean.ArticlesBean> {

    private TextView textView;

    public RightSmallSortViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<NavigationListBean.ArticlesBean> adapter) {
        super(itemView, adapter);
        textView = itemView.findViewById(R.id.tv_small);
    }

    @Override
    protected void refreshView(NavigationListBean.ArticlesBean data) {
        textView.setText(data.getTitle());
    }
}
