package com.lgf.mywanandroid.presenter.home.HomeFragment_111;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.lgf.mywanandroid.bean.BannerBean;
import com.lgf.mywanandroid.bean.HomeArticleBean;
import com.lgf.mywanandroid.contract.home.HomeFragment_111.HomeFragment_111Contract;
import com.lgf.mywanandroid.lgf.network.BaseObserver;
import com.lgf.mywanandroid.lgf.network.response.BaseData;
import com.lgf.mywanandroid.lgf.network.schedulers.SchedulerProvider;
import com.lgf.mywanandroid.model.home.HomeFragment_111.HomeFragment_111Model;
import com.lgf.mywanandroid.ui.widgets.ArticleWebActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/5/6 0006.
 * desc :
 */
public class HomeFragment_111Presenter extends HomeFragment_111Contract.Presenter {


    @NonNull
    public static HomeFragment_111Presenter newInstance() {
        return new HomeFragment_111Presenter();
    }

    @Override
    public void loadRefreshList() {
//      mRxManager.register(  mIModel.getHomeArticle(mIView.Page())
//                .compose(SchedulerProvider.getInstance().applySchedulers())
//                .subscribe(new Consumer<BaseData<HomeArticleBean>>() {
//                    @Override
//                    public void accept(BaseData<HomeArticleBean> homeArticleBeanBaseData) throws Exception {
//                        mIView.updateContentList(homeArticleBeanBaseData.getData().getDatas(), true);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        mIView.showNetworkError();
//                    }
//                }));
        mRxManager.register(mIModel.getHomeArticle(mIView.Page())
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .subscribeWith(new BaseObserver<HomeArticleBean>(mIView) {
                    @Override
                    protected void onStart() {
                        //super.onStart();
                        //覆盖从写，取消弹窗
                    }
                    @Override
                    public void onSuccess(BaseData<HomeArticleBean> o) {
                        mIView.updateContentList(o.getData().getDatas(),true);
                    }

                    @Override
                    public void onErrorMsg(String msg) {
                        mIView.showLoadMoreError();
                    }
                }));
    }

    @Override
    public void loadLatestList() {
        if(mIModel==null){
            return;
        }

//       mRxManager.register( mIModel.getHomeArticle(mIView.Page())
//                .compose(SchedulerProvider.getInstance().applySchedulers())
//                .subscribe(new Consumer<BaseData<HomeArticleBean>>() {
//                    @Override
//                    public void accept(BaseData<HomeArticleBean> homeArticleBeanBaseData) throws Exception {
//                        mIView.updateContentList(homeArticleBeanBaseData.getData().getDatas(),false);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        mIView.showNetworkError();
//                    }
//                }));
        mRxManager.register(mIModel.getHomeArticle(mIView.Page())
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .subscribeWith(new BaseObserver<HomeArticleBean>(mIView) {
                    @Override
                    protected void onStart() {
                        //super.onStart();
                        //覆盖从写，取消弹窗
                    }
                    @Override
                    public void onSuccess(BaseData<HomeArticleBean> o) {
                        mIView.updateContentList(o.getData().getDatas(),false);
                    }

                    @Override
                    public void onErrorMsg(String msg) {
                        mIView.showLoadMoreError();
                    }
                }));

    }

    @Override
    public void loadMoreList() {
        if(mIModel==null){
            return;
        }

//        mRxManager.register(mIModel.getHomeArticle(mIView.Page())
////                .compose(SchedulerProvider.getInstance().applySchedulers())
////                .subscribe(new Consumer<BaseData<HomeArticleBean>>() {
////                    @Override
////                    public void accept(BaseData<HomeArticleBean> homeArticleBeanBaseData) throws Exception {
////                        mIView.updateContentList(homeArticleBeanBaseData.getData().getDatas(), false);
////                    }
////                }, new Consumer<Throwable>() {
////                    @Override
////                    public void accept(Throwable throwable) throws Exception {
////                        mIView.showLoadMoreError();
////                    }
////                }));

        mRxManager.register(mIModel.getHomeArticle(mIView.Page())
        .compose(SchedulerProvider.getInstance().applySchedulers())
        .subscribeWith(new BaseObserver<HomeArticleBean>(mIView) {
            @Override
            protected void onStart() {
                //super.onStart();
                //覆盖从写，取消弹窗
            }
            @Override
            public void onSuccess(BaseData<HomeArticleBean> o) {
                mIView.updateContentList(o.getData().getDatas(),false);
            }

            @Override
            public void onErrorMsg(String msg) {
                mIView.showLoadMoreError();
            }
        }));

    }

    @Override
    public void onItemClick(int position, HomeArticleBean.DatasBean item) {
        if(mIView==null){
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("title",item.getDesc());
        bundle.putString("url",item.getLink());
        mIView.startNewActivity(ArticleWebActivity.class,bundle);
    }


    @Override
    public HomeFragment_111Contract.Model getModel() {
        return HomeFragment_111Model.newInstance();
    }



    @Override
    public void onStart() {

    }

    @Override
    public void getBanner() {
        mRxManager.register(mIModel.getBanner()
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .subscribeWith(new BaseObserver(mIView) {

                    @Override
                    protected void onStart() {
                        //super.onStart();
                        //覆盖从写，取消弹窗
                    }

                    @Override
                    public void onSuccess(BaseData o) {
                        mIView.setBanner((List<BannerBean>) o.getData());
                    }

                    @Override
                    public void onErrorMsg(String msg) {

                    }
                }));
    }
}
