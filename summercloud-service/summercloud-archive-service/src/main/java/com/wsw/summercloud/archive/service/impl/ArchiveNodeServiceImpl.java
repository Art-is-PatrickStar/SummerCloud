package com.wsw.summercloud.archive.service.impl;

import com.wsw.summercloud.api.dto.ArchiveNodeRequestDto;
import com.wsw.summercloud.api.dto.ArchiveNodeResponseDto;
import com.wsw.summercloud.archive.entities.ArchiveNodeEntity;
import com.wsw.summercloud.archive.mapstruct.IArchiveNodeConverter;
import com.wsw.summercloud.archive.repository.ArchiveNodeRepository;
import com.wsw.summercloud.archive.service.ArchiveNodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/16 16:46
 */
@Slf4j
@Service
public class ArchiveNodeServiceImpl implements ArchiveNodeService {
    @Autowired
    private ArchiveNodeRepository archiveNodeRepository;

    @Override
    public List<ArchiveNodeResponseDto> getAllArchiveNodes() {
        List<ArchiveNodeEntity> archiveNodeEntities = archiveNodeRepository.getAllArchiveNodes();
        return IArchiveNodeConverter.INSTANCE.entityToResponseDto(archiveNodeEntities);
    }

    @Override
    public void insertArchiveNodes(List<ArchiveNodeRequestDto> requestDtos) {
        List<ArchiveNodeEntity> archiveNodeEntities = IArchiveNodeConverter.INSTANCE.requestDtoToEntity(requestDtos);
        archiveNodeRepository.saveAll(archiveNodeEntities);
    }
}
