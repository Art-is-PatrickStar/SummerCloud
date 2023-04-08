package com.wsw.summercloud.archive.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 归档服务RabbitMQ配置类
 * @Author: wangsongwen
 * @Date: 2023/4/6 16:08
 */
@Configuration
public class ArchiveRabbitConfig {
    private final ArchiveRabbitProperties archiveRabbitProperties;

    public ArchiveRabbitConfig(ArchiveRabbitProperties archiveRabbitProperties) {
        this.archiveRabbitProperties = archiveRabbitProperties;
    }

    /**
     * 实时资源Direct交换机 支持持久化
     *
     * @return Exchange
     */
    @Bean("realTimeExchange")
    public Exchange realTimeExchange() {
        return ExchangeBuilder.directExchange(archiveRabbitProperties.getRealtimeExchange()).durable(true).build();
    }

    /**
     * 实时资源队列 支持持久化
     *
     * @return Queue
     */
    @Bean("realTimeQueue")
    public Queue realTimeQueue() {
        return QueueBuilder.durable(archiveRabbitProperties.getRealtimeQueue())
                .withArgument("x-dead-letter-exchange", archiveRabbitProperties.getRealtimeDeadExchange())
                .withArgument("x-dead-letter-routing-key", archiveRabbitProperties.getRealtimeDeadRoutingKey())
                .build();
    }

    /**
     * 通过绑定键将实时资源队列绑定到交换机
     *
     * @param queue
     * @param exchange
     * @return Binding
     */
    @Bean("realTimeBinding")
    public Binding realTimeBinding(@Qualifier("realTimeQueue") Queue queue, @Qualifier("realTimeExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(archiveRabbitProperties.getRealtimeRoutingKey()).noargs();
    }

    /**
     * 实时资源死信交换机
     *
     * @return Exchange
     */
    @Bean("realTimeDeadExchange")
    public Exchange realTimeDeadExchange() {
        return ExchangeBuilder.directExchange(archiveRabbitProperties.getRealtimeDeadExchange()).durable(true).build();
    }

    /**
     * 实时资源死信队列
     *
     * @return Queue
     */
    @Bean("realTimeDeadQueue")
    public Queue realTimeDeadQueue() {
        return QueueBuilder.durable(archiveRabbitProperties.getRealtimeDeadQueue()).build();
    }

    /**
     * 通过绑定键将实时资源死信队列绑定到交换机
     *
     * @param queue
     * @param exchange
     * @return Binding
     */
    @Bean("realTimeDeadBinding")
    public Binding realTimeDeadBinding(@Qualifier("realTimeDeadQueue") Queue queue, @Qualifier("realTimeDeadExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(archiveRabbitProperties.getRealtimeDeadRoutingKey()).noargs();
    }

    /**
     * 历史资源Direct交换机 支持持久化
     *
     * @return Exchange
     */
    @Bean("historyExchange")
    public Exchange historyExchange() {
        return ExchangeBuilder.directExchange(archiveRabbitProperties.getHistoryExchange()).durable(true).build();
    }

    /**
     * 历史资源队列 支持持久化
     *
     * @return Queue
     */
    @Bean("historyQueue")
    public Queue historyQueue() {
        return QueueBuilder.durable(archiveRabbitProperties.getHistoryQueue()).build();
    }

    /**
     * 通过绑定键将历史资源队列绑定到交换机
     *
     * @param queue
     * @param exchange
     * @return Binding
     */
    @Bean("historyBinding")
    public Binding historyBinding(@Qualifier("historyQueue") Queue queue, @Qualifier("historyExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(archiveRabbitProperties.getHistoryRoutingKey()).noargs();
    }

    @Bean(name = "consumerBatchContainerFactory")
    public SimpleRabbitListenerContainerFactory consumerBatchContainerFactory(ConnectionFactory connectionFactory) {
        //创建 SimpleRabbitListenerContainerFactory 对象
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //每个队列并发监听的数量
        factory.setConcurrentConsumers(5);
        //最大的并发监听数量
        factory.setMaxConcurrentConsumers(10);
        //批量消费的属性
        factory.setBatchListener(true);
        factory.setConsumerBatchEnabled(true);
        //批量处理消息的大小
        factory.setBatchSize(300);
        //预拉取数量
        factory.setPrefetchCount(30);
        //手动确认
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }
}

