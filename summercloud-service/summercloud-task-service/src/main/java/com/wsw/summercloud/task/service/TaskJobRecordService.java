package com.wsw.summercloud.task.service;

import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.dto.TaskJobRecordQueryDto;
import com.wsw.summercloud.api.dto.TaskJobRecordRequestDto;
import com.wsw.summercloud.api.dto.TaskJobRecordResponseDto;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/13 16:45
 */
public interface TaskJobRecordService {
    /**
     * 批量创建任务记录
     *
     * @param requestDtos
     * @return void
     */
    void createTaskJobRecords(List<TaskJobRecordRequestDto> requestDtos);

    /**
     * 分页查询任务记录
     *
     * @param queryDto
     * @return PageInfo<TaskJobRecordResponseDto>
     */
    PageInfo<TaskJobRecordResponseDto> selectTaskJobRecords(TaskJobRecordQueryDto queryDto);
}
