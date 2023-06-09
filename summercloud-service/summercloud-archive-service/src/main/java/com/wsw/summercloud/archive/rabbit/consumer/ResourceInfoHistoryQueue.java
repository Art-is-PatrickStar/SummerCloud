package com.wsw.summercloud.archive.rabbit.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.archive.config.ArchiveRabbitProperties;
import com.wsw.summercloud.archive.rabbit.convert.ResourceMsgConvert;
import com.wsw.summercloud.archive.service.ResourceMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    @Autowired
    private ArchiveRabbitProperties archiveRabbitProperties;
    @Autowired
    private ResourceMsgService resourceMsgService;
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "#{archiveRabbitProperties.getHistoryQueue()}")
    public void historyMessage(List<Message> messages, Channel channel) throws Exception {
        List<String> messageBodys = new ArrayList<>();
        try {
            messageBodys = messages.stream().map(e -> new String(e.getBody())).collect(Collectors.toList());
            List<ResourceMsg> resourceMsgs = ResourceMsgConvert.convert(messageBodys);
            resourceMsgService.historyResourceHandle(resourceMsgs);
            for (Message message : messages) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            }
            log.info("历史资源队列处理资源信息成功, messageBodys: " + objectMapper.writeValueAsString(messageBodys));
        } catch (Exception e) {
            for (Message message : messages) {
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            }
            log.error("历史资源队列处理资源信息失败: " + e + ", messageBodys: " + objectMapper.writeValueAsString(messageBodys));
        }
    }
}
