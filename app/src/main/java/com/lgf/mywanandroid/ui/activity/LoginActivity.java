package com.lgf.mywanandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;

import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.bean.JavaBean1;
import com.lgf.mywanandroid.contract.LoginContract;
import com.lgf.mywanandroid.lgf.base.BasePresenter;
import com.lgf.mywanandroid.lgf.base.activity.BaseMvpCompatActivity;
import com.lgf.mywanandroid.lgf.network.response.BaseData;
import com.lgf.mywanandroid.lgf.utils.SpUtils;
import com.lgf.mywanandroid.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpCompatActivity<LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.tv_name)
    EditText mTvName;
    @BindView(R.id.tv_password)
    EditText mTvPassword;
    @BindView(R.id.bt_login)
    Button mBtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onSuccess(BaseData<JavaBean1> bean1Response) {
        //存储用户信息
        SpUtils.putString(mContext,"username",bean1Response.getData().getUsername());
        SpUtils.putInt(mContext,"id",bean1Response.getData().getId());
        showToast("登陆成功" );
        //startActivity(MainActivity.class);
        finish();
    }


    @Override
    public void onErrot(String msg) {
        showToast(msg);
    }

    //初始化LoginPresenter
    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return LoginPresenter.newInstance();
    }

    @OnClick(R.id.bt_login)
    public void onViewClicked() {
        mPresenter.login(mTvName.getText().toString().trim(),mTvPassword.getText().toString().trim());
    }
}
