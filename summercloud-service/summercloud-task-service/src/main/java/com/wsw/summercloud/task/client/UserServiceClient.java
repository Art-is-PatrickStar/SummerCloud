package com.wsw.summercloud.task.client;

import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.UserInfoResponseDto;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/21 16:43
 */
@HttpExchange(url = "/user", accept = "application/json", contentType = "application/json")
public interface UserServiceClient {
    @GetExchange("/getAllUsers")
    Result<List<UserInfoResponseDto>> getAllUsers();
}
