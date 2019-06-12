package com.cyw.origin.frame.galaxy.controller;

import com.cyw.origin.frame.galaxy.common.response.CommonResponse;
import com.cyw.origin.frame.galaxy.common.response.resp.CommonResp;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yiwen.chang
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public CommonResponse say(@RequestBody Map<String, Object> threadDto) {
        try {
            System.out.println(Thread.currentThread().getName() + "-test");
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CommonResp.success();
    }

}