package com.wsw.summercloud.task.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.dto.TaskJobQueryDto;
import com.wsw.summercloud.api.dto.TaskJobRecordRequestDto;
import com.wsw.summercloud.api.dto.TaskJobRequestDto;
import com.wsw.summercloud.api.dto.TaskJobResponseDto;
import com.wsw.summercloud.common.constants.MemoryCacheConstant;
import com.wsw.summercloud.task.entities.TaskJobEntity;
import com.wsw.summercloud.task.event.TaskCreateEvent;
import com.wsw.summercloud.task.mapper.TaskJobMapper;
import com.wsw.summercloud.task.mapstruct.ITaskJobConverter;
import com.wsw.summercloud.task.service.TaskJobRecordService;
import com.wsw.summercloud.task.service.TaskJobService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/7 15:54
 */
@Slf4j
@Service
public class TaskJobServiceImpl extends ServiceImpl<TaskJobMapper, TaskJobEntity> implements TaskJobService {
    @Autowired
    private TaskJobRecordService taskJobRecordService;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private LoadingCache<String, List<TaskJobEntity>> taskJobEntityCache;

    @Override
    @Transactional
    public void createTasks(List<TaskJobRequestDto> requestDtos) {
        List<TaskJobEntity> taskJobEntities = ITaskJobConverter.INSTANCE.requestDtoToEntity(requestDtos);
        baseMapper.insertTaskJobs(taskJobEntities);
        List<TaskJobRecordRequestDto> taskJobRecordRequestDtos = ITaskJobConverter.INSTANCE.taskJobEntityToTaskJobRecordrequestDto(taskJobEntities);
        taskJobRecordService.createTaskJobRecords(taskJobRecordRequestDtos);
        taskJobEntities.forEach(taskJobEntity ->
                applicationEventPublisher.publishEvent(new TaskCreateEvent(this, taskJobEntity))
        );
    }

    @Override
    public PageInfo<TaskJobResponseDto> selectTaskJobs(TaskJobQueryDto queryDto) {
        if (Objects.isNull(queryDto.getCreatedTimeEnd())) {
            queryDto.setCreatedTimeEnd(new Date());
        }
        IPage<TaskJobEntity> taskJobEntityIPage = baseMapper.selectTaskJobs(new Page<>(queryDto.getCurrentPage(), queryDto.getPageSize()), queryDto);
        List<TaskJobResponseDto> taskJobResponseDtos = ITaskJobConverter.INSTANCE.entityToResponseDto(taskJobEntityIPage.getRecords());
        return new PageInfo<>(taskJobEntityIPage.getCurrent(), taskJobEntityIPage.getSize(), taskJobEntityIPage.getTotal(), taskJobResponseDtos);
    }

    @Override
    public List<TaskJobResponseDto> getAllTasks() {
        List<TaskJobEntity> allTaskJobs = taskJobEntityCache.get(MemoryCacheConstant.TASK_JOB_ENTITY_CACHE);
        return ITaskJobConverter.INSTANCE.entityToResponseDto(allTaskJobs);
    }

    @Override
    public void updateTask(TaskJobRequestDto requestDto) {
        TaskJobEntity taskJobEntity = ITaskJobConverter.INSTANCE.requestDtoToEntity(requestDto);
        updateById(taskJobEntity);
    }
}
