package com.wsw.summercloud.archive.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/13 16:16
 */
@Slf4j
@SpringBootTest
public class RedisServiceTests {
    @Autowired
    private RedisService redisService;

    @Test
    void setTest() {
        redisService.setValueByKey("name", "wsw", 300, TimeUnit.SECONDS);
    }

    @Test
    void getTest() {
        String name = redisService.getValueByKey("name");
        log.info("name: {}", name);
    }
}
