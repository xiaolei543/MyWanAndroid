package com.lgf.mywanandroid.lgf.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.lgf.mywanandroid.lgf.base.BasePresenter;
import com.lgf.mywanandroid.lgf.base.IBaseFragment;
import com.lgf.mywanandroid.lgf.base.activity.BaseCompatActivity;
import com.lgf.mywanandroid.lgf.network.response.BaseData;
import com.lgf.mywanandroid.lgf.utils.ToastUtils;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2019/5/29 0029.
 * desc :
 * * <p>
 *  * Mvp Fragment基类
 *  * <p>
 *  * 实现IBaseView方法、绑定butterknife
 */
public abstract class BaseMVPCompatFragment <P extends BasePresenter> extends BaseCompatFragment implements IBaseFragment {
    public P mPresenter;

    /**
     * 在监听器之前把数据准备好
     */
    public void initData() {
        super.initData();

        mPresenter = (P) initPresenter();
        if (mPresenter != null) {
            mPresenter.attachMV(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();
        }
    }

    //显示等待提示框
    @Override
    public void showWaitDialog(String waitMsg) {
        showProgressDialog(waitMsg);
    }
    //隐藏等待提示框
    @Override
    public void hideWaitDialog() {
        hideProgressDialog();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(mContext, msg, Toast.LENGTH_SHORT);
    }

    //返回
    @Override
    public void back() {
        this.onBackPressedSupport();
    }

    //***********************************************************************************************************************
    @Override
    public void onErrorCode(BaseData response) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onProgress(int progress) {

    }

    @Override
    public void hideProgress() {

    }

    //***********************************************************************************************************************
    @Override
    public void startNewFragment(@NonNull SupportFragment supportFragment) {
        start(supportFragment);
    }

    @Override
    public void startNewFragmentWithPop(@NonNull SupportFragment supportFragment) {
        startWithPop(supportFragment);
    }

    @Override
    public void startNewFragmentForResult(@NonNull SupportFragment supportFragment, int
            requestCode) {
        startForResult(supportFragment, requestCode);
    }

    /**
     * 出栈到目标fragment
     *
     * @param targetFragmentClass   目标fragment
     * @param includeTargetFragment 是否包含该fragment
     *                              true 目标fragment也出栈
     *                              <p>
     *                              false 出栈到目标fragment，目标fragment不出栈
     */
    @Override
    public void popToFragment(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        popToFragment(targetFragmentClass,includeTargetFragment);
    }

    @Override
    public void hideKeybord() {
        hideSoftInput();
    }

    @Override
    public void setOnFragmentResult(int ResultCode, Bundle data) {
        setFragmentResult(ResultCode, data);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz) {
        ((BaseCompatActivity) mActivity).startActivity(clz);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {
        ((BaseCompatActivity) mActivity).startActivity(clz, bundle);
    }

    @Override
    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {
        ((BaseCompatActivity) mActivity).startActivityForResult(clz, bundle, requestCode);
    }



    /**
     * 返回当前fragment是否可见
     * @return 当前fragment是否可见
     */
    @Override
    public boolean isVisiable() {
        return isSupportVisible();
    }

    /**
     * 返回当前fragment绑定的activity
     * @return activity
     */
    @Override
    public Activity getBindActivity() {
        return mActivity;
    }
}
