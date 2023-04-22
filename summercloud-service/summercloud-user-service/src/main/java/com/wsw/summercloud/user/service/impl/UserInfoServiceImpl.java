package com.wsw.summercloud.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsw.summercloud.api.dto.UserInfoResponseDto;
import com.wsw.summercloud.user.mapstruct.IUserInfoConverter;
import com.wsw.summercloud.user.entities.UserInfoEntity;
import com.wsw.summercloud.user.mapper.UserInfoMapper;
import com.wsw.summercloud.user.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/23 0:01
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserInfoService {
    @Override
    public List<UserInfoResponseDto> getAllUsers() {
        List<UserInfoEntity> userInfoEntities = list();
        return IUserInfoConverter.INSTANCE.entityToResponseDto(userInfoEntities);
    }
}
