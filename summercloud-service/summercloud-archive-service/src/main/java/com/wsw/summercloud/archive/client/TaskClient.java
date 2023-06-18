package com.wsw.summercloud.archive.client;

import com.wsw.summercloud.api.dto.TaskJobRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Description: 在k8s中可以使用restTemplate来调用微服务
 * @Author: wangsongwen
 * @Date: 2023/4/2 0:13
 */
@Component
public class TaskClient {
    @Autowired
    private RestTemplate archiveRestTemplate;

    @Value("${task.service.url}")
    private String taskServiceUrl;

    public String health() {
        return archiveRestTemplate.getForObject(taskServiceUrl + "/task/health", String.class);
    }

    public void createTask(List<TaskJobRequestDto> requestDtos) {
        archiveRestTemplate.postForObject(taskServiceUrl + "/task/createTasks", requestDtos, String.class);
    }
}
