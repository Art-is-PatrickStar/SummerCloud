package com.wsw.summercloud.archive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsw.summercloud.api.dto.ResourceInfoQueryDto;
import com.wsw.summercloud.archive.entities.ResourceInfoEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/5 22:13
 */
public interface ResourceInfoMapper extends BaseMapper<ResourceInfoEntity> {
    IPage<ResourceInfoEntity> selectResourceInfos(Page<?> page, @Param("query") ResourceInfoQueryDto queryDto);
}
