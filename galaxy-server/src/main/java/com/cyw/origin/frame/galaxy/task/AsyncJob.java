package com.cyw.origin.frame.galaxy.task;

import com.cyw.origin.frame.galaxy.conf.AsyncConfig;
import com.cyw.origin.frame.galaxy.demo.dao.TaskInfoMapper;
import com.cyw.origin.frame.galaxy.demo.domain.TaskInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
            Integer size1 = 0;
            for (Integer integer : taskMap1.keySet()) {
                System.out.println(integer);
                for (String bizId : taskMap1.get(integer).keySet()) {
                    size1 += taskMap1.get(integer).get(bizId).size();
                    System.out.println(bizId + " - " + taskMap1.get(integer).get(bizId));
                }
                System.out.println();
            }
            System.out.println(size1);
            log.info("====== AsyncJob schedule end! use time:{} ms =======", System.currentTimeMillis() - startTime1);


        }

//        Executor executor = asyncConfig.getAsyncExecutor();


//        if (taskInfoList.size() > 100) {
//            log.warn("single队列消费能力不足,当前队列数：{}", taskInfoList.size());
//            try {
//            } catch (Exception e) {
//                log.error("", e);
//            }
//        }
    }

}
