package com.lgf.mywanandroid.lgf.base.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.lgf.mywanandroid.lgf.base.BasePresenter;
import com.lgf.mywanandroid.lgf.base.IBaseActivity;
import com.lgf.mywanandroid.lgf.network.response.BaseData;
import com.lgf.mywanandroid.lgf.utils.ToastUtils;
import com.lgf.mywanandroid.ui.activity.LoginActivity;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2019/5/29 0029.
 * desc :
 */
public abstract class BaseMvpCompatActivity <P extends BasePresenter> extends BaseCompatActivity implements IBaseActivity {
    /**
     * presenter 具体的presenter由子类确定
     */
    protected P mPresenter;

    /**
     * 初始化数据
     * <p>
     * 子类可以复写此方法初始化子类数据
     */
    protected void initData() {
        super.initData();
        mPresenter = (P) initPresenter();
        if (mPresenter != null) {
            mPresenter.attachMV(this);
            Logger.d("attach M V success.");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();
            //Logger.d("detach M V success.");
        }
    }

    /**
     * 统一处理错误信息 例如登陆过期。。。
     * @param response
     */
    @Override
    public void onErrorCode(BaseData response) {
        if(response.getCode()==1001){//如果错误跳转至登陆界面
            startNewActivity(LoginActivity.class);
        }
    }


    //等待数据弹窗
    @Override
    public void showWaitDialog(String msg) {
        showProgressDialog(msg);
    }

    //结束等待数据弹窗
    @Override
    public void hideWaitDialog() {
        hideProgressDialog();
    }

    //显示其他信息
    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(msg);
    }


    //显示进度条
    @Override
    public void showProgress() {

    }

    //监听进度条
    @Override
    public void onProgress(int progress) {

    }

    //隐藏进度条
    @Override
    public void hideProgress() {

    }


    //*************************** activity 跳转*****************************
    @Override
    public void startNewActivity(@NonNull Class<?> clz) {
        startActivity(clz);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {
        startActivity(clz, bundle);
    }

    @Override
    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {
        startActivityForResult(clz, bundle, requestCode);
    }

    @Override
    public void hideKeybord() {
        hiddenKeyboard();
    }

    @Override
    public void back() {
        super.onBackPressedSupport();
    }
}
