package com.wsw.summercloud.archive.rabbit;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.archive.config.ArchiveRabbitProperties;
import com.wsw.summercloud.archive.service.ResourceMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 实时资源消费队列
 * @Author: wangsongwen
 * @Date: 2023/4/5 20:44
 */
@Slf4j
@Component
public class ResourceInfoRealtimeQueue {
    private final ArchiveRabbitProperties archiveRabbitProperties;
    private final ResourceMsgService resourceMsgService;

    public ResourceInfoRealtimeQueue(ArchiveRabbitProperties archiveRabbitProperties, ResourceMsgService resourceMsgService) {
        this.archiveRabbitProperties = archiveRabbitProperties;
        this.resourceMsgService = resourceMsgService;
    }

    @RabbitListener(queues = "#{archiveRabbitProperties.getRealtimeQueue()}", containerFactory = "consumerBatchContainerFactory")
    public void realTimeMessage(List<Message> messages, Channel channel) throws Exception {
        List<String> messageBodys = new ArrayList<>();
        try {
            messageBodys = messages.stream().map(e -> new String(e.getBody())).collect(Collectors.toList());
            List<ResourceMsg> resourceMsgs = messageBodys.stream().map(e -> JSON.parseObject(e, ResourceMsg.class)).collect(Collectors.toList());
            List<Long> resourceIds = resourceMsgs.stream().map(ResourceMsg::getResourceId).collect(Collectors.toList());
            resourceMsgService.realTimeResourceHandle(resourceMsgs);
            for (Message message : messages) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            }
            log.info("实时资源队列处理资源信息成功, resourceIds: " + resourceIds);
        } catch (Exception e) {
            for (Message message : messages) {
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            }
            log.error("实时资源队列处理资源信息失败: " + e.getMessage() + ", messageBodys: " + messageBodys);
        }
    }

    //@RabbitListener(queues = "#{archiveRabbitProperties.getRealtimeDeadQueue()}", containerFactory = "consumerBatchContainerFactory")
    public void realTimeDeadMessage(List<Message> messages, Channel channel) throws Exception {
        List<String> messageBodys = new ArrayList<>();
        try {
            messageBodys = messages.stream().map(e -> new String(e.getBody())).collect(Collectors.toList());
            List<ResourceMsg> resourceMsgs = messageBodys.stream().map(e -> JSON.parseObject(e, ResourceMsg.class)).collect(Collectors.toList());
            //死信队列补偿处理
            //resourceMsgService.realTimeResourceHandle(resourceMsgs);
            for (Message message : messages) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            }
            log.info("实时资源死信队列处理资源信息成功, messageBodys: " + messageBodys);
        } catch (Exception e) {
            for (Message message : messages) {
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            }
            log.error("实时资源死信队列处理资源信息失败: " + e.getMessage() + ", messageBodys: " + messageBodys);
        }
    }
}
