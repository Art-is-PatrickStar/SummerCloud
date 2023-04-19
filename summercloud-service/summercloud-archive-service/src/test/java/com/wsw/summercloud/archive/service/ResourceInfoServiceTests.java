package com.wsw.summercloud.archive.service;

import com.alibaba.fastjson.JSON;
import com.wsw.summercloud.api.dto.ResourceInfoRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
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
    @Resource
    private ResourceInfoService resourceInfoService;

    @Test
    void testInsert() {
        List<ResourceInfoRequestDto> requestDtos = new ArrayList<>();
        ResourceInfoRequestDto requestDto1 = new ResourceInfoRequestDto();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", "wswtest1");
        dataMap.put("md5", "1234567891");
        dataMap.put("size", 101);
        requestDto1.setData(JSON.toJSONString(dataMap));
        requestDtos.add(requestDto1);
        ResourceInfoRequestDto requestDto2 = new ResourceInfoRequestDto();
        Map<String, Object> dataMap2 = new HashMap<>();
        dataMap2.put("title", "wswtest2");
        dataMap2.put("md5", "1234567892");
        dataMap2.put("size", 102);
        requestDto2.setData(JSON.toJSONString(dataMap2));
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
