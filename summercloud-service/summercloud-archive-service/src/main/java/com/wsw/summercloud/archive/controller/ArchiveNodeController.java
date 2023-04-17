package com.wsw.summercloud.archive.controller;

import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.ArchiveNodeRequestDto;
import com.wsw.summercloud.api.dto.ArchiveNodeResponseDto;
import com.wsw.summercloud.archive.client.TaskClient;
import com.wsw.summercloud.archive.service.ArchiveNodeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 归档服务相关接口
 * <p>
 * <a href="http://localhost:8091/swagger-ui/index.html">swagger地址</a>
 * @Author: wangsongwen
 * @Date: 2023/4/2 0:01
 */
@Slf4j
@RestController
@RequestMapping("/archive")
@Api(tags = "归档节点接口")
public class ArchiveNodeController {
    @Resource
    private TaskClient taskClient;
    @Resource
    private ArchiveNodeService archiveNodeService;

    @GetMapping("/hello")
    public Result<String> sayHello() {
        String msgFromArchive = "hello from archive service.";
        log.info(msgFromArchive);
        return Result.success(msgFromArchive);
    }

    @GetMapping("/getHelloFromTaskService")
    public Result<String> getHelloFromTaskService() {
        String msgFromTask = taskClient.sayHello();
        log.info(msgFromTask);
        return Result.success(msgFromTask);
    }

    @GetMapping("/getAllArchiveNodes")
    public Result<List<ArchiveNodeResponseDto>> getAllArchiveNodes() {
        Result<List<ArchiveNodeResponseDto>> result = Result.success();
        result.value(archiveNodeService.getAllArchiveNodes());
        return result;
    }

    @PostMapping("/insertArchiveNodes")
    public Result<Void> insertArchiveNodes(@RequestBody List<ArchiveNodeRequestDto> requestDtos) {
        Result<Void> result = Result.success();
        archiveNodeService.insertArchiveNodes(requestDtos);
        return result;
    }
}
