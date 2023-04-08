package com.wsw.summercloud.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.alibaba.fastjson.JSON;
import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.dto.TaskJobQueryDto;
import com.wsw.summercloud.api.dto.TaskJobRequestDto;
import com.wsw.summercloud.api.dto.TaskJobResponseDto;
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
        List<TaskJobRequestDto> taskJobRequestDtos = new ArrayList<>();
        TaskJobRequestDto taskJobRequestDto1 = new TaskJobRequestDto();
        taskJobRequestDto1.setJobId(1L);
        taskJobRequestDto1.setResourceId(1L);
        taskJobRequestDtos.add(taskJobRequestDto1);
        TaskJobRequestDto taskJobRequestDto2 = new TaskJobRequestDto();
        taskJobRequestDto2.setJobId(2L);
        taskJobRequestDto2.setResourceId(2L);
        taskJobRequestDtos.add(taskJobRequestDto2);
        taskJobService.insertTaskJobs(taskJobRequestDtos);
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
