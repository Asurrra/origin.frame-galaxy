package com.cyw.origin.frame.galaxy.common.enums;

/**
 * 错误码枚举接口
 * @author yiwen.chang
 * @version 1.0.0
 * @date 2018/2/8
 */
public interface ErrorCode extends EnumCode<String>{
	
	/**
	 * 获取返回码
	 * @return
	 */
	String getCode();

	/**
	 * 获取错误信息
	 * @return
	 */
	String getMsg();

}
