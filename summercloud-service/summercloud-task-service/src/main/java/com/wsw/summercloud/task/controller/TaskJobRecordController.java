package com.wsw.summercloud.task.controller;

import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.TaskJobRecordQueryDto;
import com.wsw.summercloud.api.dto.TaskJobRecordResponseDto;
import com.wsw.summercloud.task.service.TaskJobRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 16:05
 */
@Slf4j
@RestController
@RequestMapping("/taskRecord")
@Tag(name = "TaskJobRecordController", description = "任务记录接口")
public class TaskJobRecordController {
    @Autowired
    private TaskJobRecordService taskJobRecordService;

    @Operation(summary = "分页查询任务记录")
    @Parameters({
            @Parameter(name = "queryDto", description = "任务信息记录查询实体类", required = true)
    })
    @PostMapping("/selectTaskRecords")
    public Result<PageInfo<TaskJobRecordResponseDto>> selectTaskRecords(@RequestBody TaskJobRecordQueryDto queryDto) {
        Result<PageInfo<TaskJobRecordResponseDto>> result = Result.success();
        result.value(taskJobRecordService.selectTaskJobRecords(queryDto));
        return result;
    }
}
