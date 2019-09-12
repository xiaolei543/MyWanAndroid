package com.lgf.mywanandroid.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.bean.HomeArticleBean;

import java.util.List;

/**
 * Created by Administrator on 2019/6/6 0006.
 * desc :
 */
public class HomeFragment_111Adapter extends BaseQuickAdapter<HomeArticleBean.DatasBean, BaseViewHolder> {


    List<HomeArticleBean.DatasBean> data;

    public HomeFragment_111Adapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }



    public HomeFragment_111Adapter(int layoutResId, @Nullable List<HomeArticleBean.DatasBean> data) {
        super(layoutResId, data);
        this.data=data;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeArticleBean.DatasBean item) {
        helper.setText(R.id.item_tv_name,item.getAuthor()+"");
        helper.setText(R.id.item_tv_time,item.getNiceDate()+"");
        helper.setText(R.id.item_tv_title,item.getTitle()+"");
        helper.setText(R.id.item_tv_label,item.getSuperChapterName()+"");


    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
