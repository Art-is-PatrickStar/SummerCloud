package com.wsw.summercloud.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsw.summercloud.api.dto.UserLoginRequestDto;
import com.wsw.summercloud.user.entities.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/23 0:00
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {
    UserInfoEntity getUserByNameOrEmailAndPassword(@Param("request") UserLoginRequestDto loginRequestDto);
}
