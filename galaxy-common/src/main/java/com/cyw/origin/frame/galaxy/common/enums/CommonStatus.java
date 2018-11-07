package com.cyw.origin.frame.galaxy.common.enums;

/**
 * 通用状态枚举
 * @author yiwen.chang
 * @version 1.0.0
 * @date 2018/2/28
 */
public enum CommonStatus {

    AVAILABLE(1, "可用的"),
    DISABLE(2, "不可用的"),
    DELETE(0, "废弃的");

    public Integer code;

    public String msg;

    CommonStatus(Integer code, String msg) {
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