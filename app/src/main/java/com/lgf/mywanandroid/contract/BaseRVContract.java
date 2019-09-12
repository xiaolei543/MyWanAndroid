package com.lgf.mywanandroid.contract;


import com.lgf.mywanandroid.lgf.base.BasePresenter;
import com.lgf.mywanandroid.lgf.base.IBaseFragment;
import com.lgf.mywanandroid.lgf.base.IBaseModel;

import java.util.List;

/**
 * Created by Horrarndoo on 2019/6/1.
 *  这里继续二次封装 使用 recyclerView 更方便
 */

public interface BaseRVContract {
    abstract class BaseRVPresenter<M extends IBaseRVModel, V extends IBaseRVView, T> extends BasePresenter<M, V> {

        /**
         * 刷新List
         */
        public abstract void loadRefreshList();

        /**
         * 加载最新的list
         */
        public abstract void loadLatestList();


        /**
         * 加载更多list
         */
        public abstract void loadMoreList();

        /**
         * item点击事件
         *
         * @param position position
         * @param item     item
         */
        public abstract void onItemClick(int position, T item);
    }

    interface IBaseRVModel extends IBaseModel {
    }

    interface IBaseRVView<L> extends IBaseFragment {
        /**
         * 更新界面list
         *
         * @param list list
         */
        void updateContentList(List<L> list,boolean Refresh);

        /**
         * 点击事件后，刷新item
         *
         * @param position position
         */
        void itemNotifyChanged(int position);

        /**
         * 显示网络错误
         */
        void showNetworkError();

        /**
         * 显示加载更多错误
         */
        void showLoadMoreError();

        /**
         * 显示没有更多数据
         */
        void showNoMoreData();
    }
}
