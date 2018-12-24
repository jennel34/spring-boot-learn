package com.connext.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger =  LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value="获取用户信息", notes="欢迎用户")
    @RequestMapping(method = RequestMethod.GET)
    public String user(){
        logger.debug("获取用户信息-欢迎用户,debug");
        logger.info("获取用户信息-欢迎用户,info");
        logger.error("获取用户信息-欢迎用户,error");
        return "hello world!";
    }

    @ApiOperation(value="获取用户信息", notes="根据{username}获取对应用户信息")
    @RequestMapping(value = "/get/{username}" , method = RequestMethod.GET)
    public String get(@PathVariable String username){
        return "welcome,"+username;
    }

    @ApiOperation(value="登录", notes="根据URL中的username、pwd进行用户登录验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true,paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pwd", value = "密码", required = true,paramType = "query", dataType = "string")
    })
//    paramType不同值对应的是不同的参数类型：
//    path-@PathVariable
//    query-@RequestParam
//    body-@RequestBody
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login(@RequestParam String username, @RequestParam String pwd){
        return "user:" + username + ",password:" + pwd;
    }

    @ApiOperation(value="测试异常", notes="测试异常处理")
    @RequestMapping(value ="/error",method = RequestMethod.GET)
    public String error(){
        throw new RuntimeException("测试异常");
    }

    @ApiOperation(value="测试异常a", notes="测试异常处理a")
    @RequestMapping(value ="/errora",method = RequestMethod.GET)
    public String errora() throws Exception{
        throw new Exception("测试异常");
    }

    @ApiOperation(value = "获取用户session",notes = "获取用户session")
    @RequestMapping(value = "/session",method = RequestMethod.GET)
    public String getSession(HttpSession httpsession){
        UUID uid = (UUID) httpsession.getAttribute("uuid");
        if(uid == null){
            uid = UUID.randomUUID();
        }
        httpsession.setAttribute("uuid",uid);
        return httpsession.getId();
    }
}
