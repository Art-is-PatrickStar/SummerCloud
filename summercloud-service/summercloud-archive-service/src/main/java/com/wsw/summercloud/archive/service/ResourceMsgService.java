package com.wsw.summercloud.archive.service;

import com.alibaba.fastjson.JSON;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.archive.client.TaskClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/6 22:59
 */
@Slf4j
@Service
public class ResourceMsgService {
    private final ResourceInfoService resourceInfoService;
    private final TaskClient taskClient;
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    public ResourceMsgService(ResourceInfoService resourceInfoService, TaskClient taskClient) {
        this.resourceInfoService = resourceInfoService;
        this.taskClient = taskClient;
    }

    public void realTimeResourceHandle(List<ResourceMsg> resourceMsgs) {
        resourceInfoService.saveOrUpdateResourceInfos(resourceMsgs);
        executorService.execute(() -> doArchive(resourceMsgs));
    }

    public void historyResourceHandle(List<ResourceMsg> resourceMsgs) {
        executorService.execute(() -> doArchive(resourceMsgs));
    }

    public void doArchive(List<ResourceMsg> resourceMsgs) {
        try {
            taskClient.createTask(resourceMsgs);
        } catch (Exception e) {
            throw new RuntimeException("归档创建任务失败: " + e);
        }
        resourceInfoService.updateResourceInfoArchiveStatus(resourceMsgs);
    }
}
