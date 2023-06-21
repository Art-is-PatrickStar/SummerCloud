package com.wsw.summercloud.archive.service;

import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.dto.ResourceInfoQueryDto;
import com.wsw.summercloud.api.dto.ResourceInfoRequestDto;
import com.wsw.summercloud.api.dto.ResourceInfoResponseDto;
import com.wsw.summercloud.archive.entities.ResourceInfoEntity;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/13 13:53
 */
public interface ResourceInfoService {
    /**
     * 保存或更新资源信息
     *
     * @param requestDtos
     * @return void
     */
    void saveOrUpdateResourceInfos(List<ResourceInfoRequestDto> requestDtos);

    /**
     * 更新资源信息归档状态
     *
     * @param resourceIds
     * @return void
     */
    void updateResourceInfoArchiveStatus(List<Long> resourceIds);

    /**
     * 分页查询资源信息
     *
     * @param queryDto
     * @return PageInfo<ResourceInfoResponseDto>
     */
    PageInfo<ResourceInfoResponseDto> selectResourceInfos(ResourceInfoQueryDto queryDto);

    List<ResourceInfoEntity> getNotArchivedResources();
}
