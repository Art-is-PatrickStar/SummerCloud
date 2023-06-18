package com.wsw.summercloud.archive.service.impl;

import com.wsw.summercloud.archive.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/13 13:46
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void setValueByKey(String key, String value, long timeout, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    @Override
    public String getValueByKey(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
