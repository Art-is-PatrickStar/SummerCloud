package com.wsw.summercloud.common.enums;

import lombok.Getter;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 16:45
 */
@Getter
public enum OperationTypeEnum {
    ADD("ADD", "新增"),
    UPDATE("UPDATE", "修改"),
    DELETE("DELETE", "删除");

    private final String operation;
    private final String message;

    OperationTypeEnum(String operation, String message) {
        this.operation = operation;
        this.message = message;
    }
}
