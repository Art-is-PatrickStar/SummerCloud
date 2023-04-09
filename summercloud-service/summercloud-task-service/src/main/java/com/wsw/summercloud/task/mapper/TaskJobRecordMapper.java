package com.wsw.summercloud.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsw.summercloud.api.dto.TaskJobRecordQueryDto;
import com.wsw.summercloud.task.entities.TaskJobRecordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 16:02
 */
public interface TaskJobRecordMapper extends BaseMapper<TaskJobRecordEntity> {
    void insertTaskJobRecords(@Param("list") List<TaskJobRecordEntity> taskJobRecordEntities);

    IPage<TaskJobRecordEntity> selectTaskJobRecords(Page<?> page, @Param("query") TaskJobRecordQueryDto queryDto);
}
