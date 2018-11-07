package com.cyw.origin.frame.galaxy.exception;

import com.cyw.origin.frame.galaxy.common.enums.ReturnCode;
import lombok.Getter;

/**
 * 用于系统内的错误信息传递,文案尽量友好
 *
 */
public class BusinessException extends Exception {

    @Getter
    private final ReturnCode code;
    @Getter
    private final String message;

    public BusinessException(ReturnCode code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public BusinessException(ReturnCode anEnum) {
        super();
        try {
            this.code = anEnum;
            this.message = anEnum.getMsg();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
