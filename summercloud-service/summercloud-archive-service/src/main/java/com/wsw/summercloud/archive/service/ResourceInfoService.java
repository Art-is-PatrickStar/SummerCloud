package com.wsw.summercloud.archive.service;

import com.wsw.summercloud.api.dto.ResourceInfoQueryDto;
import com.wsw.summercloud.api.dto.ResourceInfoResponseDto;
import com.wsw.summercloud.api.msg.ResourceMsg;
import org.springframework.data.domain.Page;

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
     * @param resourceMsgs
     * @return void
     */
    void saveOrUpdateResourceInfos(List<ResourceMsg> resourceMsgs);

    /**
     * 更新资源信息归档状态
     *
     * @param resourceMsgs
     * @return void
     */
    void updateResourceInfoArchiveStatus(List<ResourceMsg> resourceMsgs);

    /**
     * 分页查询资源信息
     *
     * @param queryDto
     * @return PageInfo<ResourceInfoResponseDto>
     */
    Page<ResourceInfoResponseDto> selectResourceInfos(ResourceInfoQueryDto queryDto);
}
