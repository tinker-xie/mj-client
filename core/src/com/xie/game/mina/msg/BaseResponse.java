package com.xie.game.mina.msg;

/**
 * Created by xie on 16/12/12.
 */
public class BaseResponse {
    public final static int SUCCESS_CODE = 0;
    public final static int FAIL_CODE = -1;
    public final static String SUCCESS_MESSAGE = "ok";
    public final static String FAIL_MESSAGE = "fail";

    private int code;
    private String msg;
    private String data;

    public BaseResponse() {
    }

    public BaseResponse(int code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static BaseResponse ok(String data) {
        return new BaseResponse(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static BaseResponse ok() {
        return new BaseResponse(SUCCESS_CODE, SUCCESS_MESSAGE, null);
    }

    public static BaseResponse fail(String data) {
        return new BaseResponse(SUCCESS_CODE, FAIL_MESSAGE, data);
    }


    public static BaseResponse fail() {
        return new BaseResponse(FAIL_CODE, FAIL_MESSAGE, null);
    }

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
