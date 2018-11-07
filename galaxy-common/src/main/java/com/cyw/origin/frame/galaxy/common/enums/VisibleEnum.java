package com.cyw.origin.frame.galaxy.common.enums;

/**
 * 可见枚举
 * @author yiwen.chang
 * @version 1.0.0
 * @date 2018/2/28
 */
public enum VisibleEnum {

    open(1,"开启"),
    hide(0,"关闭");

    private Integer code;
    private String msg;

    VisibleEnum(int code, String msg) {
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
