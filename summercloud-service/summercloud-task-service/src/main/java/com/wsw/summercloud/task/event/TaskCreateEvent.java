package com.wsw.summercloud.task.event;

import com.wsw.summercloud.common.enums.TaskEventEnum;
import com.wsw.summercloud.task.entities.TaskJobEntity;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/5/25 10:46
 */
public class TaskCreateEvent extends TaskEvent {
    private static final long serialVersionUID = 1143480364398854870L;

    public TaskCreateEvent(Object source, TaskJobEntity taskJobEntity) {
        super(source, taskJobEntity);
    }

    @Override
    public String getEventName() {
        return TaskEventEnum.CREATE.getDes();
    }
}
