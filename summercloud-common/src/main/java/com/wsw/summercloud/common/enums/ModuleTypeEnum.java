package com.wsw.summercloud.common.enums;

import lombok.Getter;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 16:46
 */
@Getter
public enum ModuleTypeEnum {
    RESOURCE("资源", 1),
    TASK("任务", 2),
    TASKRECORD("任务记录", 3),
    ;

    private final String des;
    private final Integer code;

    ModuleTypeEnum(String des, Integer code) {
        this.des = des;
        this.code = code;
    }
}
