package com.wsw.summercloud.archive.mapstruct;

import com.wsw.summercloud.api.dto.ArchiveNodeResponseDto;
import com.wsw.summercloud.archive.entities.ArchiveNodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/16 17:20
 */
@Mapper
public interface IArchiveNodeConverter {
    IArchiveNodeConverter INSTANCE = Mappers.getMapper(IArchiveNodeConverter.class);

    ArchiveNodeResponseDto entityToResponseDto(ArchiveNodeEntity archiveNodeEntity);

    List<ArchiveNodeResponseDto> entityToResponseDto(List<ArchiveNodeEntity> archiveNodeEntities);
}
