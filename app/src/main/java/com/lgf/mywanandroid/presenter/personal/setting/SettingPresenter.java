package com.lgf.mywanandroid.presenter.personal.setting;


import android.support.annotation.NonNull;

import com.lgf.mywanandroid.contract.personal.setting.SettingContract;
import com.lgf.mywanandroid.lgf.network.BaseObserver;
import com.lgf.mywanandroid.lgf.network.response.BaseData;
import com.lgf.mywanandroid.lgf.network.schedulers.SchedulerProvider;
import com.lgf.mywanandroid.model.personal.setting.SettingModel;

/**
 * Created by Administrator on 2019/6/5 0005.
 * desc :
 */
public class SettingPresenter extends SettingContract.Presenter {

    @NonNull
    public static SettingPresenter newInstance(){
        return new SettingPresenter();
    }

    @Override
    public void logout() {
        mIModel.logout()
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .subscribeWith(new BaseObserver( mIView) {
                    @Override
                    public void onSuccess(BaseData o) {
                        mIView.onSuccess();
                    }

                    @Override
                    public void onErrorMsg(String msg) {
                        mIView.onErrot(msg);
                    }
                });
    }


    @Override
    public SettingContract.Model getModel() {
        return new SettingModel();
    }


    @Override
    public void onStart() {

    }
}
