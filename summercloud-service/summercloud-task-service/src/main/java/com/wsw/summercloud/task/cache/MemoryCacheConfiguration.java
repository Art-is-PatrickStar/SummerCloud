package com.wsw.summercloud.task.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.wsw.summercloud.common.constants.MemoryCacheConstant;
import com.wsw.summercloud.task.entities.TaskJobEntity;
import com.wsw.summercloud.task.mapper.TaskJobMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 本地缓存
 * @Author: wangsongwen
 * @Date: 2023/5/26 13:18
 */
@Configuration
public class MemoryCacheConfiguration {
    @Bean("taskJobEntityCache")
    public LoadingCache<String, List<TaskJobEntity>> taskJobEntityCache(TaskJobMapper taskJobMapper) {
        LoadingCache<String, List<TaskJobEntity>> taskJobEntityCache = Caffeine.newBuilder()
                .initialCapacity(16)
                .maximumSize(1000)
                .refreshAfterWrite(1, TimeUnit.MINUTES)
                .build(key -> taskJobMapper.getAllTaskJobs());
        taskJobEntityCache.refresh(MemoryCacheConstant.TASK_JOB_ENTITY_CACHE);
        return taskJobEntityCache;
    }
}
