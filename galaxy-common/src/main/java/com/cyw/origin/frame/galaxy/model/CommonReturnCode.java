package com.cyw.origin.frame.galaxy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用错误code
 * @author yiwen.chang
 */
@NoArgsConstructor
@AllArgsConstructor
public enum CommonReturnCode implements Serializable {

    SystemError("99999", "服务在开小差，请稍等"),

    ParamError("40012", "参数校验失败，请检查你的参数"),

    BCError("30001", "商户服务在开小差，请稍等"),

    GoodsError("30002", "商品服务在开小差，请稍等"),

    CouponError("30003", "卡券服务在开小差，请稍等"),

    FileServiceError("30004", "文件服务在开小差，请稍等"),

    RiskServiceError("30005", "风控服务在开小差，请稍等");

    @Getter
    private String code;
    @Getter
    private String desc;

}