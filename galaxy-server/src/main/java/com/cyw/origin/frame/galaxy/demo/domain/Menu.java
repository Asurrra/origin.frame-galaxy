package com.cyw.origin.frame.galaxy.demo.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

	/**  */
    private Long id;

	/**  */
    private String name;

	/**  */
    private String iconUrl;

	/**  */
    private Integer level;

	/**  */
    private Long groupId;

	/**  */
    private Long parentId;

	/** 插件id */
    private Long pluginId;

	/** 权限码 */
    private String authCode;

	/**  */
    private String menuUrl;

	/**  */
    private Integer jumpType;

	/**  */
    private String jumpUrl;

	/**  */
    private Integer orderNum;

	/**  */
    private String remark;

	/**  */
    private Boolean visible;

	/**  */
    private String createUser;

	/**  */
    private String updateUser;

	/** 用"-"连接的parentId */
    private String menuPath;

	/**  */
    private Integer status;
}