package com.cyw.origin.frame.galaxy.task;

import com.alibaba.fastjson.JSONObject;
import com.cyw.origin.frame.galaxy.common.enums.SyncConfigBizTypeEnum;
import com.cyw.origin.frame.galaxy.conf.AsyncConfig;
import com.cyw.origin.frame.galaxy.demo.dao.TaskInfoMapper;
import com.cyw.origin.frame.galaxy.demo.domain.TaskInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Slf4j
@Component
@EnableScheduling
public class AsyncJob {

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Autowired
    private AsyncConfig asyncConfig;

    @Scheduled(initialDelay = 1000, fixedDelay = 5 * 1000)
    public void orderRun() {
        log.info("====== AsyncJob schedule start! =======");
        List<TaskInfo> taskInfoList = taskInfoMapper.selectTaskByBizType(null);
        if (null == taskInfoList || taskInfoList.isEmpty()) {
            log.info("====== no need sync task, schedule end! =======");
            return;
        } else {
//            Long startTime = System.currentTimeMillis();
//            Map<Integer, List<TaskInfo>> taskMap = new LinkedHashMap<>();
//            for (TaskInfo taskInfo : taskInfoList) {
//                List<TaskInfo> list = new ArrayList<>();
//                list.add(taskInfo);
//                taskMap.merge(taskInfo.getBizType() == 7 ? 0 : taskInfo.getBizType(), list, ((oldValue, value) -> {
//                    oldValue.addAll(value);
//                    return oldValue;
//                }));
//            }
//            Integer size = 0;
//            for (Integer integer : taskMap.keySet()) {
//                size += taskMap.get(integer).size();
//                System.out.println(integer + " - " + taskMap.get(integer).size() + taskMap.get(integer));
//            }
//            System.out.println(size);
//            log.info("====== AsyncJob schedule end! use time:{} ms =======", System.currentTimeMillis() - startTime);

            Long startTime1 = System.currentTimeMillis();
            Map<Integer, Map<String, List<TaskInfo>>> taskMap1 = new LinkedHashMap<>(20);
            for (TaskInfo taskInfo : taskInfoList) {
                Map<String, List<TaskInfo>> map = new LinkedHashMap<>();
                List<TaskInfo> list = new ArrayList<>();
                list.add(taskInfo);
                map.put(taskInfo.getBizId(), list);
                taskMap1.merge(taskInfo.getBizType() == 7 ? 0 : taskInfo.getBizType(), map, (oldValue, value) -> {
                    oldValue.merge(taskInfo.getBizId(), list, (oldValue1, value1) -> {
                        oldValue1.addAll(value1);
                        return oldValue1;
                    });
                    return oldValue;
                });
            }
            for (Integer integer : taskMap1.keySet()) {
                System.out.println(integer);
                for (String bizId : taskMap1.get(integer).keySet()) {
                    System.out.println(bizId + " - " + taskMap1.get(integer).get(bizId));
                }
                System.out.println();
            }
            log.info("====== AsyncJob schedule end! use time:{} ms =======", System.currentTimeMillis() - startTime1);
            for (Integer integer : taskMap1.keySet()) {
                asyncConfig.setThreadNamePrefix(SyncConfigBizTypeEnum.getKeyByValue(integer));
                ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) asyncConfig.getAsyncExecutor();
                for (Map.Entry<String, List<TaskInfo>> entry : taskMap1.get(integer).entrySet()) {
                    executor.submit(new AsyncThread(entry.getKey(), entry.getValue(), taskInfoMapper));
                }
            }
        }
    }

}

@Slf4j
class AsyncThread implements Callable {

    private String orderNo;

    private List<TaskInfo> taskInfos;

    private TaskInfoMapper taskInfoMapper;

    public AsyncThread(String orderNo, List<TaskInfo> taskInfos, TaskInfoMapper taskInfoMapper) {
        this.orderNo = orderNo;
        this.taskInfos = taskInfos;
        this.taskInfoMapper = taskInfoMapper;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            int i = 1;
            for (TaskInfo taskInfo : taskInfos) {
                System.out.println(Thread.currentThread().getName() + " - " + orderNo + "-" + JSONObject.toJSONString(taskInfo));
                TaskInfo record = new TaskInfo();
                record.setId(taskInfo.getId());
                record.setSyncStatus(i);
                taskInfoMapper.updateByPrimaryKeySelective(record);
                i++;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}