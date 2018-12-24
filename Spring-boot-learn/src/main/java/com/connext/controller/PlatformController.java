package com.connext.controller;

import com.connext.entity.Platform;
import com.connext.service.PlatformService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/platform")
public class PlatformController {

    private static final Logger logger = LoggerFactory.getLogger(PlatformController.class);

    @Autowired
    private PlatformService platformService;

    @ApiOperation(value = "获取平台信息",notes = "根据{platformname}获取相应平台的信息")
    @RequestMapping(value = "/{platformname}" , method = RequestMethod.GET)
    public Platform get(@PathVariable String platformname){
            Platform platform = platformService.findByNameForBudget(platformname);
            return platform;
    }

    @ApiOperation(value = "获取平台列表",notes = "根据URL中的pageIndex、pageSize获取平台列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex",value = "页码",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页记录数",required = true,paramType = "query",dataType = "int")
    })
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public Page<Platform> getList(@RequestParam int pageIndex,@RequestParam int pageSize){
        return platformService.findAll(pageIndex,pageSize);
    }

    @ApiOperation(value = "插入新的平台",notes = "根据URL中的name添加新的平台到列表")
    @ApiImplicitParam(name = "name" ,value = "平台名称" , required = true,paramType = "query",dataType = "String")
    @RequestMapping(value="/insert",method = RequestMethod.GET)
    public int insert(@RequestParam String name){
        return platformService.insert(name);
    }

    @ApiOperation(value = "批量修改平台信息",notes = "批量修改平台信息")
    @RequestMapping(value = "/batchUpdate" , method = RequestMethod.POST)
    public int batchUpdate(@RequestBody List<Platform> list){
        logger.info("batchUpdate-start:");
        platformService.batchUpdate(list);
        return 1;
    }

}
