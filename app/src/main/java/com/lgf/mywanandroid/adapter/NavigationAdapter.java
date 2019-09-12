package com.lgf.mywanandroid.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgf.mywanandroid.bean.NavigationListBean;

import java.util.List;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public class NavigationAdapter extends BaseQuickAdapter<NavigationListBean, BaseViewHolder> {

    public NavigationAdapter(int layoutResId, @Nullable List<NavigationListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NavigationListBean item) {

    }
}
