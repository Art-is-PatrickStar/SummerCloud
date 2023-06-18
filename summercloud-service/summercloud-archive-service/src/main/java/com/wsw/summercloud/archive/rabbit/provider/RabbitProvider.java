package com.wsw.summercloud.archive.rabbit.provider;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/20 13:58
 */
@Slf4j
@Component
public class RabbitProvider implements RabbitTemplate.ConfirmCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate() {
        rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            log.error("消息发送到exchange失败: correlationData:{}, cause:{}", correlationData, cause);
        }
    }

    public void sendMessage(String exchangeName, String routingKey, String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message, correlationData);
    }
}
