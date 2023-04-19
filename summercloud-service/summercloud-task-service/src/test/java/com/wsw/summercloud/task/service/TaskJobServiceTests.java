package com.wsw.summercloud.task.service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.wsw.summercloud.api.basic.PageInfo;
import com.wsw.summercloud.api.dto.TaskJobQueryDto;
import com.wsw.summercloud.api.dto.TaskJobRequestDto;
import com.wsw.summercloud.api.dto.TaskJobResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/13 16:57
 */
@SpringBootTest
public class TaskJobServiceTests {
    @Autowired
    private TaskJobService taskJobService;

    @Test
    void testInsert() {
        List<TaskJobRequestDto> requestDtos = new ArrayList<>();
        TaskJobRequestDto requestDto1 = new TaskJobRequestDto();
        requestDto1.setResourceId(1648606679252160512L);
        requestDto1.setArchiveId(1648610022812934144L);
        requestDtos.add(requestDto1);
        TaskJobRequestDto requestDto2 = new TaskJobRequestDto();
        requestDto2.setResourceId(1648606679252160513L);
        requestDto2.setArchiveId(1648610022812934144L);
        requestDtos.add(requestDto2);
        taskJobService.createTasks(requestDtos);
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
