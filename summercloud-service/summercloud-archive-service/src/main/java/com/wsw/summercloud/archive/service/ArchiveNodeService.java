package com.wsw.summercloud.archive.service;

import com.wsw.summercloud.api.dto.ArchiveNodeResponseDto;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/16 16:46
 */
public interface ArchiveNodeService {
    /**
     * 获取所有的归档节点
     *
     * @return List<ArchiveNodeResponseDto>
     */
    List<ArchiveNodeResponseDto> getAllArchiveNodes();
}
