package com.wsw.summercloud.task.mapstruct;

import com.wsw.summercloud.api.dto.TaskJobRecordRequestDto;
import com.wsw.summercloud.task.entities.TaskJobRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 16:03
 */
@Mapper
public interface ITaskJobRecordConverter {
    ITaskJobRecordConverter INSTANCE = Mappers.getMapper(ITaskJobRecordConverter.class);

    TaskJobRecordEntity taskJobRecordRequestDtoToTaskJobRecordEntity(TaskJobRecordRequestDto requestDto);

    List<TaskJobRecordEntity> taskJobRecordRequestDtoToTaskJobRecordEntity(List<TaskJobRecordRequestDto> requestDtos);
}
