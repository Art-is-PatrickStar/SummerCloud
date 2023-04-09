package com.wsw.summercloud.task.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.dto.TaskJobRecordQueryDto;
import com.wsw.summercloud.api.dto.TaskJobRecordRequestDto;
import com.wsw.summercloud.api.dto.TaskJobRecordResponseDto;
import com.wsw.summercloud.task.entities.TaskJobRecordEntity;
import com.wsw.summercloud.task.mapper.TaskJobRecordMapper;
import com.wsw.summercloud.task.mapstruct.ITaskJobRecordConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 16:01
 */
@Slf4j
@Service
public class TaskJobRecordService extends ServiceImpl<TaskJobRecordMapper, TaskJobRecordEntity> {
    public void createTaskJobRecords(List<TaskJobRecordRequestDto> requestDtos) {
        List<TaskJobRecordEntity> taskJobRecordEntities = ITaskJobRecordConverter.INSTANCE.taskJobRecordRequestDtoToTaskJobRecordEntity(requestDtos);
        baseMapper.insertTaskJobRecords(taskJobRecordEntities);
    }

    public PageInfo<TaskJobRecordResponseDto> selectTaskJobRecords(TaskJobRecordQueryDto queryDto) {
        IPage<TaskJobRecordEntity> taskJobRecordEntityIPage = baseMapper.selectTaskJobRecords(new Page<>(queryDto.getCurrentPage(), queryDto.getPageSize()), queryDto);
        List<TaskJobRecordResponseDto> taskJobRecordResponseDtos = ITaskJobRecordConverter.INSTANCE.taskJobRecordEntityToTaskJobRecordResponseDto(taskJobRecordEntityIPage.getRecords());
        return new PageInfo<>(taskJobRecordEntityIPage.getCurrent(), taskJobRecordEntityIPage.getSize(), taskJobRecordEntityIPage.getTotal(), taskJobRecordResponseDtos);
    }
}
