package com.wsw.summercloud.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.wsw.summercloud.api.basic.ResultStatus;
import com.wsw.summercloud.api.dto.UserInfoResponseDto;
import com.wsw.summercloud.api.dto.UserLoginRequestDto;
import com.wsw.summercloud.api.dto.UserLoginResponseDto;
import com.wsw.summercloud.api.dto.UserSignUpRequestDto;
import com.wsw.summercloud.common.constants.MemoryCacheConstant;
import com.wsw.summercloud.common.exception.BusinessException;
import com.wsw.summercloud.common.utils.JwtUtil;
import com.wsw.summercloud.user.entities.UserInfoEntity;
import com.wsw.summercloud.user.mapstruct.IUserInfoConverter;
import com.wsw.summercloud.user.repository.UserInfoRepository;
import com.wsw.summercloud.user.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private LoadingCache<String, List<UserInfoEntity>> userInfoEntityCache;

    @Override
    public List<UserInfoResponseDto> getAllUsers() {
        List<UserInfoEntity> userInfoEntities = userInfoEntityCache.get(MemoryCacheConstant.USER_INFO_ENTITY_CACHE);
        return IUserInfoConverter.INSTANCE.entityToResponseDto(userInfoEntities);
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto loginRequestDto) {
        if (Objects.isNull(loginRequestDto)) {
            throw new BusinessException(ResultStatus.PARAMS_EXCEPTION);
        }
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();
        if (StrUtil.isBlank(username)) {
            throw new BusinessException(ResultStatus.USERNAME_CAN_NOT_NULL);
        }
        if (StrUtil.isBlank(password)) {
            throw new BusinessException(ResultStatus.PASSWORD_CAN_NOT_NULL);
        }
        UserInfoEntity userInfoEntity = userInfoRepository.getUserInfoEntity(username, password);
        if (Objects.isNull(userInfoEntity)) {
            throw new BusinessException(ResultStatus.USER_INFORMATION_ERROR);
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
            throw new BusinessException(ResultStatus.PARAMS_EXCEPTION);
        }
        String username = signUpRequestDto.getUsername();
        String password = signUpRequestDto.getPassword();
        if (StrUtil.isBlank(username)) {
            throw new BusinessException(ResultStatus.USERNAME_CAN_NOT_NULL);
        }
        if (StrUtil.isBlank(password)) {
            throw new BusinessException(ResultStatus.PASSWORD_CAN_NOT_NULL);
        }
        UserInfoEntity ifExistUserInfoEntity = userInfoRepository.getUserInfoEntity(username);
        if (!Objects.isNull(ifExistUserInfoEntity)) {
            throw new BusinessException(ResultStatus.USER_IS_EXIST);
        }
        UserInfoEntity userInfoEntity = IUserInfoConverter.INSTANCE.signUpRequestDtoToEntity(signUpRequestDto);
        userInfoRepository.save(userInfoEntity);
    }
}