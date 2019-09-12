package com.lgf.mywanandroid.presenter;

import com.lgf.mywanandroid.contract.LoginContract;
import com.lgf.mywanandroid.lgf.network.BaseObserver;
import com.lgf.mywanandroid.lgf.network.response.BaseData;
import com.lgf.mywanandroid.lgf.network.schedulers.BaseSchedulerProvider;
import com.lgf.mywanandroid.lgf.network.schedulers.SchedulerProvider;
import com.lgf.mywanandroid.model.LoginModel;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Administrator on 2019/6/2 0002.
 * desc :
 */
public class LoginPresenter extends LoginContract.Presenter {

    private BaseSchedulerProvider schedulerProvider= SchedulerProvider.getInstance();

    private CompositeDisposable mDisposable=new CompositeDisposable();

    @NonNull
    public static LoginPresenter newInstance(){
        return new LoginPresenter();
    }

    @Override
    public void login(String name, String password) {

        mIModel.login(name,password)
                .compose(schedulerProvider.applySchedulers())
                .subscribeWith(new BaseObserver(mIView) {
                    @Override
                    public void onSuccess(BaseData o) {
                        mIView.onSuccess(o);
                    }
                    @Override
                    public void onErrorMsg(String msg) {
                        mIView.onErrot(msg);
                    }
                });
    }

    @Override
    public LoginContract.Model getModel() {
        return new LoginModel();
    }

    @Override
    public void onStart() {

    }
}
