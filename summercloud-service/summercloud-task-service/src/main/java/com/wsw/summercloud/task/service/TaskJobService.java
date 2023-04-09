package com.wsw.summercloud.task.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.dto.TaskJobQueryDto;
import com.wsw.summercloud.api.dto.TaskJobRecordRequestDto;
import com.wsw.summercloud.api.dto.TaskJobRequestDto;
import com.wsw.summercloud.api.dto.TaskJobResponseDto;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.task.entities.TaskJobEntity;
import com.wsw.summercloud.task.mapper.TaskJobMapper;
import com.wsw.summercloud.task.mapstruct.ITaskJobConverter;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/7 15:54
 */
@Slf4j
@Service
public class TaskJobService extends ServiceImpl<TaskJobMapper, TaskJobEntity> {
    private final TaskJobRecordService taskJobRecordService;

    public TaskJobService(TaskJobRecordService taskJobRecordService) {
        this.taskJobRecordService = taskJobRecordService;
    }

    @Transactional
    public void createTasks(List<ResourceMsg> resourceMsgs) {
        List<TaskJobEntity> taskJobEntities = ITaskJobConverter.INSTANCE.resourceMsgToTaskJobEntity(resourceMsgs);
        baseMapper.insertTaskJobs(taskJobEntities);
        List<TaskJobRecordRequestDto> taskJobRecordRequestDtos = ITaskJobConverter.INSTANCE.taskJobEntityToTaskJobRecordrequestDto(taskJobEntities);
        taskJobRecordService.createTaskJobRecords(taskJobRecordRequestDtos);
    }

    public PageInfo<TaskJobResponseDto> selectTaskJobs(TaskJobQueryDto queryDto) {
        IPage<TaskJobEntity> taskJobEntityIPage = baseMapper.selectTaskJobs(new Page<>(queryDto.getCurrentPage(), queryDto.getPageSize()), queryDto);
        List<TaskJobResponseDto> taskJobResponseDtos = ITaskJobConverter.INSTANCE.taskJobEntityToTaskJobResponseDto(taskJobEntityIPage.getRecords());
        return new PageInfo<>(taskJobEntityIPage.getCurrent(), taskJobEntityIPage.getSize(), taskJobEntityIPage.getTotal(), taskJobResponseDtos);
    }
}
