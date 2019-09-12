package com.lgf.mywanandroid.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.bean.BannerBean;

/**
 * Created by Administrator on 2019/6/7 0007.
 * desc :
 */
public class NetworkImageHolderView implements Holder<BannerBean> {
    private ImageView imageView;


    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, BannerBean data) {
        imageView.setImageResource(R.mipmap.logo);
        Glide.with(context).load(data.getImagePath()).placeholder(R.mipmap.logo).into(imageView);
    }
}
