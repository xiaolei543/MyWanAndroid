package com.lgf.mywanandroid.adapter.Holder;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.bean.NavigationListBean;
import com.lgf.mywanandroid.lgf.adapter.SimpleRecyclerAdapter;
import com.lgf.mywanandroid.lgf.adapter.SimpleViewHolder;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public class RightBigSortViewHolder extends SimpleViewHolder<NavigationListBean.ArticlesBean> {
    TextView tvTitle;

    public RightBigSortViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<NavigationListBean.ArticlesBean> adapter) {
        super(itemView, adapter);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
    }

    @Override
    protected void refreshView(NavigationListBean.ArticlesBean data) {
        tvTitle.setText(data.header);
    }
}
