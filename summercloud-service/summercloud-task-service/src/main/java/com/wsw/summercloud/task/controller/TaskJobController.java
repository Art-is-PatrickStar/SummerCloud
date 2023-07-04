package com.wsw.summercloud.task.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.*;
import com.wsw.summercloud.common.annotation.OpLog;
import com.wsw.summercloud.common.enums.ModuleTypeEnum;
import com.wsw.summercloud.common.enums.OperationTypeEnum;
import com.wsw.summercloud.task.client.ArchiveServiceClient;
import com.wsw.summercloud.task.client.UserServiceClient;
import com.wsw.summercloud.task.service.TaskJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 任务信息相关接口
 * <p>
 * <a href="http://localhost:8092/swagger-ui/index.html">swagger地址</a>
 * @Author: wangsongwen
 * @Date: 2023/4/2 0:18
 */
@Slf4j
@RestController
@RequestMapping("/task")
@Tag(name = "TaskJobController", description = "任务接口")
public class TaskJobController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TaskJobService taskJobService;
    @Autowired
    private ArchiveServiceClient archiveServiceClient;
    @Autowired
    private UserServiceClient userServiceClient;

    @GetMapping("/health")
    public String health() {
        String msgFromTask = "ok from task service.";
        log.info(msgFromTask);
        return msgFromTask;
    }

    @GetMapping("/client/getAllArchiveNodes")
    public String getAllArchiveNodes() throws JsonProcessingException {
        Result<List<ArchiveNodeResponseDto>> allArchiveNodes = archiveServiceClient.getAllArchiveNodes();
        String msgFromArchive = objectMapper.writeValueAsString(allArchiveNodes.getData());
        log.info(msgFromArchive);
        return msgFromArchive;
    }

    @GetMapping("/client/getAllUsers")
    public String getAllUsers() throws JsonProcessingException {
        Result<List<UserInfoResponseDto>> allUsers = userServiceClient.getAllUsers();
        String msgFromUser = objectMapper.writeValueAsString(allUsers.getData());
        log.info(msgFromUser);
        return msgFromUser;
    }

    @Operation(summary = "批量增加任务")
    @Parameters({
            @Parameter(name = "requestDtos", description = "任务信息请求实体类", required = true)
    })
    @PostMapping("/createTasks")
    public Result<Void> createTasks(@RequestBody List<TaskJobRequestDto> requestDtos) {
        Result<Void> result = Result.success();
        taskJobService.createTasks(requestDtos);
        return result;
    }

    @Operation(summary = "分页查询任务")
    @Parameters({
            @Parameter(name = "queryDto", description = "任务信息查询实体类", required = true)
    })
    @PostMapping("/selectTasks")
    public Result<PageInfo<TaskJobResponseDto>> selectTask(@RequestBody TaskJobQueryDto queryDto) {
        Result<PageInfo<TaskJobResponseDto>> result = Result.success();
        result.value(taskJobService.selectTaskJobs(queryDto));
        return result;
    }

    @Operation(summary = "获取所有任务")
    @GetMapping("/getAllTasks")
    public Result<List<TaskJobResponseDto>> getAllTasks() {
        Result<List<TaskJobResponseDto>> result = Result.success();
        result.value(taskJobService.getAllTasks());
        return result;
    }

    @Operation(summary = "修改任务")
    @Parameters({
            @Parameter(name = "requestDto", description = "任务信息请求实体类", required = true)
    })
    @PostMapping("/updateTask")
    @OpLog(opType = OperationTypeEnum.UPDATE, type = ModuleTypeEnum.TASK, typeId = "jobId", moduleId = "jobId", serviceClass = TaskJobService.class, ignoreFields = {"createdTime", "updatedTime"})
    public Result<Void> updateTask(@RequestBody TaskJobRequestDto requestDto) {
        Result<Void> result = Result.success();
        taskJobService.updateTask(requestDto);
        return result;
    }
}
