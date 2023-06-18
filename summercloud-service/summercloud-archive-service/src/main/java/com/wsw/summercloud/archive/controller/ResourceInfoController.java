package com.wsw.summercloud.archive.controller;

import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.ResourceInfoQueryDto;
import com.wsw.summercloud.api.dto.ResourceInfoRequestDto;
import com.wsw.summercloud.api.dto.ResourceInfoResponseDto;
import com.wsw.summercloud.archive.service.ResourceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/16 16:49
 */
@Slf4j
@RestController
@RequestMapping("/resource")
@Tag(name = "ResourceInfoController", description = "资源信息接口")
public class ResourceInfoController {
    @Autowired
    private ResourceInfoService resourceInfoService;

    @Operation(summary = "批量增加资源")
    @Parameters({
            @Parameter(name = "requestDtos", description = "资源信息请求实体类", required = true)
    })
    @PostMapping("/insertResourceInfos")
    public Result<Void> insertResourceInfo(@RequestBody List<ResourceInfoRequestDto> requestDtos) {
        Result<Void> result = Result.success();
        resourceInfoService.saveOrUpdateResourceInfos(requestDtos);
        return result;
    }

    @Operation(summary = "分页查询资源")
    @Parameters({
            @Parameter(name = "queryDto", description = "资源信息查询实体类", required = true)
    })
    @PostMapping("/selectResourceInfos")
    public Result<Page<ResourceInfoResponseDto>> selectResourceInfos(@RequestBody ResourceInfoQueryDto queryDto) {
        Result<Page<ResourceInfoResponseDto>> result = Result.success();
        result.value(resourceInfoService.selectResourceInfos(queryDto));
        return result;
    }
}
