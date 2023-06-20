package com.wsw.summercloud.user.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.wsw.summercloud.user.entities.UserInfoEntity;
import com.wsw.summercloud.user.repository.UserInfoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 本地缓存
 * @Author: wangsongwen
 * @Date: 2023/5/26 13:18
 */
@Configuration
public class MemoryCacheConfiguration {
    @Bean("userInfoEntityCache")
    public LoadingCache<String, List<UserInfoEntity>> userInfoEntityCache(UserInfoRepository userInfoRepository) {
        return Caffeine.newBuilder()
                .initialCapacity(16)
                .maximumSize(1000)
                .refreshAfterWrite(1, TimeUnit.MINUTES)
                .build(key -> userInfoRepository.findAll());
    }
}