package com.wsw.summercloud.archive.mapstruct;

import com.wsw.summercloud.api.dto.ResourceInfoRequestDto;
import com.wsw.summercloud.api.dto.ResourceInfoResponseDto;
import com.wsw.summercloud.api.dto.TaskJobRequestDto;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.archive.entities.ResourceInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/6 22:55
 */
@Mapper
public interface IResourceInfoConverter {
    IResourceInfoConverter INSTANCE = Mappers.getMapper(IResourceInfoConverter.class);

    @Mappings({
            @Mapping(target = "resourceId", source = "resourceId", defaultExpression = "java(cn.hutool.core.util.IdUtil.getSnowflake().nextId())"),
            @Mapping(target = "enableType", source = "enableType", defaultValue = "1")
    })
    ResourceInfoRequestDto resourceMsgToRequestDto(ResourceMsg resourceMsg);

    List<ResourceInfoRequestDto> resourceMsgToRequestDto(List<ResourceMsg> resourceMsgs);

    ResourceInfoResponseDto entityToResponseDto(ResourceInfoEntity entity);

    List<ResourceInfoResponseDto> entityToResponseDto(List<ResourceInfoEntity> entities);

    @Mappings({
            @Mapping(target = "resourceId", source = "resourceId", defaultExpression = "java(cn.hutool.core.util.IdUtil.getSnowflake().nextId())"),
            @Mapping(target = "enableType", source = "enableType", defaultValue = "1"),
            @Mapping(target = "archiveStatus", constant = "0")
    })
    ResourceInfoEntity requestDtoToEntity(ResourceInfoRequestDto requestDto);

    List<ResourceInfoEntity> requestDtoToEntity(List<ResourceInfoRequestDto> requestDtos);

    @Mappings({
            @Mapping(target = "jobStatus", constant = "1"),
            @Mapping(target = "isLock", constant = "0"),
            @Mapping(target = "isDelete", constant = "0")
    })
    TaskJobRequestDto requestDtoToTaskJobRequestDto(ResourceInfoRequestDto requestDto);

    List<TaskJobRequestDto> requestDtoToTaskJobRequestDto(List<ResourceInfoRequestDto> requestDtos);
}
