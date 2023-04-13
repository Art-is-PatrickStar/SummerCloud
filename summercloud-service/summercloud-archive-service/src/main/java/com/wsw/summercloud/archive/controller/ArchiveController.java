package com.wsw.summercloud.archive.controller;

import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.ResourceInfoQueryDto;
import com.wsw.summercloud.api.dto.ResourceInfoResponseDto;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.archive.client.TaskClient;
import com.wsw.summercloud.archive.service.ResourceInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "归档相关接口")
public class ArchiveController {
    @Resource
    private TaskClient taskClient;
    @Resource
    private ResourceInfoService resourceInfoService;

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

    @ApiOperation("批量增加资源")
    @PostMapping("/insertResourceInfos")
    public Result<Void> insertResourceInfo(@RequestBody List<ResourceMsg> resourceMsgs) {
        Result<Void> result = Result.success();
        resourceInfoService.saveOrUpdateResourceInfos(resourceMsgs);
        return result;
    }

    @ApiOperation("查询资源")
    @PostMapping("/selectResourceInfos")
    public Result<PageInfo<ResourceInfoResponseDto>> selectResourceInfos(@RequestBody ResourceInfoQueryDto queryDto) {
        Result<PageInfo<ResourceInfoResponseDto>> result = Result.success();
        result.value(resourceInfoService.selectResourceInfos(queryDto));
        return result;
    }
}
