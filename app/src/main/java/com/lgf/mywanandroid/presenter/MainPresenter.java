package com.lgf.mywanandroid.presenter;

import android.util.Log;

import com.lgf.mywanandroid.bean.JavaBean2;
import com.lgf.mywanandroid.bean.WxarticleBean;
import com.lgf.mywanandroid.contract.MainContract;
import com.lgf.mywanandroid.lgf.network.BaseObserver;
import com.lgf.mywanandroid.lgf.network.response.BaseData;
import com.lgf.mywanandroid.lgf.network.schedulers.BaseSchedulerProvider;
import com.lgf.mywanandroid.lgf.network.schedulers.SchedulerProvider;
import com.lgf.mywanandroid.model.MainModel;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Administrator on 2019/6/31 0031.
 * desc :
 */
public class MainPresenter extends MainContract.Presenter {

    private BaseSchedulerProvider schedulerProvider= SchedulerProvider.getInstance();

    private CompositeDisposable mDisposable=new CompositeDisposable();



    @NonNull
    public static MainPresenter newInstance(){
        return new MainPresenter();
    }

    @Override
    public void getWxarticle() {
        getModel().getWxarticle()
                .compose(schedulerProvider.applySchedulers())
                .subscribe(new BaseObserver(mIView){
            @Override
            public void onSuccess(BaseData o) {
                Log.d("12345", "onSuccess: "+o.getData().toString() ) ;
                mIView.getWxarticle((BaseData< List< JavaBean2>>)o);
            }

            @Override
            public void onErrorMsg(String msg) {
                //Log.d(TAG, "onError: ");
            }
        });

    }

    @Override
    public void getWx122333(int eg1, int eg2) {
        getModel().getWx122333(eg1,eg2)
                .compose(schedulerProvider.applySchedulers())
                .subscribe(new BaseObserver(mIView) {
                    @Override
                    public void onSuccess(BaseData o) {
                        mIView.getWx122333((BaseData<WxarticleBean>) o);
                    }

                    @Override
                    public void onErrorMsg(String msg) {
                    }
                });
    }

    @Override
    public void getWxKey(int eg1, int eg2, String key) {
        getModel().getWxKey(eg1,eg2,key)
                .compose(schedulerProvider.applySchedulers())
                .subscribe(new BaseObserver(mIView) {
                    @Override
                    public void onSuccess(BaseData o) {
                        mIView.getWxkey(o);
                    }

                    @Override
                    public void onErrorMsg(String msg) {

                    }
                });
    }


    @Override
    public MainContract.Model getModel() {
        return new MainModel();
    }

    @Override
    public void onStart() {
        Log.d("tag", "LoginPresenter onStart.");
    }
}
