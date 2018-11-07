package com.cyw.origin.frame.galaxy.common.enums;

public enum AvailableEnum {

    DISABLE(0, "禁用"),
    ENABLE(1, "可用"),
    DELETE(2, "废弃");

    public Integer code;

    public String msg;

    AvailableEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
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
