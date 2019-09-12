package com.lgf.mywanandroid.model;

import com.lgf.mywanandroid.bean.JavaBean2;
import com.lgf.mywanandroid.bean.WxKeyBean;
import com.lgf.mywanandroid.bean.WxarticleBean;
import com.lgf.mywanandroid.contract.MainContract;
import com.lgf.mywanandroid.lgf.base.BaseModel;
import com.lgf.mywanandroid.lgf.network.NetWorkManager;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2019/5/31 0031.
 * desc :
 */
public class MainModel  extends BaseModel implements MainContract.Model {
    @Override
    public Observable<BaseData<List<JavaBean2>>> getWxarticle() {
        return NetWorkManager.getRequest().getWxarticle();
    }

    @Override
    public Observable<BaseData<WxarticleBean>> getWx122333(int eg1, int eg2) {
        return NetWorkManager.getRequest().getWx122333(eg1,eg2);
    }

    @Override
    public Observable<BaseData<WxKeyBean>> getWxKey(int eg1, int eg2, String key) {
        return NetWorkManager.getRequest().getWxKey(eg1,eg2,key);
    }
}
