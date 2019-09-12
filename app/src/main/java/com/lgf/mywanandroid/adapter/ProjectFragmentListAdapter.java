package com.lgf.mywanandroid.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.bean.ProjectListBean;

import java.util.List;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public class ProjectFragmentListAdapter extends BaseQuickAdapter<ProjectListBean.DatasBean, BaseViewHolder> {

    List<ProjectListBean.DatasBean> data;

    public ProjectFragmentListAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }



    public ProjectFragmentListAdapter(int layoutResId, @Nullable List<ProjectListBean.DatasBean> data) {
        super(layoutResId, data);
        this.data=data;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ProjectListBean.DatasBean item) {
        helper.setText(R.id.item_tv_name,item.getAuthor()+"");
        helper.setText(R.id.item_tv_title,item.getTitle()+"");
        helper.setText(R.id.item_tv_time,item.getNiceDate()+"");
        helper.setText(R.id.item_tv_desc,item.getDesc()+"");


        Glide.with(mContext).load(item.getEnvelopePic()).placeholder(R.mipmap.ic_back).into((ImageView) helper.getView(R.id.item_iv_img));

    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
