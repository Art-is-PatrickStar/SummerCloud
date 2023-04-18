package com.wsw.summercloud.archive.client;

import com.wsw.summercloud.api.dto.TaskJobRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 在k8s中可以使用restTemplate来调用微服务
 * @Author: wangsongwen
 * @Date: 2023/4/2 0:13
 */
@Component
public class TaskClient {
    @Value("${task.service.url}")
    private String taskServiceUrl;
    @Resource
    private RestTemplate archiveRestTemplate;

    public String sayHello() {
        return archiveRestTemplate.getForObject(taskServiceUrl + "/task/hello", String.class);
    }

    public void createTask(List<TaskJobRequestDto> requestDtos) {
        archiveRestTemplate.postForObject(taskServiceUrl + "/task/createTasks", requestDtos, String.class);
    }
}
