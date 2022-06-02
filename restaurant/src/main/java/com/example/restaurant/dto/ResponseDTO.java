package com.example.restaurant.dto;

public class ResponseDTO<T> {


    public int code = 0;
    public String msg = "";
    public T data;

    public static ResponseDTO onSuccess(String success, Object data) {
        ResponseDTO resp = new ResponseDTO();
        resp.code = 0;
        resp.msg = success;
        resp.data = data;
        return resp;
    }

    public static ResponseDTO onErrorObj(String error, Object data) {
        ResponseDTO resp = new ResponseDTO();
        resp.code = 400;
        resp.msg = error;
        resp.data = data;
        return resp;
    }

    public static ResponseDTO onError(String error) {
        ResponseDTO resp = new ResponseDTO();
        resp.code = 400;
        resp.msg = error;
        resp.data = null;
        return resp;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return "RespDTO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", obj=" + data +
                '}';
    }
}
