package com.season.domain;

/**
 * Created by season on 2018/4/15.
 */
public class MsgBean<T> {

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public MsgBean setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public MsgBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public MsgBean setData(T data) {
        this.data = data;
        return this;
    }

    public static MsgBean success(){
        return new MsgBean().setCode(0)
                .setMsg("成功");
    }
    public static MsgBean error(){
        return new MsgBean().setCode(-1);
    }
}
