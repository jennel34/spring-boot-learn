package com.connext.controller;

import com.connext.service.EmailService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @ApiOperation(value = "发送邮件" , notes = "根据标题、内容、收件人发送邮件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "标题",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "text",value = "内容",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "toMail",value = "收件人",required = true,paramType = "query",dataType = "String")
    })
    @RequestMapping(value = "/send",method = RequestMethod.GET)
    public boolean send(@RequestParam String title,@RequestParam String text,@RequestParam String toMail){
        logger.info("title:"+title+";text:"+text+";toMail"+toMail);
        return emailService.send(title,text,toMail);
    }
}
