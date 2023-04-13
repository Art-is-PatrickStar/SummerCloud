package com.wsw.summercloud.task.service;

import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.dto.TaskJobQueryDto;
import com.wsw.summercloud.api.dto.TaskJobResponseDto;
import com.wsw.summercloud.api.msg.ResourceMsg;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/13 16:44
 */
public interface TaskJobService {
    /**
     * 批量创建任务
     *
     * @param resourceMsgs
     * @return void
     */
    void createTasks(List<ResourceMsg> resourceMsgs);

    /**
     * 分页查询任务
     *
     * @param queryDto
     * @return PageInfo<TaskJobResponseDto>
     */
    PageInfo<TaskJobResponseDto> selectTaskJobs(TaskJobQueryDto queryDto);
}
