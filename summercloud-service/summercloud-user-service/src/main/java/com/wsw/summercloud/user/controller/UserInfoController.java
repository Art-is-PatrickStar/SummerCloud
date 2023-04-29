package com.wsw.summercloud.user.controller;

import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.UserInfoResponseDto;
import com.wsw.summercloud.api.dto.UserLoginRequestDto;
import com.wsw.summercloud.api.dto.UserLoginResponseDto;
import com.wsw.summercloud.api.dto.UserSignUpRequestDto;
import com.wsw.summercloud.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/health")
    public String health() {
        String msgFromUser = "ok from user service.";
        log.info(msgFromUser);
        return msgFromUser;
    }

    @ApiOperation("获取所有用户信息")
    @GetMapping("/getAllUsers")
    public Result<List<UserInfoResponseDto>> getAllUsers() {
        Result<List<UserInfoResponseDto>> result = Result.success();
        result.value(userInfoService.getAllUsers());
        return result;
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto loginRequestDto) {
        Result<UserLoginResponseDto> result = Result.success();
        result.value(userInfoService.login(loginRequestDto));
        return result;
    }

    @ApiOperation("用户注册")
    @PostMapping("/signup")
    public Result<Void> signup(@RequestBody UserSignUpRequestDto signUpRequestDto) {
        userInfoService.signup(signUpRequestDto);
        return Result.success();
    }
}
