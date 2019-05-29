package com.cyw.origin.frame.galaxy.demo.manage.impl;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.cyw.origin.frame.galaxy.bean.DubboBean;
import com.cyw.origin.frame.galaxy.bean.TestBean;
import com.cyw.origin.frame.galaxy.conf.AsyncConfig;
import com.cyw.origin.frame.galaxy.demo.dao.DemoDao;
import com.cyw.origin.frame.galaxy.demo.domain.Demo;
import com.cyw.origin.frame.galaxy.demo.manage.DemoManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author yiwen.chang
 */
@Slf4j
@Service
public class DemoManageImpl implements DemoManage {

    @Autowired
    private DemoDao demoDao;

    @Autowired
    private AsyncConfig asyncConfig;

    @Autowired
    private AsyncConfig uploadFileExecutor;

    @Value("${spring.common}")
    private String test;

    @Autowired
    private TestBean testBean;

    @Autowired
    private DubboBean dubboBean;

    @ApolloConfig("common")
    private Config config;

    @Override
    public Integer threadTest(Demo demo) {
        return demoDao.save(demo);
    }

    @Override
    public void threadTask() {
        System.out.println(config.getProperty("spring.common", "a"));
        System.out.println(config.getProperty("batch", "b"));

        System.out.println(testBean.getSpringCommon());
        System.out.println(testBean.getBatch());

        System.out.println(dubboBean.getName());
        System.out.println(dubboBean.getOwner());
//        asyncConfig.getAsyncExecutor().execute(new ThreadTaskWorker());
    }

    public class ThreadTaskWorker implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程名称：" + Thread.currentThread().getName());
            }
        }

//        @Async("asyncServiceExecutor")
        public void executeAsyncStart() {
            System.out.println(Thread.currentThread().getName() + "---start!");
            try{
                Thread.sleep(5000);
            }catch(Exception e){
                e.printStackTrace();
            }
            System.out.println("end!");
        }
    }


}