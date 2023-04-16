package com.wsw.summercloud.archive.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsw.summercloud.api.dto.ArchiveNodeResponseDto;
import com.wsw.summercloud.archive.entities.ArchiveNodeEntity;
import com.wsw.summercloud.archive.mapper.ArchiveNodeMapper;
import com.wsw.summercloud.archive.mapstruct.IArchiveNodeConverter;
import com.wsw.summercloud.archive.service.ArchiveNodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/16 16:46
 */
@Slf4j
@Service
public class ArchiveNodeServiceImpl extends ServiceImpl<ArchiveNodeMapper, ArchiveNodeEntity> implements ArchiveNodeService {
    @Override
    public List<ArchiveNodeResponseDto> getAllArchiveNodes() {
        List<ArchiveNodeEntity> archiveNodeEntities = lambdaQuery().eq(ArchiveNodeEntity::getIsDelete, 0).list();
        return IArchiveNodeConverter.INSTANCE.entityToResponseDto(archiveNodeEntities);
    }
}
