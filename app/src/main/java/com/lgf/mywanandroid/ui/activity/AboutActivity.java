package com.lgf.mywanandroid.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.lgf.base.activity.BaseCompatActivity;
import com.lgf.mywanandroid.lgf.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseCompatActivity {

    @BindView(R.id.iv_toolbar_left)
    ImageView mIvToolbarLeft;
    @BindView(R.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtils.fixToolbar(mToolbar, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @OnClick(R.id.iv_toolbar_left)
    public void onViewClicked() {
        finish();
    }
}
