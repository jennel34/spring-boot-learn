package com.connext.controller;

import com.connext.entity.Platform;
import com.connext.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "批量插入品类信息", notes = "批量插入品类信息")
    @RequestMapping(value = "/batchUpdate", method = RequestMethod.POST)
    public int batchUpdate(@RequestBody List<Platform> list) {
        logger.info("batchUpdate-start:");
        categoryService.batchUpdate(list);
        return 1;
    }

}
