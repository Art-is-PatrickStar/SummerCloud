package com.wsw.summercloud.task.event;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/5/25 10:46
 */
public class TaskCreateEvent extends TaskEvent {
    private static final long serialVersionUID = 1143480364398854870L;

    public TaskCreateEvent(Object source) {
        super(source);
    }
}
