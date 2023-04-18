package com.wsw.summercloud.archive.mapstruct;

import com.wsw.summercloud.api.dto.ArchiveNodeRequestDto;
import com.wsw.summercloud.api.dto.ArchiveNodeResponseDto;
import com.wsw.summercloud.archive.entities.ArchiveNodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
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

    ArchiveNodeResponseDto entityToResponseDto(ArchiveNodeEntity entity);

    List<ArchiveNodeResponseDto> entityToResponseDto(List<ArchiveNodeEntity> entities);

    @Mappings({
            @Mapping(target = "enable", source = "enable", defaultValue = "0"),
            @Mapping(target = "isDelete", source = "isDelete", defaultValue = "0")
    })
    ArchiveNodeEntity requestDtoToEntity(ArchiveNodeRequestDto requestDto);

    List<ArchiveNodeEntity> requestDtoToEntity(List<ArchiveNodeRequestDto> requestDtos);
}
