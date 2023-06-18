package com.wsw.summercloud.api.basic;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 统一结果返回对象
 * @Author: wangsongwen
 * @Date: 2023/4/1 23:50
 */
@Data
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 3535289962320806820L;

    private Integer status;
    private String msg;
    private T data;
    private Boolean success;
    private Long startTime = -1L;
    private Long timeConsume;

    public static <W> Result<W> fail() {
        return fail(ResultStatus.FAILED);
    }

    public static <W> Result<W> fail(String msg) {
        return fail(ResultStatus.FAILED, msg);
    }

    public static <W> Result<W> fail(ResultStatus resultStatus) {
        return fail(resultStatus, resultStatus.getMsg());
    }

    public static <W> Result<W> fail(ResultStatus statusEnums, String msg) {
        return fail(statusEnums.getStatus(), msg);
    }

    public static <W> Result<W> fail(Integer status, String msg) {
        Result<W> ret = new Result<>();
        ret.setSuccess(false);
        ret.setStatus(status);
        ret.setMsg(msg);
        ret.startTime = System.currentTimeMillis();
        ret.timeConsume = System.currentTimeMillis() - ret.startTime;
        return ret;
    }

    public static <W> Result<W> success() {
        return success(null);
    }

    public static <T> Result<T> success(T val) {
        Result<T> ret = new Result<>();
        ret.setSuccess(true);
        ret.setStatus(ResultStatus.SUCCESS.getStatus());
        ret.setMsg(ResultStatus.SUCCESS.getMsg());
        ret.startTime = System.currentTimeMillis();
        ret.timeConsume = System.currentTimeMillis() - ret.startTime;
        ret.value(val);
        return ret;
    }

    public Result<T> value(T val) {
        if (val == null) {
            return this;
        }
        this.success = true;
        this.data = val;
        this.setStatus(ResultStatus.SUCCESS.getStatus());
        this.setMsg(ResultStatus.SUCCESS.getMsg());
        this.timeConsume = System.currentTimeMillis() - this.startTime;
        return this;
    }
}
