package com.connext.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ApiOperation(value = "测试异常", notes = "测试异常处理")
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        throw new RuntimeException("测试异常");
    }
}
