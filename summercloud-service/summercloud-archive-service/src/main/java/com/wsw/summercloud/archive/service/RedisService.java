package com.wsw.summercloud.archive.service;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/13 13:42
 */
public interface RedisService {
    /**
     * 设置key-value
     *
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     * @return void
     */
    void setValueByKey(String key, String value, long timeout, TimeUnit timeUnit);

    /**
     * 根据key获取value
     *
     * @param key
     * @return String
     */
    String getValueByKey(String key);
}
