package com.cyw.origin.frame.galaxy;

import lombok.extern.slf4j.Slf4j;
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

    @Override
    public void run(ApplicationArguments var1) throws InterruptedException {
        System.out.println("MyApplicationRunner class will be execute when the project was started!");
    }

}