package com.wsw.summercloud.archive.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.wsw.summercloud.api.dto.ArchiveNodeRequestDto;
import com.wsw.summercloud.api.dto.ArchiveNodeResponseDto;
import com.wsw.summercloud.archive.entities.ArchiveNodeEntity;
import com.wsw.summercloud.archive.mapper.ArchiveNodeMapper;
import com.wsw.summercloud.archive.mapstruct.IArchiveNodeConverter;
import com.wsw.summercloud.archive.service.ArchiveNodeService;
import com.wsw.summercloud.common.constants.MemoryCacheConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/16 16:46
 */
@Slf4j
@Service
public class ArchiveNodeServiceImpl extends ServiceImpl<ArchiveNodeMapper, ArchiveNodeEntity> implements ArchiveNodeService {
    @Autowired
    private LoadingCache<String, List<ArchiveNodeEntity>> archiveNodeEntityCache;

    @Override
    public List<ArchiveNodeResponseDto> getAllArchiveNodes() {
        List<ArchiveNodeEntity> archiveNodeEntities = archiveNodeEntityCache.get(MemoryCacheConstant.ARCHIVE_NODE_ENTITY_CACHE);
        return IArchiveNodeConverter.INSTANCE.entityToResponseDto(archiveNodeEntities);
    }

    @Override
    @Transactional
    public void insertArchiveNodes(List<ArchiveNodeRequestDto> requestDtos) {
        List<ArchiveNodeEntity> archiveNodeEntities = IArchiveNodeConverter.INSTANCE.requestDtoToEntity(requestDtos);
        saveOrUpdateBatch(archiveNodeEntities);
    }
}
