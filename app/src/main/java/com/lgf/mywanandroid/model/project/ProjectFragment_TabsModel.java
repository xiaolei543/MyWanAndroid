package com.lgf.mywanandroid.model.project;

import android.support.annotation.NonNull;

import com.lgf.mywanandroid.bean.ProjectTreeBean;
import com.lgf.mywanandroid.contract.project.ProjectFragment_TabsContract;
import com.lgf.mywanandroid.lgf.network.NetWorkManager;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public class ProjectFragment_TabsModel implements ProjectFragment_TabsContract.Model {

    @NonNull
    public static ProjectFragment_TabsModel newInstance() {
        return new ProjectFragment_TabsModel();
    }

    @Override
    public Observable<BaseData<List<ProjectTreeBean>>> getProjectTree() {
        return NetWorkManager.getRequest().getProjectTree();
    }
}
