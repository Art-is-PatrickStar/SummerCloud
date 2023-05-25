package com.wsw.summercloud.api.basic;

/**
 * @Description: 枚举一些常用的API操作码
 * @Author: wangsongwen
 * @Date: 2023/4/1 23:50
 */
public enum ResultStatus {
    //系统级
    SUCCESS(200, "操作成功", 0),
    FAILED(500, "操作失败", 0),
    UNAUTHORIZED(401, "非法访问", 0),
    FORBIDDEN(403, "没有权限", 0),
    VALIDATE_FAILED(404, "请求资源不存在", 0),
    SYSTEM_EXCEPTION(405, "系统异常", 0),
    SEND_MAIL_EXCEPTION(406, "发送邮件异常", 0),

    //参数校验级
    PARAMS_EXCEPTION(5000, "参数异常", 0),

    //业务级
    MICRO_SERVICE_EXCEPTION(6000, "微服务异常", 1),
    SQL_ERROR_EXCEPTION(6001, "SQL语法异常", 0),
    CLICK_FREQUENT(6002, "请勿频繁点击", 0),
    TOKEN_IS_NULL(6003, "token不能为空", 0),
    TOKEN_IS_INVALID(6004, "token无效", 0),
    OP_LOG_SAVE_FAILD(6005, "操作日志插入失败", 0),
    TASK_RECORD_INSERT_FAILD(6006, "新增任务记录失败", 0),
    USER_NOT_FOUND(6007, "用户不存在", 0),
    USER_INFORMATION_ERROR(6008, "用户名或密码错误", 0),
    USER_IS_EXIST(6009, "用户名已存在", 0),
    REDIS_LOCK_GET_FAILD(60010, "分布式锁获取失败", 0),
    USERNAME_OR_EMAIL_CAN_NOT_NULL(60011, "用户名或邮箱不能为空", 0),
    PASSWORD_CAN_NOT_NULL(60012, "密码不能为空", 0),
    USERNAME_IS_EXIST(60013, "用户名已存在", 0),
    EMAIL_IS_EXIST(60014, "邮箱已存在", 0),
    ;

    private final Integer status;
    private final String msg;
    private final Integer alert; //alert: 1 告警 0 不告警

    ResultStatus(Integer status, String msg, Integer alert) {
        this.status = status;
        this.msg = msg;
        this.alert = alert;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getAlert() {
        return alert;
    }

    public static ResultStatus getByStatus(Integer status) {
        for (ResultStatus result : ResultStatus.values()) {
            if (result.getStatus().equals(status)) {
                return result;
            }
        }
        return null;
    }
}
