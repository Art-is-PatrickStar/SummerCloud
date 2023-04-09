package com.wsw.summercloud.common.annotation;

import com.wsw.summercloud.common.enums.ModuleTypeEnum;
import com.wsw.summercloud.common.enums.OperationTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 操作日志注解
 * @Author: wangsongwen
 * @Date: 2023/4/9 16:43
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpLog {
    /**
     * 操作类型
     */
    OperationTypeEnum opType() default OperationTypeEnum.ADD;

    /**
     * 日志类型
     */
    ModuleTypeEnum type() default ModuleTypeEnum.RESOURCE;

    /**
     * 日志类型实体对应的主键id
     */
    String typeId() default "";

    /**
     * 日志类型被记录的类型实体键
     */
    String moduleId() default "";

    /**
     * service类
     */
    Class serviceClass();

    /**
     * 忽略记录变化字段
     */
    String[] ignoreFields() default "";
}
