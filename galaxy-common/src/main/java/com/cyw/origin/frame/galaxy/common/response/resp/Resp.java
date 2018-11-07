package com.cyw.origin.frame.galaxy.common.response.resp;

import com.weimob.soa.common.response.SoaResponse;
import lombok.Data;

@Data
public class Resp<T, ErrT> {

    private String errcode;
    private String errmsg;
    private T data;
    private ErrT errData;
    private Long serverTime = System.currentTimeMillis();
    private String globalTicket;
    private String monitorTrackId;

    private Resp() {
    }

    public static <T, ErrT> Resp<T, ErrT> build(SoaResponse<T, ErrT> soaResp) {
        return create(soaResp.getReturnCode(), soaResp.getReturnMsg(), soaResp.getResponseVo(), soaResp.getErrT(),
                soaResp.getGlobalTicket(), soaResp.getMonitorTrackId());
    }

    public static <T, ErrT> Resp<T, ErrT> success() {
        return success(null);
    }

    public static <T, ErrT> Resp<T, ErrT> success(T data) {
        return success(null, data, null, null);
    }

    public static <T, ErrT> Resp<T, ErrT> success(String msg, T data, String globalTicket, String monitorTrackId) {
        return create("0", msg, data, null, globalTicket, monitorTrackId);
    }

    public static <T, ErrT> Resp<T, ErrT> error() {
        return error("1", "服务在开小差，请稍等", null, null, null, null);
    }

    public static <T, ErrT> Resp<T, ErrT> error(String code, String msg) {
        return error(code, msg, null, null, null, null);
    }

    public static <T, ErrT> Resp<T, ErrT> error(String code, String msg, String globalTicket, String monitorTrackId) {
        return error(code, msg, null, null, globalTicket, monitorTrackId);
    }

    public static <T, ErrT> Resp<T, ErrT> error(String code, String msg, T data, ErrT errData, String globalTicket, String monitorTrackId) {
        return create(code, msg, data, errData, globalTicket, monitorTrackId);
    }

    public static <T, ErrT> Resp<T, ErrT> create(String code, String msg, T data, ErrT errData, String globalTicket, String monitorTrackId) {
        Resp<T, ErrT> resp = new Resp<>();
        resp.setErrcode(code);
        resp.setErrmsg(msg);
        resp.setData(data);
        resp.setErrData(errData);
        resp.setGlobalTicket(globalTicket);
        resp.setMonitorTrackId(monitorTrackId);
        return resp;
    }

}