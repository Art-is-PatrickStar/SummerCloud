package com.wsw.summercloud.task.listener;

import com.wsw.summercloud.task.event.TaskCreateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/5/25 10:53
 */
@Slf4j
@Component
public class TaskCreateListener {
    @EventListener(TaskCreateEvent.class)
    public void taskCreateSendMsg(TaskCreateEvent taskCreateEvent) {

    }
}
