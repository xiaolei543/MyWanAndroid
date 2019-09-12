package com.lgf.mywanandroid.contract;

import com.lgf.mywanandroid.bean.JavaBean2;
import com.lgf.mywanandroid.bean.WxKeyBean;
import com.lgf.mywanandroid.bean.WxarticleBean;
import com.lgf.mywanandroid.lgf.base.BasePresenter;
import com.lgf.mywanandroid.lgf.base.IBaseModel;
import com.lgf.mywanandroid.lgf.base.IBaseView;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Administrator on 2019/5/31 0031.
 * desc :获取公众号
 */
public interface MainContract {
    interface Model extends IBaseModel {
        Observable<BaseData<List<JavaBean2>>> getWxarticle();

        Observable<BaseData<WxarticleBean>> getWx122333(int eg1, int eg2);

        Observable<BaseData<WxKeyBean>> getWxKey(int eg1, int eg2, String key);
    }
    interface View  extends IBaseView {
        void getWxarticle(BaseData<List<JavaBean2>> o);

        void getWx122333(BaseData<WxarticleBean> beanResponse);

        void getWxkey(BaseData<WxKeyBean> keyBeanResponse);
    }

    abstract class Presenter extends BasePresenter<MainContract.Model,MainContract.View> {
        public abstract void getWxarticle();

        public abstract void getWx122333(int eg1,int eg2);

        public abstract void getWxKey(int eg1,int eg2,String key );
    }
}
