package com.cyw.origin.frame.galaxy.common.enums;

public enum OnOffEnum {

    ON(1,"开启"),
    OFF(0,"关闭");

    private Integer code;
    private String msg;

    OnOffEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
