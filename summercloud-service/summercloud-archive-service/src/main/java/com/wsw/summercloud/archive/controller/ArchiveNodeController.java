package com.wsw.summercloud.archive.controller;

import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.ArchiveNodeRequestDto;
import com.wsw.summercloud.api.dto.ArchiveNodeResponseDto;
import com.wsw.summercloud.archive.client.TaskServiceClient;
import com.wsw.summercloud.archive.service.ArchiveNodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 归档信息相关接口
 * <p>
 * <a href="http://localhost:8091/swagger-ui/index.html">swagger地址</a>
 * @Author: wangsongwen
 * @Date: 2023/4/2 0:01
 */
@Slf4j
@RestController
@RequestMapping("/archive")
@Tag(name = "ArchiveNodeController", description = "归档节点接口")
public class ArchiveNodeController {
    @Autowired
    private TaskServiceClient taskServiceClient;
    @Autowired
    private ArchiveNodeService archiveNodeService;

    @GetMapping("/health")
    public String health() {
        String msgFromArchive = "ok from archive service.";
        log.info(msgFromArchive);
        return msgFromArchive;
    }

    @GetMapping("/getHealthFromTaskService")
    public String getHealthFromTaskService() {
        String msgFromTask = taskServiceClient.health();
        log.info(msgFromTask);
        return msgFromTask;
    }

    @Operation(summary = "获取所有归档节点")
    @GetMapping("/getAllArchiveNodes")
    public Result<List<ArchiveNodeResponseDto>> getAllArchiveNodes() {
        Result<List<ArchiveNodeResponseDto>> result = Result.success();
        result.value(archiveNodeService.getAllArchiveNodes());
        return result;
    }

    @Operation(summary = "批量增加归档节点")
    @Parameters({
            @Parameter(name = "requestDtos", description = "归档节点请求实体类", required = true)
    })
    @PostMapping("/insertArchiveNodes")
    public Result<Void> insertArchiveNodes(@RequestBody List<ArchiveNodeRequestDto> requestDtos) {
        Result<Void> result = Result.success();
        archiveNodeService.insertArchiveNodes(requestDtos);
        return result;
    }
}