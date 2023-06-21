package com.wsw.summercloud.archive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsw.summercloud.api.dto.ResourceInfoQueryDto;
import com.wsw.summercloud.archive.entities.ResourceInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/21 10:18
 */
@Mapper
public interface ResourceInfoMapper extends BaseMapper<ResourceInfoEntity> {
    void updateResourceInfoArchiveStatus(@Param("list") List<Long> resourceIds);

    List<ResourceInfoEntity> getNotArchivedResources(@Param("createdTime") Date createdTime);

    IPage<ResourceInfoEntity> selectResourceInfos(Page<?> page, @Param("query") ResourceInfoQueryDto queryDto);
}
