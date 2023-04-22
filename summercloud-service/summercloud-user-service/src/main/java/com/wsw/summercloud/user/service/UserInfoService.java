package com.wsw.summercloud.user.service;

import com.wsw.summercloud.api.dto.UserInfoResponseDto;

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
}
