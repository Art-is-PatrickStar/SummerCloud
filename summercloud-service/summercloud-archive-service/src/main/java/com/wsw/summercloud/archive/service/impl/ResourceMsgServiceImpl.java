package com.wsw.summercloud.archive.service.impl;

import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.archive.client.TaskClient;
import com.wsw.summercloud.archive.service.ResourceInfoService;
import com.wsw.summercloud.archive.service.ResourceMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/6 22:59
 */
@Slf4j
@Service
public class ResourceMsgServiceImpl implements ResourceMsgService {
    @Resource
    private ResourceInfoService resourceInfoService;
    @Resource
    private TaskClient taskClient;
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Override
    public void realTimeResourceHandle(List<ResourceMsg> resourceMsgs) {
        resourceInfoService.saveOrUpdateResourceInfos(resourceMsgs);
        executorService.execute(() -> doArchive(resourceMsgs));
    }

    @Override
    public void historyResourceHandle(List<ResourceMsg> resourceMsgs) {
        executorService.execute(() -> doArchive(resourceMsgs));
    }

    private void doArchive(List<ResourceMsg> resourceMsgs) {
        try {
            taskClient.createTask(resourceMsgs);
        } catch (Exception e) {
            throw new RuntimeException("归档创建任务失败: " + e);
        }
        resourceInfoService.updateResourceInfoArchiveStatus(resourceMsgs);
    }
}
