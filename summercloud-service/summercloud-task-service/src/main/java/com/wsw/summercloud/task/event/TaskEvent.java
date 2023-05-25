package com.wsw.summercloud.task.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/5/25 10:43
 */
public abstract class TaskEvent extends ApplicationEvent {
    private static final long serialVersionUID = -7964823581435856986L;

    public TaskEvent(Object source) {
        super(source);
    }
}
