package com.cyw.origin.frame.galaxy.common.enums;

/**
 * 泛型枚举接口
 * @author yiwen.chang
 * @version 1.0.0
 * @date 2018/2/8
 */
public interface EnumCode<K> {

    K getKey();

    String getDescription();

}
