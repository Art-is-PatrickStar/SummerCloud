package com.wsw.summercloud.task.service;

import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.dto.TaskJobQueryDto;
import com.wsw.summercloud.api.dto.TaskJobRequestDto;
import com.wsw.summercloud.api.dto.TaskJobResponseDto;

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
     * @param requestDtos
     * @return void
     */
    void createTasks(List<TaskJobRequestDto> requestDtos);

    /**
     * 分页查询任务
     *
     * @param queryDto
     * @return PageInfo<TaskJobResponseDto>
     */
    PageInfo<TaskJobResponseDto> selectTaskJobs(TaskJobQueryDto queryDto);

    /**
     * 获取所有任务
     *
     * @return List<TaskJobResponseDto>
     */
    List<TaskJobResponseDto> getAllTasks();

    /**
     * 更新任务
     *
     * @param requestDto
     * @return void
     */
    void updateTask(TaskJobRequestDto requestDto);
}
