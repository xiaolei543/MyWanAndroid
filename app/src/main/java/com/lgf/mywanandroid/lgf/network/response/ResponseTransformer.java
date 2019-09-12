package com.lgf.mywanandroid.lgf.network.response;

import android.util.Log;

import com.lgf.mywanandroid.lgf.network.Exception.ApiException;
import com.lgf.mywanandroid.lgf.network.Exception.CustomException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * 对返回的数据进行处理，区分异常的情况。
 *
 * 个人使用了另外一种方法，保存这种方法
 */

public class ResponseTransformer {

    public static <T> ObservableTransformer<BaseData<T>, T> handleResult() {
        return upstream -> upstream
                .onErrorResumeNext(new ErrorResumeFunction<>())
                .flatMap(new ResponseFunction<>());
    }


    /**
     * 非服务器产生的异常，比如本地无无网络请求，Json数据解析错误等等。
     *
     * @param <T>
     */
    private static class ErrorResumeFunction<T> implements Function<Throwable, ObservableSource<? extends BaseData<T>>> {

        @Override
        public ObservableSource<? extends BaseData<T>> apply(Throwable throwable) throws Exception {
            return Observable.error(CustomException.handleException(throwable));
        }
    }

    /**
     * 服务其返回的数据解析
     * 正常服务器返回数据和服务器可能返回的exception
     *
     * @param <T>
     */
    private static class ResponseFunction<T> implements Function<BaseData<T>, ObservableSource<T>> {

        @Override
        public ObservableSource<T> apply(BaseData<T> tResponse) throws Exception {
            int code = tResponse.getCode();
            String message = tResponse.getMsg();
            if (code == 0) {
                Log.d("线程切换", "apply: ");
                return Observable.just(tResponse.getData());
            } else {
                Log.d("线程切换错误", "apply: ");
                return Observable.error(new ApiException(code, message));
            }
        }
    }


}
