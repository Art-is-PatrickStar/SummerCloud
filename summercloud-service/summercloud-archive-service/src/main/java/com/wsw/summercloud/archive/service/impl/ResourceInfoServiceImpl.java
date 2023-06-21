package com.wsw.summercloud.archive.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.dto.ResourceInfoQueryDto;
import com.wsw.summercloud.api.dto.ResourceInfoRequestDto;
import com.wsw.summercloud.api.dto.ResourceInfoResponseDto;
import com.wsw.summercloud.archive.entities.ResourceInfoEntity;
import com.wsw.summercloud.archive.mapper.ResourceInfoMapper;
import com.wsw.summercloud.archive.mapstruct.IResourceInfoConverter;
import com.wsw.summercloud.archive.service.ResourceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public void saveOrUpdateResourceInfos(List<ResourceInfoRequestDto> requestDtos) {
        List<ResourceInfoEntity> resourceInfoEntities = IResourceInfoConverter.INSTANCE.requestDtoToEntity(requestDtos);
        saveOrUpdateBatch(resourceInfoEntities);
    }

    @Override
    public void updateResourceInfoArchiveStatus(List<Long> resourceIds) {
        baseMapper.updateResourceInfoArchiveStatus(resourceIds);
    }

    @Override
    public PageInfo<ResourceInfoResponseDto> selectResourceInfos(ResourceInfoQueryDto queryDto) {
        IPage<ResourceInfoEntity> resourceInfoEntityIPage = baseMapper.selectResourceInfos(new Page<>(queryDto.getCurrentPage(), queryDto.getPageSize()), queryDto);
        List<ResourceInfoResponseDto> resourceInfoResponseDtos = IResourceInfoConverter.INSTANCE.entityToResponseDto(resourceInfoEntityIPage.getRecords());
        return new PageInfo<>(resourceInfoEntityIPage.getCurrent(), resourceInfoEntityIPage.getSize(), resourceInfoEntityIPage.getTotal(), resourceInfoResponseDtos);
    }

    @Override
    public List<ResourceInfoEntity> getNotArchivedResources() {
        Date createdTime = DateUtil.beginOfHour(new Date());
        return baseMapper.getNotArchivedResources(createdTime);
    }
}
