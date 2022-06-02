package com.example.restaurant.dto;

import java.io.Serializable;

public class BaseResponseDTO<T> implements Serializable
{
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应描述
     */
    private String msg;
    /**
     * 响应数据
     */
    private T payload;

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

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
