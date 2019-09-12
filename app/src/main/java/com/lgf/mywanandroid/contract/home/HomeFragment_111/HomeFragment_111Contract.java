package com.lgf.mywanandroid.contract.home.HomeFragment_111;

import com.lgf.mywanandroid.bean.BannerBean;
import com.lgf.mywanandroid.bean.HomeArticleBean;
import com.lgf.mywanandroid.contract.BaseRVContract;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Administrator on 2019/6/6 0006.
 * desc :
 */
public interface HomeFragment_111Contract {

    interface Model extends BaseRVContract.IBaseRVModel{

        Observable<BaseData<List<BannerBean>>> getBanner();

        Observable<BaseData<HomeArticleBean>> getHomeArticle(int age);

        //Observable<BaseData<List<BannerBean>>> getBanner();
    }


    interface View extends BaseRVContract.IBaseRVView<HomeArticleBean.DatasBean>{
        //获取当前分页
        int Page();

        void setBanner(List<BannerBean> data);
    }

     abstract  class Presenter extends BaseRVContract.BaseRVPresenter<HomeFragment_111Contract.Model,HomeFragment_111Contract.View, HomeArticleBean.DatasBean> {

        public abstract void getBanner();
     }
}
