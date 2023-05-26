package com.wsw.summercloud.task.controller;

import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.TaskJobQueryDto;
import com.wsw.summercloud.api.dto.TaskJobRequestDto;
import com.wsw.summercloud.api.dto.TaskJobResponseDto;
import com.wsw.summercloud.task.service.TaskJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 任务服务相关接口
 * <p>
 * <a href="http://localhost:8092/swagger-ui/index.html">swagger地址</a>
 * @Author: wangsongwen
 * @Date: 2023/4/2 0:18
 */
@Slf4j
@RestController
@RequestMapping("/task")
@Api(tags = "任务接口")
public class TaskJobController {
    @Resource
    private TaskJobService taskJobService;

    @GetMapping("/health")
    public String health() {
        String msgFromTask = "ok from task service.";
        log.info(msgFromTask);
        return msgFromTask;
    }

    @ApiOperation("批量增加任务")
    @PostMapping("/createTasks")
    public Result<Void> createTasks(@RequestBody List<TaskJobRequestDto> requestDtos) {
        Result<Void> result = Result.success();
        taskJobService.createTasks(requestDtos);
        return result;
    }

    @ApiOperation("分页查询任务")
    @PostMapping("/selectTasks")
    public Result<PageInfo<TaskJobResponseDto>> selectTask(@RequestBody TaskJobQueryDto queryDto) {
        Result<PageInfo<TaskJobResponseDto>> result = Result.success();
        result.value(taskJobService.selectTaskJobs(queryDto));
        return result;
    }

    @ApiOperation("获取所有任务")
    @GetMapping("/getAllTasks")
    public Result<List<TaskJobResponseDto>> getAllTasks() {
        Result<List<TaskJobResponseDto>> result = Result.success();
        result.value(taskJobService.getAllTasks());
        return result;
    }
}
