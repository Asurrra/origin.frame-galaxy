package com.cyw.origin.frame.galaxy.common.enums;

public enum LevelEnum {

    ONE(1,"一级"),
    TWO(2,"二级"),
    THREE(3,"三级"),
    FORE(4,"四级");

    private int code;
    private String msg;

    LevelEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
