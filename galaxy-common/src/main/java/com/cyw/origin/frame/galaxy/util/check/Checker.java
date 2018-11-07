package com.cyw.origin.frame.galaxy.util.check;

import com.cyw.origin.frame.galaxy.common.enums.ReturnCode;
import com.cyw.origin.frame.galaxy.exception.BusinessException;

/**
 * 代码工具,可以用来检验参数,捕获BusinessException来统一处理逻辑
 * 
 * Created by god on 16/6/21.
 */
public abstract class Checker {

    /**
     * 检查参数,不满足抛出异常,可用来简化代码
     *
     * @param matched 是否满足逻辑
     * @param anEnum 业务code枚举,需要有getCode,getDesc方法
     * @throws BusinessException matched = false则抛出异常
     */
    public static void checkArgument(boolean matched, ReturnCode anEnum) throws BusinessException {
        if (!matched) {
            throw new BusinessException(anEnum);
        }
    }

    /**
     * 检查参数,不满足抛出异常,可用来简化代码
     *
     * @param matched 是否满足逻辑
     * @param code 业务code
     * @param msg 错误信息,尽量友好
     * @throws BusinessException matched = false则抛出异常
     */
    public static void checkArgument(boolean matched, ReturnCode code, String msg) throws BusinessException {
        if (!matched) {
            throw new BusinessException(code, msg);
        }
    }

    /**
     * 检查参数,不满足抛出异常,可用来简化代码
     *
     * @param matched 是否满足逻辑
     * @param code 业务code
     * @param formatMsg 占位符错误信息,尽量友好
     * @param args 占位符参数
     * @throws BusinessException matched = false则抛出异常
     */
    public static void checkArgument(boolean matched, ReturnCode code, String formatMsg,
                                     Object... args) throws BusinessException {
        if (!matched) {
            throw new BusinessException(code, String.format(formatMsg, args));
        }
    }

}
