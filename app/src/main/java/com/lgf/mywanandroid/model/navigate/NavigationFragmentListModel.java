package com.lgf.mywanandroid.model.navigate;

import android.support.annotation.NonNull;

import com.lgf.mywanandroid.bean.NavigationListBean;
import com.lgf.mywanandroid.contract.navigate.NavigationFragmentListContract;
import com.lgf.mywanandroid.lgf.network.NetWorkManager;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public class NavigationFragmentListModel implements NavigationFragmentListContract.Model {

    @NonNull
    public static NavigationFragmentListModel newInstance(){
        return new NavigationFragmentListModel();
    }

    @Override
    public Observable<BaseData<List<NavigationListBean>>> getNavigation() {
        return NetWorkManager.getRequest().getNavigation();
    }
}
