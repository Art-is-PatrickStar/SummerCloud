package com.wsw.summercloud.task.service;

import com.wsw.summercloud.api.dto.TaskJobRequestDto;
import com.wsw.summercloud.task.entities.TaskJobEntity;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/13 17:00
 */
@SpringBootTest
public class RedisLockTests {
    @Resource
    private RedissonClient redissonClient;
    @Resource
    private TaskJobService taskJobService;

    @Test
    void lockTest() {
        String lockName = "wswtest";
        RLock lock = redissonClient.getLock(lockName);
        try {
            boolean tryLock = lock.tryLock(10, 10, TimeUnit.SECONDS);
            if (!tryLock) {
                throw new RuntimeException("分布式锁获取失败, lockName: " + lockName);
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
            if (lock != null && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
