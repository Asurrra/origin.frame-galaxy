package com.cyw.origin.frame.galaxy.demo.dao;

import com.cyw.origin.frame.galaxy.demo.domain.Demo;
import org.apache.ibatis.annotations.Param;

/**
 * @author yiwen.chang
 */
public interface DemoDao {

    Integer save(@Param("entity")Demo demo);

}