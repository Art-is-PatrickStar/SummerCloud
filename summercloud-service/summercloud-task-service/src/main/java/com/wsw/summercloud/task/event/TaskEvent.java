package com.wsw.summercloud.task.event;

import com.wsw.summercloud.api.data.BusTaskData;
import com.wsw.summercloud.task.entities.TaskJobEntity;
import com.wsw.summercloud.task.mapstruct.ITaskJobConverter;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/5/25 10:43
 */
public abstract class TaskEvent extends ApplicationEvent {
    private static final long serialVersionUID = -7964823581435856986L;

    @Getter
    private BusTaskData busTaskData;

    public TaskEvent(Object source) {
        super(source);
    }

    public TaskEvent(Object source, BusTaskData busTaskData) {
        super(source);
        this.busTaskData = busTaskData;
    }

    public TaskEvent(Object source, TaskJobEntity taskJobEntity) {
        super(source);
        this.busTaskData = ITaskJobConverter.INSTANCE.entityToBusTaskData(taskJobEntity);
    }

    public abstract String getEventName();
}
