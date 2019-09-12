package com.lgf.mywanandroid.contract.personal.setting;


import com.lgf.mywanandroid.bean.JavaBean1;
import com.lgf.mywanandroid.lgf.base.BasePresenter;
import com.lgf.mywanandroid.lgf.base.IBaseModel;
import com.lgf.mywanandroid.lgf.base.IBaseView;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2019/6/5 0005.
 * desc :
 */
public interface SettingContract {
    interface Model  extends IBaseModel {
        Observable<BaseData<JavaBean1>> logout();
    }

    interface View extends IBaseView {

        void onErrot(String msg);

        void onSuccess();
    }

    abstract class Presenter extends BasePresenter<SettingContract.Model, SettingContract.View> {
        public   abstract void logout();
    }
}
