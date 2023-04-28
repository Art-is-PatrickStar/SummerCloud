package com.wsw.summercloud.user.service;

import com.wsw.summercloud.api.dto.UserInfoResponseDto;
import com.wsw.summercloud.api.dto.UserLoginRequestDto;
import com.wsw.summercloud.api.dto.UserLoginResponseDto;
import com.wsw.summercloud.api.dto.UserSignUpRequestDto;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/23 0:01
 */
public interface UserInfoService {
    /**
     * 获取所有用户信息
     *
     * @return List<UserInfoResponseDto>
     */
    List<UserInfoResponseDto> getAllUsers();

    /**
     * 用户登录
     *
     * @param loginRequestDto
     * @return UserLoginResponseDto
     */
    UserLoginResponseDto login(UserLoginRequestDto loginRequestDto);

    /**
     * 用户注册
     *
     * @param signUpRequestDto
     */
    void signup(UserSignUpRequestDto signUpRequestDto);
}
