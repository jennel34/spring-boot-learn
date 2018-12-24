package com.connext.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisComponent {

    private static final Logger logger = LoggerFactory.getLogger(RedisComponent.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value) {
        ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
        if (!this.stringRedisTemplate.hasKey(key)) {
            ops.set(key, value);
            logger.info("set key success");
        } else {
//            存在打印之前的value值
            logger.info("this key = " + ops.get(key));
        }
    }

    public String get(String key) {
        logger.info("get key success");
        return this.stringRedisTemplate.opsForValue().get(key);
    }

    public void del(String key) {
        logger.info("delete key success");
        this.stringRedisTemplate.delete(key);
    }
}
