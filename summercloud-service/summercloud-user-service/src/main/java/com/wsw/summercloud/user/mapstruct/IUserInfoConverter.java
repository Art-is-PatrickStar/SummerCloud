package com.wsw.summercloud.user.mapstruct;

import com.wsw.summercloud.api.dto.UserInfoResponseDto;
import com.wsw.summercloud.user.entities.UserInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/23 0:14
 */
@Mapper
public interface IUserInfoConverter {
    IUserInfoConverter INSTANCE = Mappers.getMapper(IUserInfoConverter.class);

    UserInfoResponseDto entityToResponseDto(UserInfoEntity entity);

    List<UserInfoResponseDto> entityToResponseDto(List<UserInfoEntity> entities);
}
