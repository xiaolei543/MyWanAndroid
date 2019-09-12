package com.lgf.mywanandroid.lgf.base;

import android.support.annotation.NonNull;

import com.lgf.mywanandroid.lgf.network.response.BaseData;

/**
 * Created by Administrator on 2019/5/29 0029.
 * desc :
 */
public interface IBaseView {
    /**
     * 初始化presenter
     * <p>
     * 此方法返回的presenter对象不可为空
     */
    @NonNull
    BasePresenter initPresenter();

    /**
     * 显示toast消息
     *
     * @param msg 要显示的toast消息字符串
     */
    void showToast(String msg);

    /**
     * 显示等待dialog
     *
     * @param waitMsg 等待消息字符串
     */
    void showWaitDialog(String waitMsg);

    /**
     * 隐藏等待dialog
     */
    void hideWaitDialog();

    /**
     * 隐藏键盘
     */
    void hideKeybord();

    /**
     * 回退
     */
    void back();


    //错误码
    void onErrorCode(BaseData response);

    //进度条显示
    void showProgress();

    //文件下载进度监听
    void onProgress(int progress);

    //进度条隐藏
    void hideProgress();
}
