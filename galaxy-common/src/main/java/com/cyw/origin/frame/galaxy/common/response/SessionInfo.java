package com.cyw.origin.frame.galaxy.common.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yiwen.chang
 * @version
 * @date 2018/3/19
 */
@Data
public class SessionInfo implements Serializable {

    private Long userId;

    private String userName;

    private List<String> authCodes;

    private Long ts;

}