package com.wsw.summercloud.task.controller;

import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.TaskJobRecordQueryDto;
import com.wsw.summercloud.api.dto.TaskJobRecordResponseDto;
import com.wsw.summercloud.task.service.TaskJobRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 16:05
 */
@Slf4j
@RestController
@RequestMapping("/taskRecord")
@Api(tags = "任务记录相关接口")
public class TaskJobRecordController {
    @Resource
    private TaskJobRecordService taskJobRecordService;

    @ApiOperation("查询任务记录")
    @PostMapping("/selectTaskRecords")
    public Result<PageInfo<TaskJobRecordResponseDto>> selectTaskRecords(@RequestBody TaskJobRecordQueryDto queryDto) {
        Result<PageInfo<TaskJobRecordResponseDto>> result = Result.success();
        result.value(taskJobRecordService.selectTaskJobRecords(queryDto));
        return result;
    }
}
