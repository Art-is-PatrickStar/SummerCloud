package com.wsw.summercloud.task.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsw.summercloud.api.dto.TaskJobRecordRequestDto;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.task.entities.TaskJobEntity;
import com.wsw.summercloud.task.entities.TaskJobRecordEntity;
import com.wsw.summercloud.task.mapper.TaskJobRecordMapper;
import com.wsw.summercloud.task.mapstruct.ITaskJobConverter;
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
    public void createTaskJobRecords(List<TaskJobRecordRequestDto> taskJobRecordRequestDtos) {
        List<TaskJobRecordEntity> taskJobRecordEntities = ITaskJobRecordConverter.INSTANCE.taskJobRecordRequestDtoToTaskJobRecordEntity(taskJobRecordRequestDtos);
        baseMapper.insertTaskJobRecords(taskJobRecordEntities);
    }
}
