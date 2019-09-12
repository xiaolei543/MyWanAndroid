package com.lgf.mywanandroid.contract.project;

import com.lgf.mywanandroid.bean.ProjectListBean;
import com.lgf.mywanandroid.contract.BaseRVContract;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public interface ProjectFragment_ListContract {
    interface Model extends BaseRVContract.IBaseRVModel{
        Observable<BaseData<ProjectListBean>> getProjectList(int page,int cid);
    }

    interface View extends BaseRVContract.IBaseRVView<ProjectListBean.DatasBean>{
        int Page();

        int Cid();

        void updateContentList(List<ProjectListBean.DatasBean> datas,boolean Refresh);
    }

    abstract class Presenter extends BaseRVContract.BaseRVPresenter<ProjectFragment_ListContract.Model,ProjectFragment_ListContract.View, ProjectListBean.DatasBean>{

    }
}
