package com.lgf.mywanandroid.model.home.HomeFragment_111;

import android.support.annotation.NonNull;

import com.lgf.mywanandroid.bean.BannerBean;
import com.lgf.mywanandroid.bean.HomeArticleBean;
import com.lgf.mywanandroid.contract.home.HomeFragment_111.HomeFragment_111Contract;
import com.lgf.mywanandroid.lgf.network.NetWorkManager;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2019/6/6 0006.
 * desc :
 */
public class HomeFragment_111Model implements HomeFragment_111Contract.Model {

    @NonNull
    public static HomeFragment_111Model newInstance() {
        return new HomeFragment_111Model();
    }

    @Override
    public Observable<BaseData<List<BannerBean>>> getBanner() {
        return NetWorkManager.getRequest().getBanner();
    }

    @Override
    public Observable<BaseData<HomeArticleBean>> getHomeArticle(int age) {
        return NetWorkManager.getRequest().getHomeArticle(age);
    }
}
