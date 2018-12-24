package com.connext.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogbackTest {

    private final static Logger log = LoggerFactory.getLogger(LogbackTest.class);

    @Test
    public void test() throws Exception {
        log.debug("这是一个日志测试,debug");
        log.info("这是一个日志测试,info");
        log.error("这是一个日志测试,error");
    }

}
