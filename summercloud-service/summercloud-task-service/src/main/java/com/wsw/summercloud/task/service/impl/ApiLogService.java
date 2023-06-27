package com.wsw.summercloud.task.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wsw.summercloud.api.data.EsData;
import com.wsw.summercloud.api.dto.ApiLogDto;
import com.wsw.summercloud.task.config.ApiLogProperties;
import com.wsw.summercloud.task.service.ElasticSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
    private ElasticSearchService elasticSearchService;

    private final ConcurrentLinkedQueue<ApiLogDto> queue;
    private final ApiLogProperties apiLogProperties;

    public ApiLogService(ApiLogProperties apiLogProperties) {
        this.queue = new ConcurrentLinkedQueue<>();
        this.apiLogProperties = apiLogProperties;
        ScheduledExecutorService saveLogSchedule = Executors.newSingleThreadScheduledExecutor();
        saveLogSchedule.scheduleAtFixedRate(this::persistLog, 0, apiLogProperties.getLogPersistInterval(), TimeUnit.SECONDS);
        ScheduledExecutorService removeExpireLogSchedule = Executors.newSingleThreadScheduledExecutor();
        removeExpireLogSchedule.scheduleAtFixedRate(this::removeExpireLog, 0, apiLogProperties.getLogCleanupInterval(), TimeUnit.HOURS);
    }

    public void appendApiLog(ApiLogDto apiLogDto) {
        try {
            queue.add(apiLogDto);
        } catch (Exception e) {
            log.error("appendApiLog异常", e);
        }
    }

    private void persistLog() {
        if (queue.isEmpty()) {
            return;
        }
        try {
            ApiLogDto apiLogDto = new ApiLogDto();
            StringBuilder apiLogBuilder = new StringBuilder();
            EsData esData = new EsData();
            while (!queue.isEmpty()) {
                apiLogDto = queue.poll();
            }
            apiLogBuilder.append("请求方法:").append(apiLogDto.getMethod()).append(",");
            apiLogBuilder.append("请求用户:").append(apiLogDto.getOperateUser()).append(",");
            apiLogBuilder.append("请求参数:").append(apiLogDto.getParams()).append(",");
            apiLogBuilder.append("请求结果:").append(apiLogDto.getResult()).append(",");
            apiLogBuilder.append("请求耗时:").append(apiLogDto.getCostTime()).append("ms");
            if (StrUtil.isNotBlank(apiLogDto.getException())) {
                apiLogBuilder.append(",请求异常:").append(apiLogDto.getException());
            }
            esData.setData(apiLogBuilder.toString());
            boolean indexExist = elasticSearchService.isIndexExist("api_log");
            if (!indexExist) {
                elasticSearchService.createIndex("api_log");
            }
            elasticSearchService.createDocument("api_log", apiLogDto.getId().toString(), esData);
            log.info(apiLogBuilder.toString());
        } catch (Exception e) {
            log.error("persistLog异常", e);
        }
    }

    private void removeExpireLog() {
        log.info("removeExpireLog" + apiLogProperties.getLogCleanupInterval());
    }
}
