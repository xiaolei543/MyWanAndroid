package com.lgf.mywanandroid.presenter.navigate;

import android.support.annotation.NonNull;

import com.lgf.mywanandroid.bean.NavigationListBean;
import com.lgf.mywanandroid.contract.navigate.NavigationFragmentListContract;
import com.lgf.mywanandroid.lgf.network.BaseObserver;
import com.lgf.mywanandroid.lgf.network.response.BaseData;
import com.lgf.mywanandroid.lgf.network.schedulers.SchedulerProvider;
import com.lgf.mywanandroid.model.navigate.NavigationFragmentListModel;

import java.util.List;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public class NavigationFragmentListPresenter extends NavigationFragmentListContract.Presenter {


    @NonNull
    public static NavigationFragmentListPresenter newInstance(){
        return new NavigationFragmentListPresenter();
    }

    @Override
    public void getNavigationData() {
        mRxManager.register(mIModel.getNavigation()
        .compose(SchedulerProvider.getInstance().applySchedulers())
        .subscribeWith(new BaseObserver(mIView) {
            @Override
            public void onSuccess(BaseData o) {
                mIView.showNavigationListData((List<NavigationListBean>) o.getData());
            }

            @Override
            public void onErrorMsg(String msg) {

            }
        }));
    }

    @Override
    public NavigationFragmentListContract.Model getModel() {
        return NavigationFragmentListModel.newInstance();
    }

    @Override
    public void onStart() {

    }
}
