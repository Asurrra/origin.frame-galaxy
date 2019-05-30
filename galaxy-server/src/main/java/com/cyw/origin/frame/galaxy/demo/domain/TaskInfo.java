package com.cyw.origin.frame.galaxy.demo.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class TaskInfo implements Serializable {

    private static final long serialVersionUID = -4259068763312098814L;

    /** 主键id */
    private Long id;

	/** 业务单id */
    private String bizId;

	/** 业务单类型 1、订单 2、维权 */
    private Integer bizType;

	/** 业务单状态 */
    private Integer bizStatus;

	/** 同步状态 0、未完成 1、同步中 2、已完成 */
    private Integer syncStatus;

}