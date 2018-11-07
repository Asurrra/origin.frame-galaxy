package com.cyw.origin.frame.galaxy.common.response.resp;

import com.cyw.origin.frame.galaxy.common.enums.ReturnCode;
import com.cyw.origin.frame.galaxy.common.enums.SystemCode;
import com.cyw.origin.frame.galaxy.exception.BusinessException;
import com.weimob.soa.common.response.SoaResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yiwen.chang
 */
public abstract class SoaResp {
    public SoaResp() {
    }

    public static <T, ErrT> SoaResponse<T, ErrT> success() {
        return success("操作成功", null);
    }

    public static <T, ErrT> SoaResponse<T, ErrT> success(T obj) {
        return success("操作成功", obj);
    }

    public static <T, ErrT> SoaResponse<T, ErrT> success(String msg, T obj) {
        SoaResponse resp = get("000000", msg, obj, null);
        resp.setProcessResult(Boolean.valueOf(true));
        return resp;
    }

    public static <T, ErrT> SoaResponse<T, ErrT> failure(BusinessException be) {
        SoaResponse<T, ErrT> soaResp = failure(be.getCode().getCode(), be.getMessage());
        soaResp.setProcessResult(Boolean.valueOf(false));
        return soaResp;
    }

    public static <T, ErrT> SoaResponse<T, ErrT> failure(SystemCode systemCode) {
        String code = StringUtils.join(new String[]{systemCode.getCode(), "-", ReturnCode.SERVICE_EXCEPTION.getCode()});
        String msg = StringUtils.join(new String[]{systemCode.getMsg(), "-", ReturnCode.SERVICE_EXCEPTION.getMsg()});
        return failure(code, msg);
    }

    public static <T, ErrT> SoaResponse<T, ErrT> failure(ReturnCode code) {
        return failure(code.getCode(), code.getMsg());
    }

    public static <T, ErrT> SoaResponse<T, ErrT> failure(String code, String msg) {
        return failure(code, msg, null, null);
    }

    public static <T, ErrT> SoaResponse<T, ErrT> failure(ReturnCode code, T obj, ErrT errT) {
        return failure(code.getCode(), code.getMsg(), obj, errT);
    }

    public static <T, ErrT> SoaResponse<T, ErrT> failure(String code, String msg, T obj, ErrT errT) {
        SoaResponse resp = get(code, msg, obj, errT);
        resp.setProcessResult(Boolean.valueOf(false));
        return resp;
    }

    private static <T, ErrT> SoaResponse<T, ErrT> get(String code, String msg, T obj, ErrT errT) {
        SoaResponse resp = new SoaResponse();
        resp.setReturnCode(code);
        resp.setReturnMsg(msg);
        resp.setResponseVo(obj);
        resp.setErrT(errT);
        return resp;
    }

}