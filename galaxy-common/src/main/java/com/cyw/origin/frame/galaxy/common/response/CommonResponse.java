package com.cyw.origin.frame.galaxy.common.response;

import com.cyw.origin.frame.galaxy.common.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @remark 通用返回
 * @author yiwen.chang
 * @date 2018/1/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private String errcode;
    private String errmsg;
    private T data;

    public CommonResponse(ErrorCode errorCode, T data) {
        this.errcode = errorCode.getCode();
        this.errmsg = errorCode.getMsg();
        this.data = data;
    }

    public void setReturnCode(ErrorCode errorCode) {
        this.errcode = errorCode.getCode();
        this.errmsg = errorCode.getMsg();
    }
}
