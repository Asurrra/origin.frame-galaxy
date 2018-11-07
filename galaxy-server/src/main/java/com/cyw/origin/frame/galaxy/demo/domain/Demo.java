package com.cyw.origin.frame.galaxy.demo.domain;

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

    private Date birthday;

}