package com.cyw.origin.frame.galaxy.common.enums;

import java.io.Serializable;

/**
 * 通用系统枚举
 * @author yiwen.chang
 * @version 1.0.0
 * @date 2018/2/28
 */
public enum SystemCode implements Serializable {

    OPS_CENTER("13001001", "基础服务");

    private String code;
    private String msg;

    SystemCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
