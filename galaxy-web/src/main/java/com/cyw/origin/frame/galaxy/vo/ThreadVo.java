package com.cyw.origin.frame.galaxy.vo;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yiwen.chang
 */
@Getter
public class ThreadVo implements Serializable {

    private static final long serialVersionUID = -8219319392341768987L;

    private Long id;

    private String name;

    private Integer age;

    private Date birthday;

}