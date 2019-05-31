package com.cyw.origin.frame.galaxy.common.enums;

import lombok.Getter;

/**
 * 同步配置业务类型枚举
 * <p>
 * Created by xinxin.xu on 2019/4/15.
 */
public enum SyncConfigBizTypeEnum {

    ORDER_SATATUS_CHANGE(0, "订单状态变更","ORDER_STATUS_CHANGE"),
    RIGHTS_COMPLETE(1, "维权完成","RIGHTS_COMPLETE"),
    POINTS(2, "积分变更","POINTS_CHANGE"),
    ASSET(3, "余额资产变更","ASSET_CHANGE"),
    RECHARGE_ORDER(4, "充值订单","RECHARGE_ORDER"),
    GET_COUPON(5, "领券发券","GET_COUPON"),
    USE_COUPON(6, "核销券","USE_COUPON"),
    TRANSFER_ORDER(7, "转单","TRANSFER_ORDER"),
    CREATE_ORDER(-1, "创建订单","CREATE_ORDER"),
    AGREE_RIGHTS(9,"同意维权","AGREE_RIGHTS"),
    CANCEL_RIGHTS(10,"取消&拒绝维权","CANCEL_RIGHTS"),
    CREATE_RIGHTS(11,"创建维权","CREATE_RIGHTS"),
    ;

    @Getter
    private Integer value;

    @Getter
    private String desc;
    @Getter
    private String key;

    SyncConfigBizTypeEnum(Integer value, String desc, String key) {
        this.value = value;
        this.desc = desc;
        this.key=key;
    }

    public static String getKeyByValue(Integer value) {
        return getByValue(value).getKey();
    }

    public static SyncConfigBizTypeEnum getByValue(Integer value) {
        if (null == value) {
            return null;
        }
        for (SyncConfigBizTypeEnum type : SyncConfigBizTypeEnum.values()) {
            if (0 == type.getValue().compareTo(value)) {
                return type;
            }
        }
        return null;
    }
}
