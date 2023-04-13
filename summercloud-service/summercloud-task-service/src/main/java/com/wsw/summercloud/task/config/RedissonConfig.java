package com.wsw.summercloud.task.config;

import cn.hutool.core.util.StrUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/26 14:02
 */
@Configuration
public class RedissonConfig {
    @Resource
    private RedisProperties redisProperties;
    /*@Value("${spring.redis.sentinel.nodes}")
    private String nodes;
    @Value("${spring.redis.sentinel.master}")
    private String master;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.database}")
    private Integer database;
    @Value("${spring.redis.timeout}")
    private Integer timeout;*/

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 哨兵模式
        /*String[] nodeStr = nodes.split(",");
        List<String> newNodes = new ArrayList<>(nodeStr.length);
        Arrays.stream(nodeStr).forEach((index) -> newNodes.add(
                index.startsWith("redis://") ? index : "redis://" + index));
        SentinelServersConfig serverConfig = config.useSentinelServers()
                .addSentinelAddress(newNodes.toArray(new String[0]))
                .setMasterName(master)
                .setTimeout(timeout)
                .setDatabase(database);
        if (StrUtil.isNotBlank(password)) {
            serverConfig.setPassword(password);
        }*/
        // 单节点
        SingleServerConfig singleServer = config.useSingleServer();
        String redisUrl = String.format("redis://%s:%s", redisProperties.getHost(), redisProperties.getPort());
        singleServer.setAddress(redisUrl);
        singleServer.setDatabase(redisProperties.getDatabase());
        if (StrUtil.isNotBlank(redisProperties.getPassword())) {
            singleServer.setPassword(redisProperties.getPassword());
        }
        return Redisson.create(config);
    }
}
