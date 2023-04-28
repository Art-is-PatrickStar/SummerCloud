package com.wsw.summercloud.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsw.summercloud.api.basic.ResultStatusEnums;
import com.wsw.summercloud.api.dto.UserInfoResponseDto;
import com.wsw.summercloud.api.dto.UserLoginRequestDto;
import com.wsw.summercloud.api.dto.UserLoginResponseDto;
import com.wsw.summercloud.api.dto.UserSignUpRequestDto;
import com.wsw.summercloud.common.exception.BusinessException;
import com.wsw.summercloud.common.utils.JwtUtil;
import com.wsw.summercloud.user.entities.UserInfoEntity;
import com.wsw.summercloud.user.mapper.UserInfoMapper;
import com.wsw.summercloud.user.mapstruct.IUserInfoConverter;
import com.wsw.summercloud.user.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/23 0:01
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserInfoService {
    @Resource
    private JwtUtil jwtUtil;

    @Override
    public List<UserInfoResponseDto> getAllUsers() {
        List<UserInfoEntity> userInfoEntities = list();
        return IUserInfoConverter.INSTANCE.entityToResponseDto(userInfoEntities);
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto loginRequestDto) {
        if (Objects.isNull(loginRequestDto)) {
            throw new BusinessException(ResultStatusEnums.PARAMS_EXCEPTION);
        }
        if (StrUtil.isBlank(loginRequestDto.getUsername()) && StrUtil.isBlank(loginRequestDto.getEmail())) {
            throw new BusinessException(ResultStatusEnums.USERNAME_OR_EMAIL_CAN_NOT_NULL);
        }
        if (StrUtil.isBlank(loginRequestDto.getPassword())) {
            throw new BusinessException(ResultStatusEnums.PASSWORD_CAN_NOT_NULL);
        }
        UserInfoEntity userInfoEntity = baseMapper.getUserByNameOrEmailAndPassword(loginRequestDto);
        if (Objects.isNull(userInfoEntity)) {
            throw new BusinessException(ResultStatusEnums.USER_NOT_FOUND);
        }
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("userId", userInfoEntity.getId());
        userInfoMap.put("userName", userInfoEntity.getUsername());
        userInfoMap.put("email", userInfoEntity.getEmail());
        String token = jwtUtil.createToken(userInfoMap);
        UserLoginResponseDto userLoginResponseDto = IUserInfoConverter.INSTANCE.entityToLoginResponseDto(userInfoEntity);
        userLoginResponseDto.setToken(token);
        return userLoginResponseDto;
    }

    @Override
    public void signup(UserSignUpRequestDto signUpRequestDto) {
        if (Objects.isNull(signUpRequestDto)) {
            throw new BusinessException(ResultStatusEnums.PARAMS_EXCEPTION);
        }
        if (StrUtil.isBlank(signUpRequestDto.getUsername()) && StrUtil.isBlank(signUpRequestDto.getEmail())) {
            throw new BusinessException(ResultStatusEnums.USERNAME_OR_EMAIL_CAN_NOT_NULL);
        }
        if (StrUtil.isBlank(signUpRequestDto.getPassword())) {
            throw new BusinessException(ResultStatusEnums.PASSWORD_CAN_NOT_NULL);
        }
        String username = signUpRequestDto.getUsername();
        String email = signUpRequestDto.getEmail();
        if (StrUtil.isNotBlank(username)) {
            UserInfoEntity userInfoEntity = lambdaQuery().eq(UserInfoEntity::getUsername, username).eq(UserInfoEntity::getIsDelete, 0).one();
            if (!Objects.isNull(userInfoEntity)) {
                throw new BusinessException(ResultStatusEnums.USERNAME_IS_EXIST);
            }
        }
        if (StrUtil.isNotBlank(email)) {
            UserInfoEntity userInfoEntity = lambdaQuery().eq(UserInfoEntity::getEmail, email).eq(UserInfoEntity::getIsDelete, 0).one();
            if (!Objects.isNull(userInfoEntity)) {
                throw new BusinessException(ResultStatusEnums.EMAIL_IS_EXIST);
            }
        }
        UserInfoEntity userInfoEntity = IUserInfoConverter.INSTANCE.signUpRequestDtoToEntity(signUpRequestDto);
        save(userInfoEntity);
    }
}
