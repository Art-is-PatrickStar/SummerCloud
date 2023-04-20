package com.wsw.summercloud.archive.xxljob;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.archive.config.ArchiveRabbitProperties;
import com.wsw.summercloud.archive.entities.ResourceInfoEntity;
import com.wsw.summercloud.archive.rabbit.provider.RabbitProvider;
import com.wsw.summercloud.archive.service.ResourceInfoService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 22:11
 */
@Slf4j
@Component
public class ArchiveCompensateJob {
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private RabbitProvider rabbitProvider;
    @Resource
    private ResourceInfoService resourceInfoService;
    @Resource
    private ArchiveRabbitProperties archiveRabbitProperties;

    @XxlJob("archiveCompensateJobHandler")
    public void archiveCompensateJobHandler() throws Exception {
        XxlJobHelper.log("archiveCompensateJob start.");
        archiveCompensate();
        XxlJobHelper.log("archiveCompensateJob end.");
    }

    private void archiveCompensate() {
        List<ResourceInfoEntity> notArchivedResources = resourceInfoService.getNotArchivedResources();
        if (CollectionUtil.isNotEmpty(notArchivedResources)) {
            notArchivedResources.forEach(e -> {
                String message = null;
                try {
                    ResourceMsg resourceMsg = new ResourceMsg();
                    resourceMsg.setResourceId(e.getResourceId());
                    resourceMsg.setData(e.getData());
                    resourceMsg.setEnableType(e.getEnableType());
                    message = objectMapper.writeValueAsString(resourceMsg);
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException("序列化异常: " + ex);
                }
                rabbitProvider.sendMessage(archiveRabbitProperties.getRealtimeExchange(), archiveRabbitProperties.getRealtimeRoutingKey(), message);
            });
            XxlJobHelper.log("本次执行补偿归档任务,共发送消息{}条", notArchivedResources.size());
        }
    }
}
