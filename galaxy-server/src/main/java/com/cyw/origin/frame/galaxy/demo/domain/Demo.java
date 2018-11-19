package com.cyw.origin.frame.galaxy.demo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yiwen.chang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Demo {

    private Long id;

    private String name;

    private Integer age;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

}