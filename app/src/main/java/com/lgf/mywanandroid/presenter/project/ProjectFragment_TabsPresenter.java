package com.lgf.mywanandroid.presenter.project;

import android.support.annotation.NonNull;

import com.lgf.mywanandroid.bean.ProjectTreeBean;
import com.lgf.mywanandroid.contract.project.ProjectFragment_TabsContract;
import com.lgf.mywanandroid.lgf.network.BaseObserver;
import com.lgf.mywanandroid.lgf.network.response.BaseData;
import com.lgf.mywanandroid.lgf.network.schedulers.SchedulerProvider;
import com.lgf.mywanandroid.model.project.ProjectFragment_TabsModel;

import java.util.List;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public class ProjectFragment_TabsPresenter extends ProjectFragment_TabsContract.Presenter {

    @NonNull
    public static ProjectFragment_TabsPresenter newInstance() {
        return new ProjectFragment_TabsPresenter();
    }


    @Override
    public void getProjectTree() {
        mRxManager.register(mIModel.getProjectTree()
                .compose(SchedulerProvider.getInstance().applySchedulers())
        .subscribeWith(new BaseObserver(mIView) {
            @Override
            protected void onStart() {
                //super.onStart();
            }

            @Override
            public void onSuccess(BaseData o) {
                mIView.onSuccess((BaseData< List< ProjectTreeBean>>) o);
            }

            @Override
            public void onErrorMsg(String msg) {

            }
        }));
    }

    @Override
    public ProjectFragment_TabsContract.Model getModel() {
        return ProjectFragment_TabsModel.newInstance();
    }

    @Override
    public void onStart() {

    }
}
