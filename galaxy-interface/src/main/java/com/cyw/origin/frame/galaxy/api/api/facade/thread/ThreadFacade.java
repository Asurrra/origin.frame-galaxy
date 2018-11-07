package com.cyw.origin.frame.galaxy.api.api.facade.thread;

import com.cyw.origin.frame.galaxy.api.api.dto.ThreadDto;
import com.weimob.soa.common.response.SoaResponse;

/**
 * @author yiwen.chang
 */
public interface ThreadFacade {

    SoaResponse<Void, Void> threadTest(ThreadDto dto);

}