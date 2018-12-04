package com.cyw.origin.frame.galaxy;

import com.cyw.origin.frame.galaxy.demo.manage.DemoManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author: yiwen.chang
 * @date: 2018/8/2
 * @version: 1.0
 */
@Slf4j
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private DemoManage demoManage;

    @Override
    public void run(ApplicationArguments var1) throws InterruptedException {
        System.out.println("MyApplicationRunner class will be execute when the project was started!");
        for (; ; ) {
            demoManage.threadTask();
            Thread.sleep(3000L);
        }
//        demoManage.threadTest(new Demo(1L, "test", 18, new Date()));
    }

}