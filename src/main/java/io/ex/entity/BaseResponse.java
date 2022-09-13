package io.ex.entity;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    public BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(ResponseCode code, String msg) {
         this.code = code.getCode();
         this.msg = msg;
    }

    public BaseResponse(ResponseCode code) {
        this(code, code.getMsg());
    }


    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
