package com.cyw.origin.frame.galaxy.common.enums;

/**
 * @author yiwen.chang
 */
public enum QualityRiskControlEnum {

    pornographic(100,"色情"),
    sexy(110,"性感"),
    ad(200,"广告"),
    qr(210,"二维码"),
    vt(300,"暴恐"),
    vb(400,"违禁"),
    regent(500,"摄政"),
    abuse(600,"谩骂"),
    watering(700,"灌水");

    private int code;
    private String msg;

    QualityRiskControlEnum(int code, String msg) {
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
