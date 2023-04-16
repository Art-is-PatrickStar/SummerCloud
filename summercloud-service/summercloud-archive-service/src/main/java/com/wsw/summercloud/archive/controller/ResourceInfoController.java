package com.wsw.summercloud.archive.controller;

import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.ResourceInfoQueryDto;
import com.wsw.summercloud.api.dto.ResourceInfoResponseDto;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.archive.service.ResourceInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/16 16:49
 */
@Slf4j
@RestController
@RequestMapping("/resource")
@Api(tags = "资源信息接口")
public class ResourceInfoController {
    @Resource
    private ResourceInfoService resourceInfoService;

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
