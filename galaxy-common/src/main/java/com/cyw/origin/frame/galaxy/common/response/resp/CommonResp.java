package com.cyw.origin.frame.galaxy.common.response.resp;

import com.cyw.origin.frame.galaxy.common.enums.ReturnCode;
import com.cyw.origin.frame.galaxy.common.response.CommonResponse;
import com.cyw.origin.frame.galaxy.exception.BusinessException;
import com.weimob.soa.common.response.SoaResponse;

public abstract class CommonResp {

    public static <T> CommonResponse<T> success() {
        return success("操作成功",null);
    }

    public static <T> CommonResponse<T> success(T obj) {
        return success("操作成功", obj);
    }

    public static <T> CommonResponse<T> success(String msg, T obj) {
        CommonResponse cresp = get(ReturnCode.SUCCESS.getCode(), msg, obj);
        return cresp;
    }

    public static <T> CommonResponse<T> success(SoaResponse soa) {
        CommonResponse cresp = get(ReturnCode.SUCCESS.getCode(), soa.getReturnMsg(), soa.getResponseVo());
        return cresp;
    }

    public static <T> CommonResponse<T> failure(BusinessException be) {
        ReturnCode code = be.getCode();
        String msg = be.getMessage();
        return failure(code, msg, null);
    }

    public static <T> CommonResponse<T> failure(ReturnCode anEnum) {
        return failure(anEnum, anEnum.getMsg(), null);
    }

    public static <T> CommonResponse<T> failure(ReturnCode anEnum, String msg) {
        return failure(anEnum, msg, null);
    }

    public static <T> CommonResponse<T> failure(ReturnCode anEnum, String msg, T obj) {
        CommonResponse cresp = get(anEnum.getCode(), msg, obj);
        return cresp;
    }

    public static <T> CommonResponse<T> failure(SoaResponse soa) {
        CommonResponse cresp = get(soa.getReturnCode(), soa.getReturnMsg(), null);
        return cresp;
    }

    private static <T> CommonResponse<T> get(String code, String msg, T obj) {
        CommonResponse cresp = new CommonResponse();
        cresp.setErrcode(code);
        cresp.setErrmsg(msg);
        cresp.setData(obj);
        return cresp;
    }

}