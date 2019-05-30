package com.cyw.origin.frame.galaxy.demo.dao;

import com.cyw.origin.frame.galaxy.demo.domain.TaskInfo;

import java.util.List;

public interface TaskInfoMapper {
    int updateByPrimaryKey(TaskInfo record);

    int insert(TaskInfo record);

    int deleteByPrimaryKey(Long id);

    TaskInfo selectByPrimaryKey(Long id);

    int insertSelective(TaskInfo record);

    int updateByPrimaryKeySelective(TaskInfo record);

    List<TaskInfo> selectTaskByBizType(Integer bizType);
}