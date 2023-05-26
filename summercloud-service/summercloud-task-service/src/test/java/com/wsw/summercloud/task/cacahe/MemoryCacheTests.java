package com.wsw.summercloud.task.cacahe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.wsw.summercloud.common.constants.MemoryCacheConstant;
import com.wsw.summercloud.task.entities.TaskJobEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/5/26 13:34
 */
@Slf4j
@SpringBootTest
public class MemoryCacheTests {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private LoadingCache<String, List<TaskJobEntity>> taskJobEntityCache;

    @Test
    void getAllTaskJobs() throws JsonProcessingException {
        List<TaskJobEntity> taskJobEntities = taskJobEntityCache.get(MemoryCacheConstant.TASK_JOB_ENTITY_CACHE);
        log.info(objectMapper.writeValueAsString(taskJobEntities));
    }
}
