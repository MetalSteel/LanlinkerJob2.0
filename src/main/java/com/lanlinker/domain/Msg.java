package com.lanlinker.domain;

/**
 * 消息提示类
 */
public class Msg {
    private String status;
    private String msg;

    public Msg() {
    }

    public Msg(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
