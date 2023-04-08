package com.wsw.summercloud.archive;

import com.alibaba.fastjson.JSON;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.archive.entities.ResourceInfoEntity;
import com.wsw.summercloud.archive.mapstruct.IResourceInfoConverter;
import com.wsw.summercloud.archive.service.ResourceInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SummercloudArchiveServiceApplicationTests {
    @Resource
    private ResourceInfoService resourceInfoService;

    @Test
    void testInsert() {
        List<ResourceMsg> resourceMsgs = new ArrayList<>();
        ResourceMsg resourceMsg = new ResourceMsg();
        resourceMsg.setResourceId(2L);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", "wswtest2");
        dataMap.put("md5", "12345678910");
        dataMap.put("size", 101);
        resourceMsg.setData(JSON.toJSONString(dataMap));
        resourceMsg.setEnableType(1);
        resourceMsgs.add(resourceMsg);
        List<ResourceInfoEntity> resourceInfoEntities = IResourceInfoConverter.INSTANCE.resourceMsgToResourceInfoEntity(resourceMsgs);
        resourceInfoService.saveBatch(resourceInfoEntities);
    }

}
