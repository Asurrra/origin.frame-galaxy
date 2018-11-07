package com.cyw.origin.frame.galaxy.demo.facade.impl;

import com.cyw.origin.frame.galaxy.api.api.dto.ThreadDto;
import com.cyw.origin.frame.galaxy.api.api.facade.thread.ThreadFacade;
import com.cyw.origin.frame.galaxy.common.enums.ReturnCode;
import com.cyw.origin.frame.galaxy.common.response.resp.SoaResp;
import com.cyw.origin.frame.galaxy.demo.service.DemoService;
import com.weimob.soa.common.response.SoaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yiwen.chang
 */
@Slf4j
@Service
public class DemoFacadeImpl implements ThreadFacade {

    @Autowired
    private DemoService demoService;

    @Override
    public SoaResponse<Void, Void> threadTest(ThreadDto dto) {
        try {
            demoService.threadTest(dto);
            return SoaResp.success();
        } catch (Exception e) {
            log.error("threadTest 异常:{}", e);
            return SoaResp.failure(ReturnCode.SERVICE_EXCEPTION);
        }
    }
}