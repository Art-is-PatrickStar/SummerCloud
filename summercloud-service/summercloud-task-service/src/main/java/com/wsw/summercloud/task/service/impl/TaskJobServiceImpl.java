package com.wsw.summercloud.task.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.dto.TaskJobQueryDto;
import com.wsw.summercloud.api.dto.TaskJobRecordRequestDto;
import com.wsw.summercloud.api.dto.TaskJobRequestDto;
import com.wsw.summercloud.api.dto.TaskJobResponseDto;
import com.wsw.summercloud.task.entities.TaskJobEntity;
import com.wsw.summercloud.task.mapper.TaskJobMapper;
import com.wsw.summercloud.task.mapstruct.ITaskJobConverter;
import com.wsw.summercloud.task.service.TaskJobRecordService;
import com.wsw.summercloud.task.service.TaskJobService;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/7 15:54
 */
@Slf4j
@Service
public class TaskJobServiceImpl extends ServiceImpl<TaskJobMapper, TaskJobEntity> implements TaskJobService {
    @Resource
    private TaskJobRecordService taskJobRecordService;

    @Override
    @Transactional
    public void createTasks(List<TaskJobRequestDto> requestDtos) {
        List<TaskJobEntity> taskJobEntities = ITaskJobConverter.INSTANCE.requestDtoToEntity(requestDtos);
        baseMapper.insertTaskJobs(taskJobEntities);
        List<TaskJobRecordRequestDto> taskJobRecordRequestDtos = ITaskJobConverter.INSTANCE.taskJobEntityToTaskJobRecordrequestDto(taskJobEntities);
        taskJobRecordService.createTaskJobRecords(taskJobRecordRequestDtos);
    }

    @Override
    public PageInfo<TaskJobResponseDto> selectTaskJobs(TaskJobQueryDto queryDto) {
        IPage<TaskJobEntity> taskJobEntityIPage = baseMapper.selectTaskJobs(new Page<>(queryDto.getCurrentPage(), queryDto.getPageSize()), queryDto);
        List<TaskJobResponseDto> taskJobResponseDtos = ITaskJobConverter.INSTANCE.entityToResponseDto(taskJobEntityIPage.getRecords());
        return new PageInfo<>(taskJobEntityIPage.getCurrent(), taskJobEntityIPage.getSize(), taskJobEntityIPage.getTotal(), taskJobResponseDtos);
    }
}
