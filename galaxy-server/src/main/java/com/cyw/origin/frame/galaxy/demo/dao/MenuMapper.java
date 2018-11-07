package com.cyw.origin.frame.galaxy.demo.dao;


import com.cyw.origin.frame.galaxy.demo.domain.Menu;

public interface MenuMapper {
    int updateByPrimaryKey(Menu record);

    int insert(Menu record);

    int deleteByPrimaryKey(Long id);

    Menu selectByPrimaryKey(Long id);

    int insertSelective(Menu record);

    int updateByPrimaryKeySelective(Menu record);
}