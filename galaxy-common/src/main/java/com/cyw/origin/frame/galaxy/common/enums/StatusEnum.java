package com.cyw.origin.frame.galaxy.common.enums;

public enum StatusEnum {

    normal(1,"正常"),
    delete(0,"删除");

    private int code;
    private String name;

    StatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
