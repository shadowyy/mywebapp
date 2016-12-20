package com.shadow.vo;

/**
 * Created by alice on 2016/11/7 12:13
 */
public class JsonResult<T> {
    private boolean flag;
    private String msg;
    private T data;

    public JsonResult() {
    }

    public JsonResult(boolean flag, String msg, T data) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

    public static <T> JsonResult newIns(boolean flag, String msg, T data) {
        return new JsonResult<>(flag, msg, data);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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
