package com.wsw.summercloud.user.controller;

import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.UserInfoResponseDto;
import com.wsw.summercloud.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用户服务相关接口
 * <p>
 * <a href="http://localhost:8093/swagger-ui/index.html">swagger地址</a>
 * @Author: wangsongwen
 * @Date: 2023/4/23 0:08
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户信息接口")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @ApiOperation("获取所有用户信息")
    @GetMapping("/getAllUsers")
    public Result<List<UserInfoResponseDto>> getAllUsers() {
        Result<List<UserInfoResponseDto>> result = Result.success();
        result.value(userInfoService.getAllUsers());
        return result;
    }
}
