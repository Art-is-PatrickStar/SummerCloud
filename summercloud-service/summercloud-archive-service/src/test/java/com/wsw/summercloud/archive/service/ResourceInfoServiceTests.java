package com.wsw.summercloud.archive.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsw.summercloud.api.dto.ResourceInfoRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/13 16:17
 */
@SpringBootTest
public class ResourceInfoServiceTests {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ResourceInfoService resourceInfoService;

    @Test
    void testInsert() throws JsonProcessingException {
        List<ResourceInfoRequestDto> requestDtos = new ArrayList<>();
        ResourceInfoRequestDto requestDto1 = new ResourceInfoRequestDto();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", "wswtest1");
        dataMap.put("md5", "1234567891");
        dataMap.put("size", 101);
        requestDto1.setData(objectMapper.writeValueAsString(dataMap));
        requestDtos.add(requestDto1);
        ResourceInfoRequestDto requestDto2 = new ResourceInfoRequestDto();
        Map<String, Object> dataMap2 = new HashMap<>();
        dataMap2.put("title", "wswtest2");
        dataMap2.put("md5", "1234567892");
        dataMap2.put("size", 102);
        requestDto2.setData(objectMapper.writeValueAsString(dataMap2));
        requestDtos.add(requestDto2);
        resourceInfoService.saveOrUpdateResourceInfos(requestDtos);
    }

    @Test
    void testUpdate() {
        List<Long> resourceIds = new ArrayList<>();
        resourceIds.add(1648606679252160512L);
        resourceIds.add(1648606679252160513L);
        resourceInfoService.updateResourceInfoArchiveStatus(resourceIds);
    }
}
