package com.cyw.origin.frame.galaxy.demo.manage;

import com.cyw.origin.frame.galaxy.demo.domain.Demo;

/**
 * @author yiwen.chang
 */
public interface DemoManage {

    Integer threadTest(Demo demo);

    void threadTask();

}