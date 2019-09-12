package com.lgf.mywanandroid.contract.project;

import com.lgf.mywanandroid.bean.ProjectTreeBean;
import com.lgf.mywanandroid.lgf.base.BasePresenter;
import com.lgf.mywanandroid.lgf.base.IBaseFragment;
import com.lgf.mywanandroid.lgf.base.IBaseModel;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public interface ProjectFragment_TabsContract {
    interface Model extends IBaseModel {
        Observable<BaseData<List<ProjectTreeBean>>> getProjectTree();
     }

    interface View  extends IBaseFragment {
        void onErrot(String msg);

        void onSuccess(BaseData<List<ProjectTreeBean>> bean1Response);
    }

    abstract class Presenter extends BasePresenter<ProjectFragment_TabsContract.Model,ProjectFragment_TabsContract.View> {
        public abstract void getProjectTree();
    }
}
