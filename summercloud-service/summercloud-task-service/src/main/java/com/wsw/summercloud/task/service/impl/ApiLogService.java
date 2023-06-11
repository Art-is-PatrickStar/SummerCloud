package com.wsw.summercloud.task.service.impl;

import com.wsw.summercloud.task.config.ApiLogProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/8 11:14
 */
@Slf4j
public class ApiLogService {
    private final ConcurrentLinkedQueue<String> queue;
    private final ApiLogProperties apiLogProperties;

    public ApiLogService(ApiLogProperties apiLogProperties) {
        this.queue = new ConcurrentLinkedQueue<>();
        this.apiLogProperties = apiLogProperties;
        ScheduledExecutorService saveLogSchedule = Executors.newSingleThreadScheduledExecutor();
        saveLogSchedule.scheduleAtFixedRate(this::persistLog, 0, apiLogProperties.getLogPersistInterval(), TimeUnit.SECONDS);
        ScheduledExecutorService removeExpireLogSchedule = Executors.newSingleThreadScheduledExecutor();
        removeExpireLogSchedule.scheduleAtFixedRate(this::removeExpireLog, 0, apiLogProperties.getLogCleanupInterval(), TimeUnit.HOURS);
    }

    public void appendApiLog(String logs) {
        try {
            queue.add(logs);
        } catch (Exception e) {
            log.error("appendApiLog异常", e);
        }
    }

    private void persistLog() {
        if (queue.isEmpty()) {
            return;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (!queue.isEmpty()) {
                sb.append(queue.poll()).append(",");
            }
            log.info(sb.toString());
        } catch (Exception e) {
            log.error("persistLog异常", e);
        }
    }

    private void removeExpireLog() {
        log.info("removeExpireLog" + apiLogProperties.getLogCleanupInterval());
    }
}
