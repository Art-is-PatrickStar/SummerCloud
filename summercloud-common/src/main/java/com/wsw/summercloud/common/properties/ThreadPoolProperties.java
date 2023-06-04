package com.wsw.summercloud.common.properties;

import lombok.Data;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/4 17:26
 */
@Data
public class ThreadPoolProperties {
    private Integer corePoolSize;
    private Integer maximumPoolSize;
    private Integer keepAliveTime;
    private Integer queueCapacity;
}
