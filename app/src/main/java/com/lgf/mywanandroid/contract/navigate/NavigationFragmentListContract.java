package com.lgf.mywanandroid.contract.navigate;

import com.lgf.mywanandroid.bean.NavigationListBean;
import com.lgf.mywanandroid.lgf.base.BasePresenter;
import com.lgf.mywanandroid.lgf.base.IBaseModel;
import com.lgf.mywanandroid.lgf.base.IBaseView;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public interface NavigationFragmentListContract {
    interface Model extends IBaseModel {
        Observable<BaseData<List<NavigationListBean>>> getNavigation();
    }

    interface View extends IBaseView {
        void showNavigationListData(List<NavigationListBean> navigationListData);

    }

    abstract class Presenter extends BasePresenter<NavigationFragmentListContract.Model,NavigationFragmentListContract.View> {
      public   abstract void getNavigationData();
    }
}
