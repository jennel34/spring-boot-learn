package com.connext.test;

import com.connext.ConfigCenter;
import com.connext.controller.UserController;
import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    private MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void contextLoads() throws Exception {
        RequestBuilder request = get("/user");
        mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("hello world!"));
        request = get("/user/get/qxl");
        mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("welcome,qxl"));
        request = get("/user/login").param("username", "qxl").param("pwd", "123456");
        mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("user:qxl,password:123456"));
    }

    @Test
    public void test() throws Exception {
        ConfigCenter cfg = ConfigFactory.create(ConfigCenter.class);
        System.out.println(cfg.getAuthor());
        ConfigCenter instance = ConfigCache.getOrCreate(ConfigCenter.class);
        System.out.println(instance.getTitle());
        Assert.assertEquals("xwy", cfg.getAuthor());
        Assert.assertEquals("学习教程", instance.getTitle());
    }
}
