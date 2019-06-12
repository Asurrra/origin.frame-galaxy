package com.cyw.origin.frame.galaxy.task;

import com.alibaba.fastjson.JSONObject;
import com.cyw.origin.frame.galaxy.demo.dao.TaskInfoMapper;
import com.cyw.origin.frame.galaxy.demo.domain.TaskInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Component
@EnableScheduling
public class AsyncJob {

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    private ThreadPoolTaskExecutor orderAndRightExecutor = null;
    private ThreadPoolTaskExecutor couponExecutor = null;
    private ThreadPoolTaskExecutor otherExecutor = null;

//    @PostConstruct
    private void executorInit() {
        orderAndRightExecutor = init("ORDER-", 10, 2000);
        couponExecutor = init("COUPON-", 5, 500);
        otherExecutor = init("OTHER-", 5, 200);
    }

    private ThreadPoolTaskExecutor init(String threadName, Integer corePoolSize, Integer queueCapacity) {
        log.info("初始化线程池：{}", threadName);
        ThreadPoolTaskExecutor executorService = new ThreadPoolTaskExecutor();
        executorService.setCorePoolSize(corePoolSize);
        executorService.setMaxPoolSize(corePoolSize * 3);
        executorService.setQueueCapacity(queueCapacity);
        executorService.setKeepAliveSeconds(60);
        executorService.setThreadNamePrefix(threadName);
        executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executorService.setWaitForTasksToCompleteOnShutdown(true);
        executorService.setAwaitTerminationSeconds(60);
        executorService.initialize();
        return executorService;
    }

//    @Scheduled(initialDelay = 1000, fixedDelay = 5 * 1000)
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
            List<Integer> orderList = Arrays.asList(-1, 0, 1, 7, 9, 10, 11);
            List<Integer> couponList = Arrays.asList(5, 6);
            for (Integer integer : taskMap1.keySet()) {
//                ThreadPoolTaskExecutor executor = init(SyncConfigBizTypeEnum.getKeyByValue(integer), 10, 500);
                ThreadPoolTaskExecutor executor;
                if (orderList.contains(integer)) {
                    executor = orderAndRightExecutor;
                } else if (couponList.contains(integer)) {
                    executor = couponExecutor;
                } else {
                    executor = otherExecutor;
                }
                for (Map.Entry<String, List<TaskInfo>> entry : taskMap1.get(integer).entrySet()) {
                    executor.submit(new AsyncThread(entry.getKey(), entry.getValue(), taskInfoMapper));
                }
//                executor.shutdown();
            }
            log.info("====== AsyncJob schedule end! use time:{} ms =======", System.currentTimeMillis() - startTime1);
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