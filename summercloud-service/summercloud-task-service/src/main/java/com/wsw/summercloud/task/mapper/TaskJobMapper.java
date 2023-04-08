package com.wsw.summercloud.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsw.summercloud.api.dto.TaskJobQueryDto;
import com.wsw.summercloud.task.entities.TaskJobEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/7 15:52
 */
public interface TaskJobMapper extends BaseMapper<TaskJobEntity> {
    void insertTaskJobs(@Param("list") List<TaskJobEntity> taskJobEntities);

    IPage<TaskJobEntity> selectTaskJobs(Page<?> page, @Param("query") TaskJobQueryDto queryDto);
}