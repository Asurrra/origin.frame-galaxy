package com.cyw.origin.frame.galaxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 */
@Slf4j
@Component
public class MyApplicationRunner implements ApplicationRunner {

    private ThreadPoolTaskExecutor orderAndRightExecutor = null;
    private ThreadPoolTaskExecutor couponExecutor = null;
    private ThreadPoolTaskExecutor otherExecutor = null;

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

    @Override
    public void run(ApplicationArguments var1) {
        System.out.println("MyApplicationRunner class will be execute when the project was started!");
        orderAndRightExecutor = init("ORDER-", 10, 2000);
        couponExecutor = init("COUPON-", 5, 500);
        otherExecutor = init("OTHER-", 5, 200);
    }

}