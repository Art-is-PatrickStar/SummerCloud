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

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 历史资源消费队列
 * @Author: wangsongwen
 * @Date: 2023/4/5 20:44
 */
@Slf4j
@Component
public class ResourceInfoHistoryQueue {
    @Resource
    private ArchiveRabbitProperties archiveRabbitProperties;
    @Resource
    private ResourceMsgService resourceMsgService;

    @RabbitListener(queues = "#{archiveRabbitProperties.getHistoryQueue()}")
    public void historyMessage(List<Message> messages, Channel channel) throws Exception {
        List<String> messageBodys = new ArrayList<>();
        try {
            messageBodys = messages.stream().map(e -> new String(e.getBody())).collect(Collectors.toList());
            List<ResourceMsg> resourceMsgs = messageBodys.stream().map(e -> JSON.parseObject(e, ResourceMsg.class)).collect(Collectors.toList());
            log.info("历史资源队列接收到资源信息: " + resourceMsgs);
            resourceMsgService.historyResourceHandle(resourceMsgs);
            for (Message message : messages) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            }
            log.info("历史资源队列处理资源信息成功");
        } catch (Exception e) {
            for (Message message : messages) {
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            }
            log.error("历史资源队列处理资源信息失败: " + e.getMessage() + ", messageBodys: " + messageBodys);
        }
    }
}
