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
public class LeftViewHolder extends SimpleViewHolder<NavigationListBean> { /**
     * tvName显示大类名称，view是显示被选中的黄色标记
     */
    private TextView tvName;
    private View view;


    public LeftViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<NavigationListBean> adapter) {
        super(itemView, adapter);
        tvName = (TextView) itemView.findViewById(R.id.tv_left);
        view = itemView.findViewById(R.id.view);
    }

    @Override
    protected void refreshView(NavigationListBean data) {
        tvName.setText(data.getName());
        //item点击后背景的变化
        if (data.isSelected) {
            view.setVisibility(View.VISIBLE);
            tvName.setBackgroundResource(R.color.bg_gray);
            tvName.setTextColor(getContext().getResources().getColor(R.color.txt_link_blue));
        } else {
            view.setVisibility(View.GONE);
            tvName.setBackgroundResource(R.color.white);
            tvName.setTextColor(getContext().getResources().getColor(R.color.dark_grey));
        }
    }
}
