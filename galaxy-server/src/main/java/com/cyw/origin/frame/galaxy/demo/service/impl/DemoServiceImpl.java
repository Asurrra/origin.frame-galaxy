package com.cyw.origin.frame.galaxy.demo.service.impl;

import com.cyw.origin.frame.galaxy.api.dto.ThreadDto;
import com.cyw.origin.frame.galaxy.bean.DubboBean;
import com.cyw.origin.frame.galaxy.bean.TestBean;
import com.cyw.origin.frame.galaxy.demo.dao.DemoMapper;
import com.cyw.origin.frame.galaxy.demo.domain.Demo;
import com.cyw.origin.frame.galaxy.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 相对具体的业务逻辑服务层
 * @author yiwen.chang
 * @version 1.0.0
 * @date 2018/7/16
 */
@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private TestBean testBean;

    @Autowired
    private DubboBean dubboBean;

    @Override
    public Integer threadTest(ThreadDto dto) {
        Demo demo = new Demo();
        BeanUtils.copyProperties(dto, demo);
        return demoMapper.save(demo);
    }

    @Async
    public void testAsync1() {
        try {
            Thread.sleep(1000);
            System.out.println("线程一");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void testAsync2() {
        System.out.println("线程2");
    }

}