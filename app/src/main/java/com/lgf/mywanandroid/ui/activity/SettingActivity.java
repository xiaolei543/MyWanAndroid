package com.lgf.mywanandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.contract.personal.setting.SettingContract;
import com.lgf.mywanandroid.lgf.base.BasePresenter;
import com.lgf.mywanandroid.lgf.base.activity.BaseMvpCompatActivity;
import com.lgf.mywanandroid.lgf.utils.SpUtils;
import com.lgf.mywanandroid.lgf.utils.StatusBarUtils;
import com.lgf.mywanandroid.presenter.personal.setting.SettingPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseMvpCompatActivity<SettingContract.Presenter> implements SettingContract.View {

    @BindView(R.id.personal_about)
    LinearLayout mPersonalAbout;
    @BindView(R.id.iv_toolbar_left)
    ImageView mIvToolbarLeft;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtils.fixToolbar(mToolbar, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }


    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return SettingPresenter.newInstance();
    }

    @Override
    public void onErrot(String msg) {

    }

    @Override
    public void onSuccess() {
        SpUtils.putString(mContext, "username", "登陆");
        showToast("退出登陆成功");
    }

    @OnClick({R.id.iv_toolbar_left, R.id.personal_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_left:
                finish();
                break;
            case R.id.personal_about:
                mPresenter.logout();
                break;
        }
    }
}
