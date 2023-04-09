package com.wsw.summercloud.task;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.dto.TaskJobQueryDto;
import com.wsw.summercloud.api.dto.TaskJobResponseDto;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.task.service.TaskJobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SummercloudTaskServiceApplicationTests {
    @Autowired
    private TaskJobService taskJobService;

    @Test
    void testInsert() {
        List<ResourceMsg> resourceMsgs = new ArrayList<>();
        ResourceMsg resourceMsg1 = new ResourceMsg();
        resourceMsg1.setResourceId(12L);
        resourceMsgs.add(resourceMsg1);
        ResourceMsg resourceMsg2 = new ResourceMsg();
        resourceMsg2.setResourceId(13L);
        resourceMsgs.add(resourceMsg2);
        taskJobService.createTasks(resourceMsgs);
    }

    @Test
    void testQuery() {
        TaskJobQueryDto queryDto = new TaskJobQueryDto();
        queryDto.setCurrentPage(1);
        queryDto.setPageSize(10);
        queryDto.setCreatedTimeBegin(DateUtil.parseDate("2023-04-01 00:00:00"));
        queryDto.setCreatedTimeEnd(DateUtil.parseDate("2023-04-10 00:00:00"));
        PageInfo<TaskJobResponseDto> taskJobResponseDtoPageInfo = taskJobService.selectTaskJobs(queryDto);
        System.out.println(JSON.toJSONString(taskJobResponseDtoPageInfo));
    }
}
