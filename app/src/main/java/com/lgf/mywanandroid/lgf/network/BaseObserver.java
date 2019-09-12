package com.lgf.mywanandroid.lgf.network;

import com.google.gson.JsonParseException;
import com.lgf.mywanandroid.lgf.base.IBaseView;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;


/**
 * File descripition:   数据处理基类
 *
 */

public abstract class BaseObserver<T> extends DisposableObserver<BaseData<T>> {
    protected IBaseView view;
    /**
     * 网络连接失败  无网
     */
    public static final int NETWORK_ERROR = 100000;
    /**
     * 解析数据失败
     */
    public static final int PARSE_ERROR = 1008;
    /**
     * 网络问题
     */
    public static final int BAD_NETWORK = 1007;
    /**
     * 连接错误
     */
    public static final int CONNECT_ERROR = 1006;
    /**
     * 连接超时
     */
    public static final int CONNECT_TIMEOUT = 1005;

    /**
     * 其他所有情况
     */
    public static final int NOT_TRUE_OVER = 1004;

    /**
     * 错误账号密码
     */
    public static final int NAME_PASSWORD_NO=-1;


    public BaseObserver(IBaseView view) {
        this.view = view;
    }
    public BaseObserver() {
    }

    @Override
    protected void onStart() {
        if (view != null) {
            view.showWaitDialog("数据获取中");
        }
    }

    @Override
    public void onNext(BaseData<T> o) {
//        T t = o.getData();
        try {
            if (view != null) {
                view.hideWaitDialog();
            }
            if (o.getCode()==0 || o.getCode() == 1) {

                onSuccess(o);
            } else {
                if (view != null) {
                    view.onErrorCode(o);
                }
                //非  true的所有情况
                onException(o.getCode(), o.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            onErrorMsg(e.toString());
        }
    }

    @Override
    public void onError(Throwable e) {
        if (view != null) {
            view.hideWaitDialog();;
        }
        if (e instanceof HttpException) {
            //   HTTP错误
            onException(BAD_NETWORK, "");
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            //   连接错误
            onException(CONNECT_ERROR, "");
        } else if (e instanceof InterruptedIOException) {
            //  连接超时
            onException(CONNECT_TIMEOUT, "");
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //  解析错误
            onException(PARSE_ERROR, "");
            e.printStackTrace();
        } else {
            if (e != null) {
                onErrorMsg(e.toString());
            } else {
                onErrorMsg("未知错误");
            }
        }
    }


    private void onException(int unknownError, String message) {
        switch (unknownError) {
            case CONNECT_ERROR:
                onErrorMsg("连接错误");
                break;
            case CONNECT_TIMEOUT:
                onErrorMsg("连接超时");
                break;
            case BAD_NETWORK:
                onErrorMsg("网络超时");
                break;
            case PARSE_ERROR:
                onErrorMsg("数据解析失败");
                break;
            case NAME_PASSWORD_NO:
                onErrorMsg(message);
                break;
            //非true的所有情况
            case NOT_TRUE_OVER:
                onErrorMsg(message);
                break;
            default:
                break;
        }
    }

    //消失写到这 有一定的延迟  对dialog显示有影响
    @Override
    public void onComplete() {
       /* if (view != null) {
            view.hideLoading();
        }*/
    }

    public abstract void onSuccess(BaseData<T> o);

    public abstract void onErrorMsg(String msg);
}
