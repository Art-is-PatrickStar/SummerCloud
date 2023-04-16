package com.wsw.summercloud.archive.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.dto.ResourceInfoQueryDto;
import com.wsw.summercloud.api.dto.ResourceInfoResponseDto;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.archive.entities.ResourceInfoEntity;
import com.wsw.summercloud.archive.mapper.ResourceInfoMapper;
import com.wsw.summercloud.archive.mapstruct.IResourceInfoConverter;
import com.wsw.summercloud.archive.service.ResourceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/5 20:04
 */
@Slf4j
@Service
public class ResourceInfoServiceImpl extends ServiceImpl<ResourceInfoMapper, ResourceInfoEntity> implements ResourceInfoService {
    @Override
    @Transactional
    public void saveOrUpdateResourceInfos(List<ResourceMsg> resourceMsgs) {
        List<ResourceInfoEntity> resourceInfoEntities = IResourceInfoConverter.INSTANCE.resourceMsgToResourceInfoEntity(resourceMsgs);
        saveOrUpdateBatch(resourceInfoEntities);
    }

    @Override
    public void updateResourceInfoArchiveStatus(List<ResourceMsg> resourceMsgs) {
        lambdaUpdate().in(ResourceInfoEntity::getResourceId, resourceMsgs.stream().map(ResourceMsg::getResourceId).toArray())
                .set(ResourceInfoEntity::getArchiveStatus, 1).update();
    }

    @Override
    public PageInfo<ResourceInfoResponseDto> selectResourceInfos(ResourceInfoQueryDto queryDto) {
        IPage<ResourceInfoEntity> resourceInfoEntityIPage = baseMapper.selectResourceInfos(new Page<>(queryDto.getCurrentPage(), queryDto.getPageSize()), queryDto);
        List<ResourceInfoResponseDto> resourceInfoResponseDtos = IResourceInfoConverter.INSTANCE.resourceInfoEntityToResourceInfoResponseDto(resourceInfoEntityIPage.getRecords());
        return new PageInfo<>(resourceInfoEntityIPage.getCurrent(), resourceInfoEntityIPage.getSize(), resourceInfoEntityIPage.getTotal(), resourceInfoResponseDtos);
    }
}
