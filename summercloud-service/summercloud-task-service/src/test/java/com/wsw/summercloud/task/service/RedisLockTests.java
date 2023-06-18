package com.wsw.summercloud.task.service;

import com.wsw.summercloud.api.basic.ResultStatus;
import com.wsw.summercloud.common.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/13 17:00
 */
@SpringBootTest
public class RedisLockTests {
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private TaskJobService taskJobService;

    @Test
    void lockTest() {
        String lockName = "wswtest";
        RLock rLock = redissonClient.getLock(lockName);
        try {
            boolean tryLock = rLock.tryLock(10, 10, TimeUnit.SECONDS);
            if (!tryLock) {
                throw new BusinessException(ResultStatus.REDIS_LOCK_GET_FAILD);
            }
//            TaskJobRequestDto taskJobRequestDto = new TaskJobRequestDto();
//            taskJobRequestDto.setJobId(20L);
//            taskJobRequestDto.setResourceId(20L);
//            TaskJobEntity taskJobEntity = taskJobService.getTaskJobByResourceId(taskJobRequestDto.getResourceId());
//            if (!Objects.isNull(taskJobEntity)) {
//                taskJobService.insertTaskJobs(taskJobRequestDto);
//            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (rLock != null && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
    }
}
