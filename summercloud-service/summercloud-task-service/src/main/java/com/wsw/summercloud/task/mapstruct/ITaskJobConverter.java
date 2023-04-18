package com.wsw.summercloud.task.mapstruct;

import com.wsw.summercloud.api.dto.TaskJobRecordRequestDto;
import com.wsw.summercloud.api.dto.TaskJobRequestDto;
import com.wsw.summercloud.api.dto.TaskJobResponseDto;
import com.wsw.summercloud.task.entities.TaskJobEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/7 16:09
 */
@Mapper
public interface ITaskJobConverter {
    ITaskJobConverter INSTANCE = Mappers.getMapper(ITaskJobConverter.class);

    @Mappings({
            @Mapping(target = "jobId", source = "resourceId")
    })
    TaskJobEntity requestDtoToEntity(TaskJobRequestDto requestDto);

    List<TaskJobEntity> requestDtoToEntity(List<TaskJobRequestDto> requestDtos);

    TaskJobResponseDto entityToResponseDto(TaskJobEntity entity);

    List<TaskJobResponseDto> entityToResponseDto(List<TaskJobEntity> entities);

    @Mappings({
            @Mapping(target = "recordId", source = "jobId")
    })
    TaskJobRecordRequestDto taskJobEntityToTaskJobRecordrequestDto(TaskJobEntity entity);

    List<TaskJobRecordRequestDto> taskJobEntityToTaskJobRecordrequestDto(List<TaskJobEntity> entities);
}
