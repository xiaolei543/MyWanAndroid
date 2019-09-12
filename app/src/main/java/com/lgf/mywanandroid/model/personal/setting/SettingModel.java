package com.lgf.mywanandroid.model.personal.setting;

import com.lgf.mywanandroid.bean.JavaBean1;
import com.lgf.mywanandroid.contract.personal.setting.SettingContract;
import com.lgf.mywanandroid.lgf.network.NetWorkManager;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2019/6/5 0005.
 * desc :
 */
public class SettingModel implements SettingContract.Model {
    @Override
    public Observable<BaseData<JavaBean1>> logout() {
        return NetWorkManager.getRequest().logout();
    }
}
