package com.wsw.summercloud.common.enums;

import lombok.Getter;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 16:46
 */
@Getter
public enum TaskEventEnum {
    CREATE("创建", 1),
    ACCEPT("领取", 2),
    SUBMIT("提交", 3),
    ;

    private final String des;
    private final Integer code;

    TaskEventEnum(String des, Integer code) {
        this.des = des;
        this.code = code;
    }
}
