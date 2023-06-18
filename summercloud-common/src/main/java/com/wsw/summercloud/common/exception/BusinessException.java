package com.wsw.summercloud.common.exception;

import com.wsw.summercloud.api.basic.ResultStatus;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 自定义异常类
 * @Author: wangsongwen
 * @Date: 2023/4/6 23:57
 */
public class BusinessException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 6094159908069439356L;

    private final String message;
    private final ResultStatus resultStatus;

    public BusinessException() {
        this(ResultStatus.SYSTEM_EXCEPTION);
    }

    public BusinessException(ResultStatus resultStatus) {
        this(resultStatus, resultStatus.getMsg());
    }

    public BusinessException(ResultStatus resultStatus, String message) {
        this(resultStatus, message, null);
    }

    public BusinessException(ResultStatus resultStatus, Throwable cause) {
        this(resultStatus, resultStatus.getMsg(), cause);
    }

    public BusinessException(ResultStatus resultStatus, String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.resultStatus = resultStatus;
    }

    public String getMessage() {
        return message;
    }

    public ResultStatus getResultStatusEnums() {
        return resultStatus;
    }
}
