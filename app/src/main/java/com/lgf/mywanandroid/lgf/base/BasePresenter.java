package com.lgf.mywanandroid.lgf.base;

import com.lgf.mywanandroid.lgf.RxManager;

/**
 * Created by Administrator on 2019/5/29 0029.
 * desc :Presenter 相当于M和V的桥梁 需要同时持有M和V的实例
 */
public abstract class BasePresenter< M , V > {
    public M mIModel;
    public V mIView;
    public RxManager mRxManager=new RxManager();

    /**
     * 返回presenter要持有的Model引用
     *
     * @return
     */
    public abstract M getModel();

    /**
     * 绑定IModel和IView的引用
     *
     * @param v view
     */
    public void attachMV(V v) {
        this.mIModel = getModel();
        this.mIView = v;
        this.onStart();
    }


    /**
     * 解绑IModel和IView
     */
    public void detachMV() {
        mRxManager.unSubscribe();
        mIView = null;
        mIModel = null;
    }

    /**
     * IView和IModel绑定完成立即执行
     * <p>
     * 实现类实现绑定完成后的逻辑,例如数据初始化等
     */
    public abstract void onStart();



}
