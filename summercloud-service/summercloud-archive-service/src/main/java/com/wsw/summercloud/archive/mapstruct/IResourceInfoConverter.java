package com.wsw.summercloud.archive.mapstruct;

import com.wsw.summercloud.api.dto.ResourceInfoResponseDto;
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
            @Mapping(target = "enableType", source = "enableType", defaultValue = "1"),
            @Mapping(target = "archiveStatus", constant = "0")
    })
    ResourceInfoEntity resourceMsgToResourceInfoEntity(ResourceMsg resourceMsg);

    List<ResourceInfoEntity> resourceMsgToResourceInfoEntity(List<ResourceMsg> resourceMsgs);

    ResourceInfoResponseDto resourceInfoEntityToResourceInfoResponseDto(ResourceInfoEntity entity);

    List<ResourceInfoResponseDto> resourceInfoEntityToResourceInfoResponseDto(List<ResourceInfoEntity> entities);
}
