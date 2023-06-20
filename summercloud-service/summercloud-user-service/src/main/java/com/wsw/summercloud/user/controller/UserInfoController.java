package com.wsw.summercloud.user.controller;

import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.UserInfoResponseDto;
import com.wsw.summercloud.api.dto.UserLoginRequestDto;
import com.wsw.summercloud.api.dto.UserLoginResponseDto;
import com.wsw.summercloud.api.dto.UserSignUpRequestDto;
import com.wsw.summercloud.user.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 用户信息相关接口
 * <p>
 * <a href="http://localhost:8093/swagger-ui/index.html">swagger地址</a>
 * @Author: wangsongwen
 * @Date: 2023/4/23 0:08
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "UserInfoController", description = "用户信息接口")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/health")
    public String health() {
        String msgFromUser = "ok from user service.";
        log.info(msgFromUser);
        return msgFromUser;
    }

    @Operation(summary = "获取所有用户信息")
    @GetMapping("/getAllUsers")
    public Result<List<UserInfoResponseDto>> getAllUsers() {
        Result<List<UserInfoResponseDto>> result = Result.success();
        result.value(userInfoService.getAllUsers());
        return result;
    }

    @Operation(summary = "用户登录")
    @Parameters({
            @Parameter(name = "loginRequestDto", description = "用户登录请求参数", required = true)
    })
    @PostMapping("/login")
    public Result<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto loginRequestDto) {
        Result<UserLoginResponseDto> result = Result.success();
        result.value(userInfoService.login(loginRequestDto));
        return result;
    }

    @Operation(summary = "用户注册")
    @Parameters({
            @Parameter(name = "signUpRequestDto", description = "用户注册请求参数", required = true)
    })
    @PostMapping("/signup")
    public Result<Void> signup(@RequestBody UserSignUpRequestDto signUpRequestDto) {
        userInfoService.signup(signUpRequestDto);
        return Result.success();
    }
}