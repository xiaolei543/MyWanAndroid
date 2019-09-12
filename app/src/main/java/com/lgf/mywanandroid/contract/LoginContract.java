package com.lgf.mywanandroid.contract;

import com.lgf.mywanandroid.bean.JavaBean1;
import com.lgf.mywanandroid.lgf.base.BasePresenter;
import com.lgf.mywanandroid.lgf.base.IBaseModel;
import com.lgf.mywanandroid.lgf.base.IBaseView;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2019/6/2 0002.
 * desc :
 */
public interface LoginContract {

    interface Model extends IBaseModel {
        Observable<BaseData<JavaBean1>> login(String name, String password);
    }

    interface View extends IBaseView {
        void onErrot(String msg);

        void onSuccess(BaseData<JavaBean1> bean1Response);
    }

    abstract class Presenter extends BasePresenter<LoginContract.Model,LoginContract.View> {
      public   abstract void login(String name, String password);
    }
}
