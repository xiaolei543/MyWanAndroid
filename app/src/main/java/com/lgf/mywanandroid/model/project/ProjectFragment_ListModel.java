package com.lgf.mywanandroid.model.project;

import android.support.annotation.NonNull;

import com.lgf.mywanandroid.bean.ProjectListBean;
import com.lgf.mywanandroid.contract.project.ProjectFragment_ListContract;
import com.lgf.mywanandroid.lgf.network.NetWorkManager;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public class ProjectFragment_ListModel implements ProjectFragment_ListContract.Model {

    @NonNull
    public static ProjectFragment_ListModel newInstance() {
        return new ProjectFragment_ListModel();
    }

    @Override
    public Observable<BaseData<ProjectListBean>> getProjectList(int page, int cid) {
        return NetWorkManager.getRequest().getProjectList(page,cid);
    }
}