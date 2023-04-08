package com.wsw.summercloud.archive.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/6 15:49
 */
@Data
@Component
@ConfigurationProperties(prefix = "archive")
public class ArchiveRabbitProperties {
    private String realtimeExchange;
    private String realtimeRoutingKey;
    private String realtimeQueue;

    private String historyExchange;
    private String historyRoutingKey;
    private String historyQueue;

    private String realtimeDeadExchange;
    private String realtimeDeadRoutingKey;
    private String realtimeDeadQueue;
}
