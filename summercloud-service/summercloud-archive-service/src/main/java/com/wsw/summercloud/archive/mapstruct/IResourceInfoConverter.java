package com.wsw.summercloud.archive.mapstruct;

import com.wsw.summercloud.api.dto.ResourceInfoResponseDto;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.archive.entities.ResourceInfoEntity;
import org.mapstruct.Mapper;
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

    ResourceInfoEntity resourceMsgToResourceInfoEntity(ResourceMsg resourceMsg);

    List<ResourceInfoEntity> resourceMsgToResourceInfoEntity(List<ResourceMsg> resourceMsgs);

    ResourceInfoResponseDto resourceInfoEntityToResourceInfoResponseDto(ResourceInfoEntity resourceInfoEntity);

    List<ResourceInfoResponseDto> resourceInfoEntityToResourceInfoResponseDto(List<ResourceInfoEntity> resourceInfoEntities);
}
