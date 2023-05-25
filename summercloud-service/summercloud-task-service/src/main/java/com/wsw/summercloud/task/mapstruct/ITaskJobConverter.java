package com.wsw.summercloud.task.mapstruct;

import com.wsw.summercloud.api.data.BusTaskData;
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
            @Mapping(target = "jobId", source = "jobId", defaultExpression = "java(cn.hutool.core.util.IdUtil.getSnowflake().nextId())"),
            @Mapping(target = "jobStatus", source = "jobStatus", defaultValue = "1"),
            @Mapping(target = "isLock", source = "isLock", defaultValue = "0"),
            @Mapping(target = "isDelete", source = "isDelete", defaultValue = "0")
    })
    TaskJobEntity requestDtoToEntity(TaskJobRequestDto requestDto);

    List<TaskJobEntity> requestDtoToEntity(List<TaskJobRequestDto> requestDtos);

    TaskJobResponseDto entityToResponseDto(TaskJobEntity entity);

    List<TaskJobResponseDto> entityToResponseDto(List<TaskJobEntity> entities);

    @Mappings({
            @Mapping(target = "recordId", expression = "java(cn.hutool.core.util.IdUtil.getSnowflake().nextId())")
    })
    TaskJobRecordRequestDto taskJobEntityToTaskJobRecordrequestDto(TaskJobEntity entity);

    List<TaskJobRecordRequestDto> taskJobEntityToTaskJobRecordrequestDto(List<TaskJobEntity> entities);

    BusTaskData entityToBusTaskData(TaskJobEntity entity);

    List<BusTaskData> entityToBusTaskData(List<TaskJobEntity> entities);
}
