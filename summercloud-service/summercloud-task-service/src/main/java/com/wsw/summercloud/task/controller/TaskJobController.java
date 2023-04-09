package com.wsw.summercloud.task.controller;

import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.TaskJobQueryDto;
import com.wsw.summercloud.api.dto.TaskJobResponseDto;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.task.service.TaskJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "任务相关接口")
public class TaskJobController {
    private final TaskJobService taskJobService;

    public TaskJobController(TaskJobService taskJobService) {
        this.taskJobService = taskJobService;
    }

    @GetMapping("/hello")
    public Result<String> sayHello() {
        String msgFromTask = "hello from task service.";
        log.info(msgFromTask);
        return Result.success(msgFromTask);
    }

    @ApiOperation("批量增加任务")
    @PostMapping("/createTasks")
    public Result<Void> createTasks(@RequestBody List<ResourceMsg> resourceMsgs) {
        Result<Void> result = Result.success();
        taskJobService.createTasks(resourceMsgs);
        return result;
    }

    @ApiOperation("查询任务")
    @PostMapping("/selectTasks")
    public Result<PageInfo<TaskJobResponseDto>> selectTask(@RequestBody TaskJobQueryDto queryDto) {
        Result<PageInfo<TaskJobResponseDto>> result = Result.success();
        result.value(taskJobService.selectTaskJobs(queryDto));
        return result;
    }
}
