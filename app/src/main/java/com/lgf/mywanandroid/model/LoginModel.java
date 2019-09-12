package com.lgf.mywanandroid.model;

import com.lgf.mywanandroid.bean.JavaBean1;
import com.lgf.mywanandroid.contract.LoginContract;
import com.lgf.mywanandroid.lgf.network.NetWorkManager;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2019/6/2 0002.
 * desc :
 */
public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<BaseData<JavaBean1>> login(String name, String password) {
        return NetWorkManager.getRequest().login(name,password);
    }
}
