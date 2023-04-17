package com.wsw.summercloud.archive.service.impl;

import com.wsw.summercloud.api.dto.ArchiveNodeResponseDto;
import com.wsw.summercloud.archive.entities.ArchiveNodeEntity;
import com.wsw.summercloud.archive.mapstruct.IArchiveNodeConverter;
import com.wsw.summercloud.archive.repository.ArchiveNodeRepository;
import com.wsw.summercloud.archive.service.ArchiveNodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/16 16:46
 */
@Slf4j
@Service
public class ArchiveNodeServiceImpl implements ArchiveNodeService {
    @Resource
    private ArchiveNodeRepository archiveNodeRepository;

    @Override
    public List<ArchiveNodeResponseDto> getAllArchiveNodes() {
        List<ArchiveNodeEntity> archiveNodeEntities = archiveNodeRepository.getAllArchiveNodes();
        return IArchiveNodeConverter.INSTANCE.entityToResponseDto(archiveNodeEntities);
    }
}
