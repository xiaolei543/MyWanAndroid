package com.lgf.mywanandroid.presenter.project;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.lgf.mywanandroid.bean.ProjectListBean;
import com.lgf.mywanandroid.contract.project.ProjectFragment_ListContract;
import com.lgf.mywanandroid.lgf.network.BaseObserver;
import com.lgf.mywanandroid.lgf.network.response.BaseData;
import com.lgf.mywanandroid.lgf.network.schedulers.SchedulerProvider;
import com.lgf.mywanandroid.model.project.ProjectFragment_ListModel;
import com.lgf.mywanandroid.ui.widgets.ArticleWebActivity;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public class ProjectFragment_ListPresenter extends ProjectFragment_ListContract.Presenter {


    @NonNull
    public static ProjectFragment_ListPresenter newInstance() {
        return new ProjectFragment_ListPresenter();
    }

    @Override
    public void loadRefreshList() {
        mRxManager.register(mIModel.getProjectList(mIView.Page(),mIView.Cid())
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .subscribeWith(new BaseObserver<ProjectListBean>(mIView) {
                    @Override
                    protected void onStart() {
                        //super.onStart();
                    }

                    @Override
                    public void onSuccess(BaseData<ProjectListBean> o) {
                        mIView.updateContentList( o.getData().getDatas(),true);
                    }

                    @Override
                    public void onErrorMsg(String msg) {

                    }
                }));
    }


    @Override
    public void loadLatestList() {
        mRxManager.register(mIModel.getProjectList(mIView.Page(),mIView.Cid())
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .subscribeWith(new BaseObserver<ProjectListBean>(mIView) {
                    @Override
                    protected void onStart() {
                        //super.onStart();
                    }

                    @Override
                    public void onSuccess(BaseData<ProjectListBean> o) {
                        mIView.updateContentList( o.getData().getDatas(),false);
                    }

                    @Override
                    public void onErrorMsg(String msg) {

                    }
        }));
//
//        mRxManager.register(mIModel.getProjectList(mIView.Page(),mIView.Cid())
//                .compose(SchedulerProvider.getInstance().applySchedulers())
//                .subscribe(new Consumer<BaseData<ProjectListBean>>() {
//                    @Override
//                    public void accept(BaseData<ProjectListBean> projectListBeanBaseData) throws Exception {
//                       // mIView.updateContentList(projectListBeanBaseData.getData().getDatas(),false);
//                    }
//
////                    @Override
////                    public void accept(BaseData<HomeArticleBean> homeArticleBeanBaseData) throws Exception {
////                        mIView.updateContentList(homeArticleBeanBaseData.getData().getDatas(), false);
////                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        mIView.showLoadMoreError();
//                    }
//                }));

    }


    @Override
    public void loadMoreList() {
        mRxManager.register(mIModel.getProjectList(mIView.Page(),mIView.Cid())
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .subscribeWith(new BaseObserver<ProjectListBean>(mIView) {
                    @Override
                    protected void onStart() {
                        //super.onStart();
                    }
                    @Override
                    public void onSuccess(BaseData<ProjectListBean> o) {
                        mIView.updateContentList( o.getData().getDatas(),false);
                    }

                    @Override
                    public void onErrorMsg(String msg) {

                    }
                }));
    }


    @Override
    public void onItemClick(int position, ProjectListBean.DatasBean item) {
        if(mIView==null){
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("title",item.getDesc());
        bundle.putString("url",item.getLink());
        mIView.startNewActivity(ArticleWebActivity.class,bundle);
    }


    @Override
    public ProjectFragment_ListContract.Model getModel() {
        return ProjectFragment_ListModel.newInstance();
    }

    @Override
    public void onStart() {

    }


}
