package com.wsw.summercloud.archive.client;

import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.TaskJobRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

/**
 * @Description: 使用SpringBoot3官方已经支持的声明式服务调用方式来调用远程接口
 * @Author: wangsongwen
 * @Date: 2023/6/20 17:21
 */
@HttpExchange(url = "/task", accept = "application/json", contentType = "application/json")
public interface TaskServiceClient {
    @GetExchange("/health")
    String health();

    @PostMapping("/createTasks")
    Result<Void> createTasks(@RequestBody List<TaskJobRequestDto> requestDtos);
}