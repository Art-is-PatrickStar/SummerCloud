package com.wsw.summercloud.task.mapstruct;

import com.wsw.summercloud.api.dto.TaskJobRecordRequestDto;
import com.wsw.summercloud.api.dto.TaskJobRequestDto;
import com.wsw.summercloud.api.dto.TaskJobResponseDto;
import com.wsw.summercloud.api.msg.ResourceMsg;
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

    TaskJobEntity taskJobRequestDtoToTaskJobEntity(TaskJobRequestDto requestDto);

    List<TaskJobEntity> taskJobRequestDtoToTaskJobEntity(List<TaskJobRequestDto> requestDtos);

    @Mappings({
            @Mapping(target = "resourceId", source = "resourceId"),
            @Mapping(target = "jobId", source = "resourceId"),
            @Mapping(target = "jobStatus", constant = "1")
    })
    TaskJobEntity resourceMsgToTaskJobEntity(ResourceMsg resourceMsg);

    List<TaskJobEntity> resourceMsgToTaskJobEntity(List<ResourceMsg> resourceMsgs);

    TaskJobResponseDto taskJobEntityToTaskJobResponseDto(TaskJobEntity entity);

    List<TaskJobResponseDto> taskJobEntityToTaskJobResponseDto(List<TaskJobEntity> entities);

    @Mappings({
            @Mapping(target = "recordId", source = "jobId")
    })
    TaskJobRecordRequestDto taskJobEntityToTaskJobRecordrequestDto(TaskJobEntity entity);

    List<TaskJobRecordRequestDto> taskJobEntityToTaskJobRecordrequestDto(List<TaskJobEntity> entities);
}
