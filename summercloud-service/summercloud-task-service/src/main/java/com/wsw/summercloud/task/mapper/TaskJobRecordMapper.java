package com.wsw.summercloud.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}
