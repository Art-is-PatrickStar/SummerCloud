package com.wsw.summercloud.archive.cache;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.wsw.summercloud.archive.entities.ArchiveNodeEntity;
import com.wsw.summercloud.archive.mapper.ArchiveNodeMapper;
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
    @Bean("archiveNodeEntityCache")
    public LoadingCache<String, List<ArchiveNodeEntity>> archiveNodeEntityCache(ArchiveNodeMapper archiveNodeMapper) {
        return Caffeine.newBuilder()
                .initialCapacity(16)
                .maximumSize(1000)
                .refreshAfterWrite(1, TimeUnit.MINUTES)
                .build(key -> new LambdaQueryChainWrapper<>(archiveNodeMapper).eq(ArchiveNodeEntity::getIsDelete, 0).list());
    }
}
