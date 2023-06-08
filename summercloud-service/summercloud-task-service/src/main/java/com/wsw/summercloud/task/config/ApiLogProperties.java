package com.wsw.summercloud.task.config;

import lombok.Data;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/8 13:13
 */
@Data
public class ApiLogProperties {
    /**
     * 保存日志缓冲区频率(秒)
     */
    private int logPersistInterval;

    /**
     * 启用接口日志
     */
    private boolean enabled;

    /**
     * 日志过期时间(天)
     */
    private int expireDays;

    /**
     * 过期日志清理频率(小时)
     */
    private int logCleanupInterval;
}
