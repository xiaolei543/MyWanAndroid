package com.lgf.mywanandroid.lgf.network.response;

/**
 * 返回结果封装
 *
 * 可对应添加需要字段
 */

public class BaseData<T> {

    private int errorCode; // 返回的code
    private T data; // 具体的数据结果
    private String errorMsg; // message 可用来返回接口的说明

    public int getCode() {
        return errorCode;
    }

    public void setCode(int code) {
        this.errorCode = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return errorMsg;
    }

    public void setMsg(String msg) {
        this.errorMsg = msg;
    }


    @Override
    public String toString() {
        return "Response{" +
                "ret=" + errorCode +
                ", data=" + data +
                ", msg='" + errorMsg + '\'' +
                '}';
    }
}
