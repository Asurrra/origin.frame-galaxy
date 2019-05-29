package com.cyw.origin.frame.galaxy.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yiwen.chang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadDto implements Serializable {

    private static final long serialVersionUID = -5671993829382463987L;

    private Long id;

    private String name;

    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date birthday;

}