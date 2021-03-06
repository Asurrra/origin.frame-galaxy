package com.cyw.origin.frame.galaxy.api.demo;

import com.cyw.origin.frame.galaxy.api.dto.ThreadDto;
import com.cyw.origin.frame.galaxy.common.response.CommonResponse;
import com.cyw.origin.frame.galaxy.common.response.resp.CommonResp;
import com.cyw.origin.frame.galaxy.vo.ThreadVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yiwen.chang
 */
@RestController
@RequestMapping("/demo")
@Api(value = "DemoController")
public class DemoController {

    @ApiOperation(value = "hello", httpMethod = "POST", produces = "application/json; charset=utf-8", notes = "hello", response = CommonResponse.class)
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public CommonResponse<ThreadVo> say(@RequestBody ThreadDto threadDto) {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CommonResp.success();
    }

}