package com.cyw.origin.frame.galaxy.util;

import com.cyw.origin.frame.galaxy.common.enums.ReturnCode;
import com.cyw.origin.frame.galaxy.common.response.CommonResponse;

/**
 * 创建通用返回对象工具类
 * @author yiwen.chang
 * @version 1.3.0
 * @date 2018/1/22
 */
public class BuildUtil {

    public static CommonResponse buildDefaultResponse(Class<?> responseClass, ReturnCode errorCode, Object data) throws IllegalAccessException, InstantiationException {
        CommonResponse response = (CommonResponse) responseClass.newInstance();
        response.setData(data);
        changeResponse(response, errorCode, data);
        return response;
    }

    public static CommonResponse changeResponse(CommonResponse response, ReturnCode errorCode, Object data) throws NullPointerException {
        if (data != null) {
            response.setData(data);
        }
        changeResponse(response, errorCode);
        return response;
    }

    public static CommonResponse changeResponse(CommonResponse response, ReturnCode errorCode) {
        response.setErrcode(errorCode.getCode());
        response.setErrmsg(errorCode.getMsg());
        return response;
    }


}
